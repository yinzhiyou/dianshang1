package com.bwie.yinzhiyou20181123.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelpter extends SQLiteOpenHelper {
    private static final int version = 1;
    private static final String name ="USERS.db" ;

    public DBHelpter(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table adminss(id Integer primary key autoincrement," +
                "name text," +
                "title text," +
                "thumbnail_pic_s text," +
                "thumbnail_pic_s02 text," +
                "thumbnail_pic_s03 text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
