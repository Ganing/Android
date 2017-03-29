package com.xxx.myapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by bb on 2017/3/29.
 */

public class MyProvider extends ContentProvider {



    private DBOpenHelper dbOpenHelper;


    private final static int TEST = 100;//匹配码

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ContentData.CONTENT_AUTHORITY;

        matcher.addURI(authority, ContentData.PATH_TEST, TEST);

        return matcher;
    }


    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        switch ( buildUriMatcher().match(uri)) {
            case TEST:
                cursor = db.query(ContentData.TestEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
                break;
        }

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Uri returnUri;
        long _id;
        switch ( buildUriMatcher().match(uri)) {
            case TEST:
                _id = db.insert(ContentData.TestEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ContentData.TestEntry.buildUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new android.database.SQLException("Unknown uri: " + uri);
        }
        return returnUri;
    }



    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }



}
