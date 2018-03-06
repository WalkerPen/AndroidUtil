package com.pen.pdownload;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Pen on 2018/1/11.
 */

public class DownloadStartDao {
    private static volatile DownloadStartDao instance;

    private final DownloadDBHelper mDownloadDBHelper;
    private final SQLiteDatabase mDb;

    private DownloadStartDao(Context context) {
        mDownloadDBHelper = new DownloadDBHelper(context);
        mDb = mDownloadDBHelper.getWritableDatabase();
    }

    public static DownloadStartDao getInstance(Context context) {
        if (instance == null) {
            synchronized (DownloadStartDao.class) {
                if (instance == null) {
                    instance = new DownloadStartDao(context);
                }
            }
        }
        return instance;
    }

    public void insert(String url, int start) {
        if (idExist(url)) {
            String update = "update %s set start = %s where url = '%s'";
            mDb.execSQL(String.format(update, DownloadDBHelper.TABLE_DOWNLOAD_START, start, url));
        } else {
            String insert = "INSERT INTO %s (url, start) " +
                    "VALUES ('%s', %s)";
            mDb.execSQL(String.format(insert, DownloadDBHelper.TABLE_DOWNLOAD_START, url, start));
        }
    }

    public int getStart(String url) {
        String select = "select * from %s where url = '%s'";
        Cursor cursor = mDb.rawQuery(String.format(select, DownloadDBHelper.TABLE_DOWNLOAD_START, url), null);
        if (cursor.moveToNext()) {
            int start = cursor.getInt(cursor.getColumnIndex("start"));
            cursor.close();
            return start;
        }
        cursor.close();
        return 0;
    }

    public void delete(String url) {
        String delete = "delete from %s where url = '%s'";
        mDb.execSQL(String.format(delete, DownloadDBHelper.TABLE_DOWNLOAD_START, url));
    }

    private boolean idExist(String url) {
        String select = "select * from %s where url = '%s'";
        Cursor cursor = mDb.rawQuery(String.format(select, DownloadDBHelper.TABLE_DOWNLOAD_START, url), null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}
