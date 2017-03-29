package com.xxx.myapp;

import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by bb on 2017/3/29.
 */

public class ContentData {

    //provider唯一标示信息
    protected static final String CONTENT_AUTHORITY = "com.xxx.MyApp.myprovider";

    //基础Uri
    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //操作表的名称
    protected static final String PATH_TEST = "people";

    //表中记录信息
    public static final class TestEntry implements BaseColumns {

        // 完整Uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEST).build();

        protected static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        protected static final String TABLE_NAME = "people";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_SEX = "sex";

        public static final String COLUMN_AGE = "age";
    }
}
