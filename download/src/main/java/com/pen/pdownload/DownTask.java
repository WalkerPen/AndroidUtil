package com.pen.pdownload;

import android.util.Log;

import com.pen.pdownload.bean.ProgressInfo;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Executors;


/**
 * Created by Pen on 2018/1/11.
 */

public class DownTask {

//    private static HashMap<String, DownTask> downTasks = new HashMap<>();

    public String url;
    public String dir;
    public String path;
    private String tempPath;
    private boolean tempName;
    private boolean isStart;
    private boolean pause; //暂停指令
    private boolean cancel; //停止
    private DownloadManager downloadManager;
    private DownloadState state;

    private ProgressInfo mProgressInfo;
    private int downloadSize;
    //    private long mStart;
    private long updateTime; // 发送消息的时间
    private DownloadStateListener mDownloadStateListener;
    private Runnable mRunnable;

    private DownTask() {
    }

    public static DownTask getInstance(DownloadManager downloadManager, String url) {
        DownTask downTask = downloadManager.downTasks.get(url);
        if (downTask == null) {
            synchronized (DownTask.class) {
                if (downTask == null) {
                    downTask = new DownTask();
                    downTask.url = url;
                    downTask.downloadManager = downloadManager;
                    downTask.mProgressInfo = new ProgressInfo();
                    downTask.mProgressInfo.setUrl(downTask.url);
                    downloadManager.downTasks.put(url, downTask);
                }
            }
        }
        return downTask;
    }

    public DownTask path(String path, boolean tempName) {
        this.path = path;
        this.tempName = tempName;
        if (tempName) {
            tempPath = path + ".temp";
        }
        return this;
    }

    public void start() {
        //加入任务栈，等待下载
        mProgressInfo.clear();
        DownTask.this.state = DownloadState.WAIT;
        mDownloadStateListener.onStateChange(state, mProgressInfo);
        mRunnable = new DownThread();
        downloadManager.threadPool.execute(mRunnable);
    }

    public DownTask setListener(DownloadStateListener listener) {
        this.mDownloadStateListener = listener;
        return this;
    }

    public void pause() {
        pause = true;
    }

    public void cancel() {
        //停止下载，清空数据库下载信息，删除文件
        cancel = true;
        //如果还没开始任务，那么任务从线程池删除
        if (!isStart) {
            downloadManager.threadPool.remove(mRunnable);
            DownTask.this.state = DownloadState.CANCEL;
            if (mDownloadStateListener != null) {
                mDownloadStateListener.onStateChange(state, mProgressInfo);
            }
        }

        if (pause) {
            //如果暂停，那么任务已经完成了，只能直接未完成删除文件
            DownTask.this.state = DownloadState.CANCEL;
            mDownloadStateListener.onStateChange(state, mProgressInfo);
            DownloadStartDao.getInstance(DownloadManager.context).delete(url);

            if (tempName) {
                new File(tempPath).delete();
            } else {
                new File(path).delete();
            }
        }
    }

    private class DownThread implements Runnable {

        public DownThread() {
        }

