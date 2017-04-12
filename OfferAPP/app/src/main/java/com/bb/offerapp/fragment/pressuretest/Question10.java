package com.bb.offerapp.fragment.pressuretest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.util.TotalCount;
/**
 * Created by bb on 2016/11/27.
 */
public class Question10 extends Fragment{
    private int count = 0;
    private TextView tv;
    private RadioGroup rg;
    private RadioButton rb1, rb2, rb3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.press_select, container, false);
        tv= (TextView) v.findViewById(R.id.tv);
        rg = (RadioGroup) v.findViewById(R.id.rg);
        rb1= (RadioButton) v.findViewById(R.id.item1);
        rb2= (RadioButton) v.findViewById(R.id.item2);
        rb3= (RadioButton) v.findViewById(R.id.item3);
        tv.setText("十、你的一个非常亲近的人在一场事故中受了重伤，你从电话里得到了这个消息。");
        rb1.setText("A 努力压抑自己的感情，因为你还要把这一消息告诉其它朋友和亲戚；");
        rb2.setText("B 你挂断电话，哭起来，让悲痛尽情发泄出来，使心里好受一些；");
        rb3.setText("C 去医务室向医生要一些镇静剂，帮助你度过以后几小时；");
        rb1.setTextSize(20);
        rb2.setTextSize(20);
        rb3.setTextSize(17);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TotalCount tc = TotalCount.getInstance();
                if (checkedId == R.id.item1) {
                    count = 2;
                    tc.addCount(count);
                }
                if (checkedId == R.id.item2) {
                    count = 1;
                    tc.addCount(count);
                }
                if (checkedId == R.id.item3) {
                    count = 3;
                    tc.addCount(count);
                }
            }
        });
        return v;
    }


}