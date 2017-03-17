package com.bb.offerapp.fragment.pressuretest;
import com.bb.offerapp.util.TotalCount;
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
/**
 * Created by bb on 2016/11/27.
 */
public class Question11 extends Fragment{
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
        tv.setText("十一、每个节假日，家里总为去探望双方的父母而发生激烈争吵。");
        rb1.setText("A 你制定了一个严格的5年计划，要求在节假日轮流探望双方父母；");
        rb2.setText("B 决定在重要的假期与自己最喜欢的家庭成员一起度过，而在不太重要的假期邀请其他人；");
        rb3.setText("C 决定做最“公平”的事，根本不与家里老人、亲戚一起度假，这样麻烦最少；");
        rb1.setTextSize(20);
        rb2.setTextSize(18);
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
