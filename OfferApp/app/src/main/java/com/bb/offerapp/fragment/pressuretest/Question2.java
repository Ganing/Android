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
public class Question2 extends Fragment{
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
        tv.setText("二、你的自行车与别人的车相撞，你不得不与对方约个时间解决这个问题。");
        rb1.setText("A 这件事引起的焦虑和不安使你失眠；");
        rb1.setTextSize(20);
        rb2.setTextSize(14);
        rb3.setTextSize(20);
        rb2.setText("B 这并非重要的事情，只是生活中发生的许多事情中的一件，你会在问题解决后，做点自己喜欢的事情，以便尽快忘掉那不愉快的事；");
        rb3.setText("C 开始时你不去管它，只要在解决问题的那一天到来时再想办法应付它；");
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