        @Override
        public void run() {
            InputStream is = null;
            BufferedInputStream bis = null;
            BufferedRandomAccessFile accessFile = null;
            try {
                //初始化
                if (isStart) {
                    Log.d("测试", "正在下载");
                    return;
                }

                isStart = true;
                pause = false;
                cancel = false;

                //开始请求
                DownTask.this.state = DownloadState.REQUEST;
                mDownloadStateListener.onStateChange(state, mProgressInfo);

                // 如果文件不存在，表示已经删除，那么将数据库中对应的下载信息删除
                File file = null;
                if (tempName) {
                    file = new File(tempPath);
                    if (!file.exists()) {
                        file = new File(path);
                    }
                } else {
                    file = new File(path);
                }
                if (!file.exists()) {
                    DownloadStartDao.getInstance(DownloadManager.context).delete(url);
                }
                //如果所在文件夹不存在，就创建
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                //初始化进度信息
                HttpURLConnection connection = getConnection(DownTask.this.url);
                //线程数量
                int contentLength = -1;
                for (int i = 0; i < 5; i++) {
                    contentLength = connection.getContentLength();
                    if (contentLength != -1) {
                        break;
                    }
                }

                if (contentLength == -1) {
                    //如果获取不到
                    throw new IOException("网络异常");
                }

                int start = 0;
                int db_start = DownloadStartDao.getInstance(DownloadManager.context).getStart(DownTask.this.url);
                if (db_start != 0) {
                    start = db_start;
                } else {
                    start = 0;
                }
                int end = contentLength - 1;
                downloadSize = start;

                mProgressInfo.setTotalSize(contentLength);
                mProgressInfo.setDownloadSize(downloadSize);

                //下载完成
                if (downloadSize == contentLength) {
                    //下载完成移除任务
                    downloadManager.downTasks.remove(url);
                    DownTask.this.state = DownloadState.COMPLETE;
                    mDownloadStateListener.onStateChange(state, mProgressInfo);
//                    pause = true;
                    isStart = false;
                    return;
                }

                connection = getConnection(DownTask.this.url);
                connection.setRequestProperty("Range", "bytes=" + start + "-" + end); //设置读取文件的位置，和结束位置

                for (int i = 0; i < downloadManager.reconnectCount; i++) {
                    is = connection.getInputStream();
                    if (is != null) {
                        break;
                    }
                    if (i == downloadManager.reconnectCount - 1) {
                        throw new IOException("网络异常");
                    }
                }
                bis = new BufferedInputStream(is);

                if (tempName) {
                    accessFile = new BufferedRandomAccessFile(DownTask.this.tempPath, "rwd", 1024 * 8);
                } else {
                    accessFile = new BufferedRandomAccessFile(DownTask.this.path, "rwd", 1024 * 8);
                }
                accessFile.seek(start);
                byte[] buffer = new byte[1024 * 8];
                int len = 0;

                //连接成功
                DownTask.this.state = DownloadState.CONNECTED;
                mDownloadStateListener.onStateChange(state, mProgressInfo);

                while ((len = bis.read(buffer)) != -1) {
                    accessFile.write(buffer, 0, len);
                    downloadSize += len;
                    //每隔一段时间更新一次进度
                    if (updateTime == 0) {
                        updateTime = System.currentTimeMillis();
                        DownloadStartDao.getInstance(DownloadManager.context).insert(url, downloadSize);
                        mProgressInfo.setDownloadSize(downloadSize);
                        DownTask.this.state = DownloadState.DOWNLOADING;
                        mDownloadStateListener.onStateChange(state, mProgressInfo);
                    } else {
                        long l = System.currentTimeMillis();
                        if ((l - updateTime) > downloadManager.updateInterval) {

                            DownloadStartDao.getInstance(DownloadManager.context).insert(url, downloadSize);
                            mProgressInfo.setDownloadSize(downloadSize);
                            DownTask.this.state = DownloadState.DOWNLOADING;
                            mDownloadStateListener.onStateChange(state, mProgressInfo);
                            updateTime = l;
                        } else if (downloadSize == contentLength) {
                            DownloadStartDao.getInstance(DownloadManager.context).insert(url, downloadSize);
                            mProgressInfo.setDownloadSize(downloadSize);
                            DownTask.this.state = DownloadState.DOWNLOADING;
                            mDownloadStateListener.onStateChange(state, mProgressInfo);
                            updateTime = l;
                        }
                    }


                    if (pause || cancel) {
                        isStart = false;
                        if (pause) {
                            DownTask.this.state = DownloadState.PAUSE;
                            mDownloadStateListener.onStateChange(state, mProgressInfo);
                        } else if (cancel) {
                            DownTask.this.state = DownloadState.CANCEL;
                            mDownloadStateListener.onStateChange(state, mProgressInfo);
                            DownloadStartDao.getInstance(DownloadManager.context).delete(url);
//                            new File(path).delete();

                            if (tempName) {
                                new File(tempPath).delete();
                            } else {
                                new File(path).delete();
                            }
                        }
                        return;
                    }
                }
                //下载完成
                if (tempName) {
                    new File(tempPath).renameTo(new File(path));
                }

//                pause = true;
                isStart = false;
                //下载完成移除任务
                downloadManager.downTasks.remove(url);
                DownTask.this.state = DownloadState.COMPLETE;
                mDownloadStateListener.onStateChange(state, mProgressInfo);

            } catch (Exception e) {
                e.printStackTrace();
                isStart = false;
                mDownloadStateListener.onError(e);
            } finally {
                try {
                    close(is);
                    close(bis);
                    close(accessFile);
                } catch (Exception e) {
                    mDownloadStateListener.onError(e);
                }
            }
        }
    }

    private HttpURLConnection getConnection(String url) throws Exception {
        HttpURLConnection connection = null;
        URL url_ = new URL(url);
        connection = (HttpURLConnection) url_.openConnection();
        connection.setConnectTimeout(downloadManager.timeOut);
        connection.setRequestProperty("Accept-Encoding", "identity");

        return connection;
    }

    private void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

}
