package com.imooc.contentproviderdemo1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
//http://www.jianshu.com/p/f5ec75a9cfea
//1.自定义一个Provider 继承ContentProvider （ContentProvider去操作数据库）
//2.其他程序使用ContentResolver 对Provider进行操作
// （ContentResolver来进行对其的操作，这样岂不是更复杂了吗？其实不然，
//   大家要知道一台手机中可不是只有一个Provider内容，它可能安装了很多
//   含有Provider的应用，比如联系人应用，日历应用，字典应用等等。有如
//   此多的Provider，如果你开发一款应用要使用其中多个，如果让你去了解
//   每个ContentProvider的不同实现，岂不是要头都大了。所以Android为
//   我们提供了ContentResolver来统一管理与不同ContentProvider间的操作。）
public class MyContentProvider extends ContentProvider{


	//比如你实现了增和删  那么其他程序在访问这个provider的时候就只能有这两个功能
	@Override//根据Uri删除selection指定的条件所匹配的全部记录
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override//返回当前uri的MIME类型，如果该URI对应的数据可能包括多条记录
	//那么MIME类型字符串 就是以vnd.android.dir/开头
//	如果该URI对应的数据只有一条记录 该MIME类型字符串 就是以vnd.android.cursor.item/开头
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//根据Uri插入Values对应的数据
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//在ContetnProvider创建后被调用
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override//根据uri查询出selection指定的条件所匹配的全部记录，并且可以指定查询哪些列 以什么方式(order)排序
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//根据uri修改selection指定的条件所匹配的全部记录
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
