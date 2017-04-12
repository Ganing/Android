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
public class Question3 extends Fragment{
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
        tv.setText("三、你的家具或电器由于水管破裂被损坏了，而且发现你的财产保险不能完全弥补损失。");
        rb1.setText("A 你很失望，痛苦地抱怨保险公司；");
        rb2.setText("B 开始自己修复家具；");
        rb3.setText("C 考虑撤销保险，并向有关事务机关投诉；");
        rb3.setTextSize(20);
        rb1.setTextSize(23);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TotalCount tc = TotalCount.getInstance();
                if (checkedId == R.id.item1) {
                    count = 3;
                    tc.addCount(count);
                }
                if (checkedId == R.id.item2) {
                    count = 1;
                    tc.addCount(count);
                }
                if (checkedId == R.id.item3) {
                    count = 2;
                    tc.addCount(count);
                }
            }
        });
        return v;
    }


}
