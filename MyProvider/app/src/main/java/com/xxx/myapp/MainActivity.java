package com.xxx.myapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getContentResolver().query(ContentData.TestEntry.CONTENT_URI,new String[] { ContentData.TestEntry.COLUMN_NAME,
                ContentData.TestEntry.COLUMN_SEX }, null, null, null);
    }
}
