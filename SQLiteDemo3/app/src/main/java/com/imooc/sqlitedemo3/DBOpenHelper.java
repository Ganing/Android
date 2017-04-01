package com.imooc.sqlitedemo3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{
//	SQLiteOpenHelper:
//	SQLiteOpenHelper帮助类，用于对数据库进行创建和升级。
//	实现两个方法：onCreate()方法用来实现创建数据库、onUpgrade()用来实现升级数据库
//
//	getWritableDatabase()和getReadableDatabase()方法都可以获取一个用于操作数据库的SQLiteDatabase实例。
//	其中getReadableDatabase()方法则是先以读写方式打开数据库，如果数据库的磁盘空间满了，就会打开失败，当打开失败后
//	会继续尝试以只读方式打开数据库。如果该问题成功解决，则只读数据库对象就会关闭，然后返回一个可读写的数据库对象。
//	getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，
//	使用的是getWritableDatabase() 方法就会出错。
//
	
	public DBOpenHelper(Context context, String name) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override //首次创建数据库的时候执行  里面一般写建库 建表
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,name text not null,sex text not null,age integer not null)");
		db.execSQL("insert into stutb(name,sex,age)values('aa','man',18)");
		db.execSQL("insert into stutb(name,sex,age)values('bb','women',20)");
	}

	@Override // 当数据库版本发生变化时 执行此方法
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
