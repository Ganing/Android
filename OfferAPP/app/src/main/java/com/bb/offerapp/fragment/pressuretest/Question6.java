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
public class Question6 extends Fragment{
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
        tv.setText("六、一个你所爱的亲密朋友准备与别人结婚了，对你来说这是个巨大的不幸。");
        rb1.setText("A 你逃避现实，使用权自己相信这不可能发生，因此没必要担心，于是仍然乐观地抱希望；");
        rb2.setText("B 决定不去担忧，因为还有时间去改变这个“事实”；");
        rb3.setText("C 决定向你所爱的人提出你的观点，表明你的态度，严肃地向她（他）说明不该这样的理由；");
        rb1.setTextSize(15);
        rb2.setTextSize(17);
        rb3.setTextSize(15);
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
