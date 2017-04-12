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
public class Question13 extends Fragment{
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
        tv.setText("十三、你最小的孩子离开家走入社会，这意味着家里只剩下你和你丈夫（或妻子）。");
        rb1.setText("A 与朋友谈论家中的这一变化，看他们是怎么应付这一变化所带来的各种不适应的状况；");
        rb2.setText("B 尽可能地帮助别人并为自己寻求新的兴趣爱好；");
        rb3.setText("C 想告诉孩子们，希望他们多在家里呆一段时间陪陪自己；");
        rb1.setTextSize(16);
        rb2.setTextSize(20);
        rb3.setTextSize(20);
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
