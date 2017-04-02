package com.bb.handlerdemo;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    //Handler安排 Runnable 在某个主线程中某个地方执行

    Handler handler = new Handler();
    Runnable thread = new Runnable() {

        @Override
        public void run() {
            System.out.println("HandlerThread:" + Thread.currentThread().getId());

        }
    };
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(thread);
            }
        });

        System.out.println("Activity Thread:" + Thread.currentThread().getId());
    }

}