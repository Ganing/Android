package com.bb.sharedp_read;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);

        Context otherAppContent = null;
        try {
            otherAppContent = createPackageContext("com.bb.sharedpr",CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        SharedPreferences read = otherAppContent.getSharedPreferences("userInfo", MODE_WORLD_READABLE);

        String name=read.getString("userName","");


        textView.setText(name);

    }
}
