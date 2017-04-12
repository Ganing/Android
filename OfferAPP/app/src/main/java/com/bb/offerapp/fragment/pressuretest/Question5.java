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
public class Question5 extends Fragment{
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
        tv.setText("五、当今日常生活中的压力使你和你妻子（或丈夫）经常发生口角。");
        rb1.setText("A 每当这个时候，你尽力放松自己，保持沉默，不去争执；");
        rb2.setText("B 你和朋友谈论这事，使你的观点和感情得到理解；");
        rb3.setText("C 寻求机会，心平气和地与自己的妻子（或丈夫）谈心，看如何摆脱由于日常生活压力而引起的争吵；");
        rb3.setTextSize(17);
        rb1.setTextSize(20);
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