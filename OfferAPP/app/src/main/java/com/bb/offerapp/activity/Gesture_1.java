package com.bb.offerapp.activity;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

import com.bb.offerapp.R;

import java.util.ArrayList;
// 1. GestureOverlayView是一种用于手势输入的透明覆盖层，可覆盖在其他控件的上方，也可包含其他控件。它有三个监听器接口：
//    GestureOverlayView.onGestureListener（手势监听器）
//    GestureOverlayView.onGesturePerformedListener（手势执行监听器）
//    GestureOverlayView.OnGesturingListener（手势执行中监听器）
// 2. 使用Gestures Builder生成欲使用的手势文件加入到项目中（raw资源文件夹里）
//    然后在项目中创建一个GestureOverlayView，将其包裹到想要识别手势的控件上。

public class Gesture_1 extends Activity {
    GestureOverlayView gestureOverlayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture);
        gestureOverlayView= (GestureOverlayView) findViewById(R.id.ges);
//        1.找到并加载刚才预设的手势文件
//        2.匹配识别
//        3.设置监听器
        final GestureLibrary gestureLibrary = GestureLibraries.fromRawResource(Gesture_1.this, R.raw.gestures);
        gestureLibrary.load();
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
//                读出手势 返回一个手势集合 范性为Prediction预测性 Prediction类里面有两个变量：名字和相似度
                ArrayList<Prediction> myGesture = gestureLibrary.recognize(gesture);
//                获得手势 并设置它与用户手势匹配的相似度 0.0-10.0
                Prediction prediction = myGesture.get(0);
                if (prediction.score>2.0){
                    if (prediction.name.equals("exit")){
                        finish();//关闭
                    }
                    if (prediction.name.equals("next")){
                        Toast.makeText(Gesture_1.this, "下一首", Toast.LENGTH_SHORT).show();
                    }
                    if (prediction.name.equals("last")){
                        Toast.makeText(Gesture_1.this, "上一首", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Gesture_1.this, "没有该手势", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
