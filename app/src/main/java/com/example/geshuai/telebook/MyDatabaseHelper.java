package com.example.geshuai.telebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GeShuai on 2017/10/29.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_INFORMATION = "create table information ("
            +"name text primary key,"
            +"number text,"
            +"relative text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INFORMATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
