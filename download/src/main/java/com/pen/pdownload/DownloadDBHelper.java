package com.pen.pdownload;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pen on 2018/1/11.
 */

public class DownloadDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "p_download.db";

    public static final String TABLE_DOWNLOAD_START = "download_start";

    public DownloadDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table %s(\n" +
                "id integer primary key autoincrement not null, \n" +
                "url varchar(255) not null unique, \n" +
                "start int not null default(0))";
        db.execSQL(String.format(s, TABLE_DOWNLOAD_START));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
