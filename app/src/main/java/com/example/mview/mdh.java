package com.example.mview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class mdh extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, " + "name text, " + "startTime text, " + "endTime text, " + "type text,"+" state integer)";
    private Context mContext;
    public static final String CREATE_BOOK1 = "create table Book ("
            + "id integer primary key autoincrement, " + "name text, " + "startTime text, " + "endTime text, " + "type text,"+" state integer)";

    public mdh(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context; }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
//        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}