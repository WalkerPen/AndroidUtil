package com.pen.pdownload.bean;

import java.text.DecimalFormat;

/**
 * Created by Pen on 2018/1/12.
 */

public class ProgressInfo {

    private static DecimalFormat df = new DecimalFormat("#.0");
    private static DecimalFormat dfInt = new DecimalFormat("#");

    private String url;

    private int totalSize;

    private int downloadSize;

    private String formatTotalSize = "0";

    private String formatDownLoadSize = "0";

    private float progress;

    private int speed; //每秒下载速度

    private String formatSpeed = "0";//格式化的速度

    private long updateTime;

    public void clear() {
        speed = 0;
        formatSpeed = "0";
        updateTime = 0;
        setTotalSize(0);
        setDownloadSize(0);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
        formatTotalSize = format(this.totalSize);
    }

    public void setDownloadSize(int downloadSize) {
        long millis = System.currentTimeMillis();
        if(updateTime != 0 && downloadSize != totalSize) {
            speed = (int) ((downloadSize - this.downloadSize) / (millis - updateTime) * 1000);
            formatSpeed = format(this.speed);
        }
        this.downloadSize = downloadSize;
        formatDownLoadSize = format(this.downloadSize);
        progress = downloadSize * 1f / totalSize;

        updateTime = millis;
    }

    public String getUrl() {
        return url;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getDownloadSize() {
        return downloadSize;
    }

    public String getFormatTotalSize() {
        return formatTotalSize;
    }

    public String getFormatDownLoadSize() {
        return formatDownLoadSize;
    }

    public float getProgress() {
        return progress;
    }

    public int getSpeed() {
        return speed;
    }

    public String getFormatSpeed() {
        return formatSpeed;
    }

    private String format(int size) {
        String format = "";
        double d = 0;
        if (size < 1024) {
            format = size + "B";
        } else if (size < 1024 * 1024) {
            d = Double.parseDouble(df.format(size / 1024.0));
            if(d % 1 == 0) {
                format = dfInt.format(d) + "K";
            } else {
                format = d + "K";
            }
        } else if(size < 1024 * 1024 * 1024) {
            d = Double.parseDouble(df.format(size / 1024.0 / 1024.0));
            if(d % 1 == 0) {
                format = dfInt.format(d) + "M";
            } else {
                format = d + "M";
            }
        } else if(size < 1024 * 1024 * 1024 * 1024l) {
            d = Double.parseDouble(df.format(size / 1024.0 / 1024.0 / 1024.0));
            if(d % 1 == 0) {
                format = dfInt.format(d) + "G";
            } else {
                format = d + "G";
            }
        }
        return format;
    }
}
