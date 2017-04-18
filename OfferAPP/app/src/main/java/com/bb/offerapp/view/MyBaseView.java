package com.bb.offerapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by bb on 2017/4/17.
 *
 * //封装这个类
 */

public abstract class MyBaseView extends View {



    private MyTheread myTheread;
    private boolean running=true;

    public MyBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBaseView(Context context) {
        super(context);
    }


    //当view离开屏幕时候，将循环关闭
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        running=false;
    }


    @Override//禁止子类修改这个方法
    protected final void onDraw(Canvas canvas) {

        if(myTheread==null){
            myTheread=new MyTheread();
            myTheread.start();
        }else{
            drawSub(canvas);
        }

    }

    protected abstract void drawSub(Canvas canvas);


    protected abstract void logic();




    class MyTheread extends Thread{
        @Override
        public void run() {
            while(running){

                logic();
                //重新绘制加载onDraw
                postInvalidate();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
