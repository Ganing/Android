package com.bb.offerapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by bb on 2017/4/12.
 */
public class Pic extends AppCompatActivity {
    private final String PATH = "http://wallpaperswide.com/download/hot_air_balloons_in_the_air-wallpaper-2560x1024.jpg";
    private final String PATH2 = "http://wallpaperswide.com/download/moraine_lake_and_the_valley_of_the_ten_peaks-wallpaper-2560x1024.jpg";
    private final String PATH3 = "http://wallpaperswide.com/download/iceland_waterfall_2-wallpaper-2560x1024.jpg";
    private Button button;
    private ImageView imageView, imageView2, imageView3;
    TextView textView, textView2, textView3;
    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                byte[] data = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView.setImageBitmap(bitmap);
                textView.setText(" ");

            }
            if (msg.what == 2) {
                byte[] data = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView2.setImageBitmap(bitmap);
                textView2.setText(" ");

            }
            if (msg.what == 3) {
                byte[] data = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView3.setImageBitmap(bitmap);
                textView3.setText("");

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);
        imageView = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        textView = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);
        textView3 = (TextView) findViewById(R.id.tv3);

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picDown();
                textView.setText("正在加载。。。");
                textView2.setText("正在加载。。。");
                textView3.setText("正在加载。。。");
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
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }

                HttpClient httpClient2 = new DefaultHttpClient();
                HttpGet httpGet2 = new HttpGet(PATH2);
                try {
                    HttpResponse httpResponse2 = httpClient2.execute(httpGet2);
                    if (httpResponse2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        byte[] data = EntityUtils.toByteArray(httpResponse2
                                .getEntity());
                        Message message = Message.obtain();
                        message.obj = data;
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }

                HttpClient httpClient3 = new DefaultHttpClient();
                HttpGet httpGet3 = new HttpGet(PATH3);
                try {
                    HttpResponse httpResponse3 = httpClient3.execute(httpGet3);
                    if (httpResponse3.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        byte[] data = EntityUtils.toByteArray(httpResponse3
                                .getEntity());
                        Message message = Message.obtain();
                        message.obj = data;
                        message.what = 3;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }

            }
        }).start();
    }
}

