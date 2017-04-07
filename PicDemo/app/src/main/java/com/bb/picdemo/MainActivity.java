package com.bb.picdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity extends AppCompatActivity {
    private final String PATH = "https://www.baidu.com/img/bdlogo.png";
    private Button button;
    private ImageView imageView;
    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == RESULT_OK) {
                byte[] data = (byte[]) msg.obj;
                //使用Bitmap类解析图片
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView1);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picDown();
            }
        });
    }

    private void picDown() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(PATH);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        byte[] data = EntityUtils.toByteArray(httpResponse
                                .getEntity());
                        Message message = Message.obtain();
                        message.obj = data;
                        message.what = RESULT_OK;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (httpClient != null
                            && httpClient.getConnectionManager() != null) {
                        httpClient.getConnectionManager().shutdown();
                    }
                }
            }
        }).start();
    }

}

