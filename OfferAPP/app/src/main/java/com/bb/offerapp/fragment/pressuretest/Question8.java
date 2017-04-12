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
public class Question8 extends Fragment{
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
        tv.setText("八、终于有一天你的能力被人们认识并被赋予一项重要工作。");
        rb1.setText("A 你考虑放弃这次机会，因为工作量太大；");
        rb2.setText("B 你开始怀疑自己能否承担这个重任；");
        rb3.setText("C 分析这项工作对你的要求，并为从事这一工作做各方面的准备；");
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