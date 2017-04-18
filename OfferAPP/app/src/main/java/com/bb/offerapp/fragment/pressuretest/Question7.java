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
public class Question7 extends Fragment{
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
        tv.setText("七、每个人都承受物价上涨所带来的心理上和生活上的压力，你更担心食品价格上涨。");
        rb1.setText("A 尽管价格上涨，你仍拒绝改变饮食习惯，因此不得不花更多的钱；");
        rb2.setText("B 每看到物价上涨，你怒气会大增，但不管怎么样还要买，甚至拼命抢购，担心还会再涨；");
        rb3.setText("C 设法少花钱，制定出一个营养而又实惠的信食谱；");
        rb1.setTextSize(20);
        rb2.setTextSize(16);
        rb3.setTextSize(20);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TotalCount tc = TotalCount.getInstance();
                if (checkedId == R.id.item1) {
                    count = 3;
                    tc.addCount(count);
                }
                if (checkedId == R.id.item2) {
                    count = 2;
                    tc.addCount(count);
                }
                if (checkedId == R.id.item3) {
                    count = 1;
                    tc.addCount(count);
                }
            }
        });
        return v;
    }


}