package com.pen.pdownload;

/**
 * Created by Pen on 2018/1/11.
 */

public enum DownloadState {
    /**等待*/
    WAIT,
    /**请求下载*/
    REQUEST,
    /**连接成功*/
    CONNECTED,
    /**下载中*/
    DOWNLOADING,
    /**暂停*/
    PAUSE,
    /**取消*/
    CANCEL,
    /**出错*/
    ERROR,
    /**下载完成*/
    COMPLETE
}
