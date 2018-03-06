package com.pen.pdownload;

import android.content.Context;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Pen on 2018/1/11.
 */

public class DownloadManager {

    private volatile static HashMap<String, DownloadManager> instances = new HashMap<>();

    ThreadPoolExecutor threadPool;

    int timeOut;

    int reconnectCount; //重连次数

    long updateInterval; //更新间隔

    HashMap<String, DownTask> downTasks;

    static Context context;

    private DownloadManager() {
    }

    public static void init(Context context) {
        DownloadManager.context = context.getApplicationContext();
    }

    public static void newManager(String tag, Builder builder) {
        DownloadManager downloadManager = new DownloadManager();
        downloadManager.timeOut = builder.timeOut;
        downloadManager.reconnectCount = builder.reconnectCount;
        downloadManager.updateInterval = builder.updateInterval;
        downloadManager.threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(builder.threadPoolCount);
        downloadManager.downTasks = new HashMap<>();
        instances.put(tag, downloadManager);
    }

    public static DownloadManager getInstance(String tag) {
        DownloadManager downloadManager = instances.get(tag);
        /*if (downloadManager == null) {
            synchronized (DownloadManager.class) {
                if (downloadManager == null) {
                    downloadManager = new DownloadManager();
                    downloadManager.downTasks = new HashMap<>();
                    instances.put(tag, downloadManager);
                }
            }
        }*/
        return downloadManager;
    }

    public void pauseAll() {
        for (DownTask downTask : downTasks.values()) {
            downTask.pause();
        }
    }

    public void cancelAll() {
        for (DownTask downTask : downTasks.values()) {
            downTask.cancel();
        }
    }

    public DownTask url(String url) {
        return DownTask.getInstance(this, url);
    }

    public static class Builder{

        private int timeOut = 2 * 1000;

        private int reconnectCount = 5; //重连次数

        private long updateInterval = 1000; //更新间隔

        private int threadPoolCount = 2;


        public Builder timeOut(int timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public Builder reconnectCount(int count) {
            this.reconnectCount = count;
            return this;
        }

        public Builder threadPool(int count) {
            this.threadPoolCount = count;
            return this;
        }

        public Builder updateInterval(int millisecond) {
            updateInterval = millisecond;
            return this;
        }
    }

}
