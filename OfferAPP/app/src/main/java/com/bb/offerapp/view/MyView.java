package com.bb.offerapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.bb.offerapp.R;

import java.util.Random;

/**
 * Created by bb on 2017/4/17.
 */

public class MyView extends MyBaseView{

    private Paint paint =new Paint();
    private float rx=0;//开始滚动位置
    private float sweepAngle =0;//开始角度
    private RectF rectF =new RectF(300,60,400,160);
    Random random =new Random();

//    在xml中定义样式属性

    private String text="默认内容";


    public MyView(Context context) {
        super(context);
    }




    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        text = ta.getString(R.styleable.MyView_text);

        ta.recycle();
    }



    @Override
    protected void drawSub(Canvas canvas) {
        paint.setTextSize(30);
        //画文字
        canvas.drawText(text,rx,30,paint);
        //画圆形
        canvas.drawArc(rectF,0,sweepAngle,true,paint);
    }


    @Override
    protected void logic()
    {
        rx+=3;
        if(rx>getWidth()){
            rx=0-paint.measureText("滚动字体");
        }

        sweepAngle+=3;
        if(sweepAngle>360){
            sweepAngle=0;
        }

        int r= random.nextInt(256);
        int g= random.nextInt(256);
        int b= random.nextInt(256);

        paint.setARGB(255,r,g,b);

    }





}
