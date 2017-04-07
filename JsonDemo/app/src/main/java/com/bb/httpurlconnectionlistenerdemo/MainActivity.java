package com.bb.httpurlconnectionlistenerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button button, button2;
    private TextView textView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String response = (String) msg.obj;
                    textView.setText(response);
                    break;
                case 1:
                    String response2 = (String) msg.obj;
                    textView.setText(response2);
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


        button = (Button) findViewById(R.id.btn);
        button2 = (Button) findViewById(R.id.btn2);
        textView = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用该HttpTool发起GET请求
                String url = "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ";
                HttpTool.sendRequest(url, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        // ...省略对返回结果的处理代码
                        Message message = new Message();
                        message.what = 0;
                        message.obj = parseItemJSONWithJSONObject(response);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        // ...省略请求失败的处理代码
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用该HttpTool发起GET请求
                String url = "http://www.kuaidi100.com/query?type=ems&postid=9890561954327";
                HttpTool.sendRequest(url, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        // ...省略对返回结果的处理代码
                        Message message = new Message();
                        message.what = 1;
                        message.obj = parseJSONWithJSONObject(response);
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

    //单条数据
    private String parseItemJSONWithJSONObject(String jsonData) {
        String status=null;
        String message=null;
        try {
            //第一步：将从网络字符串jsonData字符串装入JSONObject
            JSONObject jsonObject = new JSONObject(jsonData);
            //第二步：因为单条数据，所以用jsonObject.getString方法直接取出对应键值
            status = jsonObject.getString("status");
            message = jsonObject.getString("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "status: "+ status+"\n"+"message: " + message;
    }


    //多条数据
    private String parseJSONWithJSONObject(String jsonData) {
        StringBuffer sb =new StringBuffer();
        try {
            //第一步：将从网络字符串jsonData字符串装入JSONObject，即JSONObject
            JSONObject jsonObject = new JSONObject(jsonData);
            //第二步：因为多条数据，所以将"取出来的、要遍历的"字段装入JSONArray（这里要遍历data字段）
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            //第三步：循环遍历，依次取出JSONObject对象
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String time = jsonObject2.getString("time");
                String ftime = jsonObject2.getString("ftime");
                String context = jsonObject2.getString("context");
                sb.append("time: " + time+"  "+"ftime: " + ftime+"\n"+"context: " + context+"\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
