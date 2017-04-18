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
public class Question4 extends Fragment{
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
        tv.setText("四、你由于某件生活中的小事和邻居发生了争执，却没能解决任何问题。");
        rb1.setText("A 回到家，你拼命喝酒，想轻松一下，忘掉这件事；");
        rb2.setText("B 准备到对方单位告他；");
        rb3.setText("C 通过散步或看一场电影来平息怒气；");
        rb3.setTextSize(20);
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

