package com.bb.threadidtest;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button button;
    MyHandler myHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.start);
        //打印主线程id
        Log.d("ThreadId", "MainThreadId:"+Thread.currentThread().getId()+"");

        // 当创建一个新的Handler实例时，它会绑定到当前线程和消息的队列中，开始分发数据
        myHandler = new MyHandler();

        //开启子线程
        MyThread m = new MyThread();
        new Thread(m).start();
    }

    /**
     * 接收消息，处理消息 ，此Handler会与当前主线程一块运行
     * */

    class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        // 子类必须重写此方法，接收数据
        @Override
        public void handleMessage(Message msg) {
            Log.d("MyHandler", "handleMessage。。。。。。");
            super.handleMessage(msg);
            // 此处可以更新UI
            Bundle b = msg.getData();
            String color = b.getString("color");
            MainActivity.this.button.setText(color);

        }
    }

    class MyThread implements Runnable {
        public void run() {
            try {
                //6秒执行完毕
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("ThreadId","MainThreadId:"+Thread.currentThread().getId()+"");
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putString("color","我的名字");
            msg.setData(b);
            MainActivity.this.myHandler.sendMessage(msg); // 向Handler发送消息，更新UI

        }
    }

}
