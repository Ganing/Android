package com.bb.offerapp.fragment.pressuretest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.util.TotalCount;

/**
 * Created by bb on 2016/11/27.
 */
public class Result extends Fragment{
    private TextView tv_res;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.press_result_1, container, false);
        tv_res= (TextView) v.findViewById(R.id.tv_result);
        TotalCount tc = TotalCount.getInstance();
        int total = tc.getCount();
        if (total < 21) {
            tv_res.setText("分数：" + total + "。你很会处理问题，心理压力不大，或许还可教其他人如何平静下来.");
        } else if (total >= 21 && total < 31) {
            tv_res.setText("分数：" + total + "。心理压力稍大，日常生活中应该注意调节心态。");
        } else {
            tv_res.setText("分数：" + total + "。压力过大，应该采取适当的措施进行减压。");
        }
        return v;
    }


}
