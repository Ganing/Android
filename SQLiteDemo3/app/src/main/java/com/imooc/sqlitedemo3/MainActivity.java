package com.imooc.sqlitedemo3;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
//	SQLiteOpenHelper:
//	SQLiteOpenHelper帮助类，用于对数据库进行创建和升级。
//	实现两个方法：onCreate()方法用来实现创建数据库、onUpgrade()用来实现升级数据库
//
//	getWritableDatabase()和getReadableDatabase()方法都可以获取一个用于操作数据库的SQLiteDatabase实例。
//	其中getReadableDatabase()方法则是先以读写方式打开数据库，如果数据库的磁盘空间满了，就会打开失败，当打开失败后
//	会继续尝试以只读方式打开数据库。如果该问题成功解决，则只读数据库对象就会关闭，然后返回一个可读写的数据库对象。
//	getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，
//	使用的是getWritableDatabase() 方法就会出错。

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DBOpenHelper helper =	new DBOpenHelper(MainActivity.this, "stu.db");
//		helper.getReadableDatabase();//获取一个只读的数据库 只能查询 不能写入 不能更新
		SQLiteDatabase db = helper.getWritableDatabase();
//		db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		Cursor c = db.rawQuery("select * from stutb", null);
		if (c!=null) {
			String [] cols = c.getColumnNames();
			while (c.moveToNext()) {
				for (String ColumnName : cols) {
					Log.i("info ", ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));
				}
			}
			c.close();
		}
		db.close();
	}


}
