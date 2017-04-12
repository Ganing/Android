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
public class Question9 extends Fragment{
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
        tv.setText("九、你猜想你的房租或一些其它的月支付会增加。");
        rb1.setText("A 每天急于收信，以便从朋友那里早点确认上涨的信息，只有没信时才有所放松；");
        rb2.setText("B 决定不被这次涨价吓倒，你计划怎么样应付这种情况，如换房、采取节约措施等；");
        rb3.setText("C 你觉得每个人都处在同样的状态中，因此逃避现实，被动等待，认为自己总会应付得了；");
        rb1.setTextSize(18);
        rb2.setTextSize(18);
        rb3.setTextSize(18);
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