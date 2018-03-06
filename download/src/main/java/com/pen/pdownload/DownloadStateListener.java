package com.pen.pdownload;

import com.pen.pdownload.bean.ProgressInfo;

/**
 * Created by Pen on 2018/1/12.
 */

public interface DownloadStateListener {
    void onStateChange(DownloadState state, ProgressInfo progressInfo);

    void onError(Throwable throwable);
}
