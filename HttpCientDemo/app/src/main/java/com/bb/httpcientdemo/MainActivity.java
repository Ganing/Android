package com.bb.httpcientdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int SHOW_RESPONSE = 0;

    private Button button_sendRequest,button_sendRequest2;
    private TextView textView_response;

    //新建Handler的对象，在这里接收Message，然后更新TextView控件的内容
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    textView_response.setText(response);
                    break;
                case 1:
                    Log.i("mm","handler");
                    String response2 = (String) msg.obj;
                    textView_response.setText(response2.substring(150,7000));
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
        textView_response = (TextView) findViewById(R.id.TextView1);
        button_sendRequest = (Button) findViewById(R.id.button1);
        button_sendRequest2 = (Button) findViewById(R.id.button2);
        button_sendRequest.setOnClickListener(new View.OnClickListener() {
            //点击按钮时，执行sendRequestWithHttpClient()方法里面的线程
            @Override
            public void onClick(View v) {
                sendRequestWithHttpClientGET();
            }
        });
        button_sendRequest2.setOnClickListener(new View.OnClickListener() {
            //点击按钮时，执行sendRequestWithHttpClient()方法里面的线程
            @Override
            public void onClick(View v) {
                sendRequestWithHttpClientPOST();
            }
        });

    }


    private void sendRequestWithHttpClientGET() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpCient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpGet httpGet = new HttpGet("http://www.baidu.com");
                try {
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");//将entity当中的数据转换为字符串
                        //在子线程中将Message对象发出去
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void sendRequestWithHttpClientPOST() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpClient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpPost httpPost= new HttpPost("https://reg.163.com/logins.jsp");
                // 使用NameValuePair（键值对）存放参数
                List<NameValuePair> data = new ArrayList<NameValuePair>();
                // 添加键值对
                data.add(new BasicNameValuePair("id", "helloworld"));
                data.add(new BasicNameValuePair("pwd", "android"));
                try {
                    // 使用setEntity方法传入编码后的参数
                    httpPost.setEntity(new UrlEncodedFormEntity(data, "utf-8"));
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        Log.i("mm","success");
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");//将entity当中的数据转换为字符串
                        //在子线程中将Message对象发出去
                        Message message = new Message();
                        message.what = 1;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }else{
                        Log.i("mm","fail");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




}

