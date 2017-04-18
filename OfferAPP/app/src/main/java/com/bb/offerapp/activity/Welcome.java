package com.bb.offerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.bb.offerapp.R;


/**
 * Created by bb on 2017/3/15.
 */

public class Welcome extends AppCompatActivity {
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.welcome, null);
        setContentView(view);
        handler = new Handler();
        //打印主线程
//        Log.i("main", Thread.currentThread().getId() + "");

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    /**
     * 跳转到...
     */
    private void redirectTo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //打印子线程
//                Log.i("new", Thread.currentThread().getId() + "");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Welcome.this, OfferAppMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);


            }
        }).start();



}

}

