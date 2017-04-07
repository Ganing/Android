package com.bb.httphandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    MyHandler handler = null;
    private Button mButton, mButton2;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btn);
        mButton2 = (Button) findViewById(R.id.btn2);
        mTextView = (TextView) findViewById(R.id.tv);
        handler = new MyHandler();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWithHttpURLConnectionGET();
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWithHttpURLConnectionPOST();
            }
        });
    }

    private void connectWithHttpURLConnectionGET() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                HttpURLConnection connection = null;
                try {
                    // 调用URL对象的openConnection方法获取HttpURLConnection的实例
                    URL url = new URL("http://www.jianshu.com/u/19513cee1eb8");
                    connection = (HttpURLConnection) url.openConnection();
                    // 设置请求方式，GET或POST
                    connection.setRequestMethod("GET");
                    // 设置连接超时、读取超时的时间，单位为毫秒（ms）
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    // 设置是否使用缓存，默认是true
                    connection.setUseCaches(true);
                    //设置请求头里面的属性
                    //connection.setRequestProperty();
                    // 开始连接
                    Log.i("HttpURLConnection.GET", "开始连接");
                    connection.connect();
                    if (connection.getResponseCode() == 200) {
                        Log.i("HttpURLConnection.GET", "请求成功");
                        InputStream in = connection.getInputStream();
                        // 使用BufferedReader对象读取返回的数据流
                        // 按行读取，存储在StringBuider对象response中
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        // 此处省略处理数据的代码,直接将返回的结果消息发送给UI线程列队
                        Bundle bundle = new Bundle();
                        bundle.putString("data", String.valueOf(response));
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    } else {
                        Log.i("HttpURLConnection.GET", "请求失败");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        // 结束后，关闭连接
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    private void connectWithHttpURLConnectionPOST() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                String path = "https://reg.163.com/logins.jsp";
                // 请求的参数转换为byte数组
                String params = null;
                HttpURLConnection urlConn = null;
                try {
                    params = "id=" + URLEncoder.encode("helloworld", "UTF-8")
                            + "&pwd=" + URLEncoder.encode("android", "UTF-8");

                    byte[] postData = params.getBytes();
                    // 新建一个URL对象
                    URL url = new URL(path);
                    // 打开一个HttpURLConnection连接
                    urlConn = (HttpURLConnection) url.openConnection();
                    // 设置连接超时时间
                    urlConn.setConnectTimeout(8 * 1000);
                    // Post请求必须设置允许输出
                    urlConn.setDoOutput(true);
                    // Post请求不能使用缓存
                    urlConn.setUseCaches(false);
                    // 设置为Post请求
                    urlConn.setRequestMethod("POST");
                    urlConn.setInstanceFollowRedirects(true);
                    // 配置请求Content-Type
                    urlConn.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencode");
                    // 开始连接
                    urlConn.connect();
                    Log.i("HttpURLConnection.POST", "开始连接");
                    // 发送请求参数
                    DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
                    dos.write(postData);
                    dos.flush();
                    dos.close();
                    // 判断请求是否成功
                    if (urlConn.getResponseCode() == 200) {
                        // 获取返回的数据
                        InputStream in = urlConn.getInputStream();
                        // 使用BufferedReader对象读取返回的数据流
                        // 按行读取，存储在StringBuider对象response中
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        Log.i("HttpURLConnection.POST", "请求成功");
                        // 此处省略处理数据的代码,直接将返回的结果消息发送给UI线程列队
                        Bundle bundle = new Bundle();
                        bundle.putString("data", String.valueOf(response));
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    } else {
                        Log.i("HttpURLConnection.POST", "请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConn != null) {
                        // 结束后，关闭连接
                        urlConn.disconnect();
                    }
                }
            }
        }).start();


    }

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
            mTextView.setText(msg.getData().getString("data"));
        }
    }
}

