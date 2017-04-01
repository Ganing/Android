package com.bb.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_name, tv_sex, tv_age;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        创建一个数据库并且打开，如果存在就打开
//        以防报错，第一个字段的添加 要加上"下划线"
//        在ddns 的file explorer 中的data／data／<包>中 创建这个数据库文件

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);

        init();
    }

    public void init() {
        db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists usertb (_id integer primary key autoincrement, name text not null , age integer not null , sex text not null )");
        db.execSQL("insert into usertb(name,sex,age) values('张三','女',18)");
        db.execSQL("insert into usertb(name,sex,age) values('李四','男',19)");
        db.execSQL("insert into usertb(name,sex,age) values('王五','女',22)");
        query(findViewById(R.id.query));
    }

    public void query(View view) {
        tv_id.setText("");
        tv_name.setText("");
        tv_sex.setText("");
        tv_age.setText("");
        Cursor cursor = db.rawQuery("select * from usertb", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                tv_id.append("\n" + cursor.getString(cursor.getColumnIndex("_id")));
                tv_name.append("\n" + cursor.getString(cursor.getColumnIndex("name")));
                tv_sex.append("\n" + cursor.getString(cursor.getColumnIndex("sex")));
                tv_age.append("\n" + cursor.getString(cursor.getColumnIndex("age")));
            }
            cursor.close();
        }
//        db.close();不要关闭，不然单独调用查询操作会空指针
    }

    public void add(View view) {

        ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据
        cv.put("name", "新来的");
        cv.put("sex", "女");
        cv.put("age", "18");
        db.insert("usertb", null, cv);//执行插入操作
//      使用直接执行语句添加
//      db.execSQL("insert into usertb(name,sex,age) values('新来的','女',18)");

        query(findViewById(R.id.query));


    }

    public void delete(View view) {
        String sql = "delete from usertb where name='新来的'";//删除操作的SQL语句
        db.execSQL(sql);//执行删除操作
        query(findViewById(R.id.query));
    }



    public void update(View view){
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("name","张三三");//添加要更改的字段及内容
        String whereClause = "name=?";//修改条件
        String[] whereArgs = {"张三"};//修改条件的参数
        db.update("usertb",cv,whereClause,whereArgs);//执行修改
        query(findViewById(R.id.query));
    }

}
