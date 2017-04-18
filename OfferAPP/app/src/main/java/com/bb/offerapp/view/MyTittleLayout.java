package com.bb.offerapp.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bb.offerapp.R;

/**
 * Created by bb on 2017/4/18.
 */

//自定义布局，并写逻辑 方便以后复用逻辑。    相比include引入布局，不用每次都写逻辑。
public class MyTittleLayout extends LinearLayout{
    private Button bt1,bt2;
    public MyTittleLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mytittle,this);

        bt1= (Button) findViewById(R.id.tittle_back);
        bt2= (Button) findViewById(R.id.tittle_edit);
        bt1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭当前
                ((Activity)getContext()).finish();
            }
        });

        bt2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了编辑", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
