package com.bb.httpurlconnectionlistenerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String response = (String) msg.obj;
                    textView.setText(response);
                    break;

                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= (Button) findViewById(R.id.btn);
        textView= (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用该HttpTool发起GET请求
                String url = "http://www.jianshu.com";
                HttpTool.sendRequest(url,new HttpCallbackListener(){
                    @Override
                    public void onFinish(String response) {
                        // ...省略对返回结果的处理代码
                        Message message = new Message();
                        message.what = 0;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        // ...省略请求失败的处理代码
                    }
                });

        }
        });




    }
}
