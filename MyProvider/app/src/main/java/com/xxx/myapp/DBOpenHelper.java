package com.xxx.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bb on 2017/3/29.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    //数据库版本
    private static final int DATABASE_VERSION = 1;
    //数据库名称
    private static final String DATABASE_NAME = "people.db";

    //构造方法
     public DBOpenHelper(Context context) {
     super(context, DATABASE_NAME,null, DATABASE_VERSION);
     }

    //通过sql语句建表并插入数据
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("create table");

        final String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE " + ContentData.TestEntry.TABLE_NAME + "( "
                + "_id integer primary key autoincrement,"
                + ContentData.TestEntry.COLUMN_NAME + " TEXT NOT NULL,"
                + ContentData.TestEntry.COLUMN_SEX + " TEXT NOT NULL,"
                + ContentData.TestEntry.COLUMN_AGE + " INTEGER NOT NULL );";
        db.execSQL(SQL_CREATE_CONTACT_TABLE);

        db.execSQL("insert into people(name,sex,age)values('张三','女',18)");
        db.execSQL("insert into people(name,sex,age)values('张四','男',20)");
        db.execSQL("insert into people(name,sex,age)values('张五','女',19)");
        db.execSQL("insert into people(name,sex,age)values('张六','男',22)");
    }

    //数据库升级的时候会调用的代码
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContentData.TestEntry.TABLE_NAME);
        onCreate(db);

    }

}