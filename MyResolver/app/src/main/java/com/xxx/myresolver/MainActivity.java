package com.xxx.myresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //声明Uri常量
    private static final Uri CONTENT_URI = Uri.parse("content://com.xxx.MyApp.myprovider/people");
    TextView tv_id, tv_name, tv_sex, tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
    }

    public void query(View view) {
        //通过getContentResolver().query调用ContentProvider实现对数据库的查询
        tv_id.setText("");
        tv_name.setText("");
        tv_sex.setText("");
        tv_age.setText("");
        Cursor cursor = getContentResolver().query(CONTENT_URI, new String[]{"_id", "name", "sex", "age"
        }, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                tv_id.append("\n" + cursor.getString(cursor.getColumnIndex("_id")));
                tv_name.append("\n" + cursor.getString(cursor.getColumnIndex("name")));
                tv_sex.append("\n" + cursor.getString(cursor.getColumnIndex("sex")));
                tv_age.append("\n" + cursor.getString(cursor.getColumnIndex("age")));
            }
            cursor.close();
        }
    }

    public void add(View view) {
        //通过getContentResolver().insert调用ContentProvider实现对数据库的增加
        ContentValues values = new ContentValues();
        values.put("name", "新来的");
        values.put("sex", "男");
        values.put("age", "28");
        getContentResolver().insert(CONTENT_URI, values);
        query(findViewById(R.id.btn_query));

    }

}