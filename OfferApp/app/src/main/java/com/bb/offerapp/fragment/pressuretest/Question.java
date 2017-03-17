package com.bb.offerapp.fragment.pressuretest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bb.offerapp.R;
import com.bb.offerapp.activity.PressTest;
import com.bb.offerapp.util.TotalCount;

/**
 * Created by bb on 2016/11/27.
 */
public class Question extends AppCompatActivity implements View.OnClickListener {
    private Fragment fr01, fr02, fr03, fr04, fr05, fr06, fr07, fr08, fr09, fr10, fr11, fr12, fr13,fr14;
    private Button mnext;
    private Context mContext =this;
    private int count1=0;
    private int flag = 2;
    private boolean canReturn = false;
    private FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.press_question);
        FragmentTransaction ft = fm.beginTransaction();
        fr01 = new Question1();
        ft.add(R.id.frament_question, fr01);
        ft.commit();
        mnext = (Button) findViewById(R.id.question_next);
        mnext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 2) {
            fr02 = new Question2();
            ft.replace(R.id.frament_question, fr02);
        } else if (flag == 3) {
            fr03 = new Question3();
            ft.replace(R.id.frament_question, fr03);
        } else if (flag == 4) {
            fr04 = new Question4();
            ft.replace(R.id.frament_question, fr04);
        } else if (flag == 5) {
            fr05 = new Question5();
            ft.replace(R.id.frament_question, fr05);
        } else if (flag == 6) {
            fr06 = new Question6();
            ft.replace(R.id.frament_question, fr06);
        } else if (flag == 7) {
            fr07 = new Question7();
            ft.replace(R.id.frament_question, fr07);
        } else if (flag == 8) {
            fr08 = new Question8();
            ft.replace(R.id.frament_question, fr08);
        } else if (flag == 9) {
            fr09 = new Question9();
            ft.replace(R.id.frament_question, fr09);
        } else if (flag == 10) {
            fr10 = new Question10();
            ft.replace(R.id.frament_question, fr10);
        } else if (flag == 11) {
            fr11 = new Question11();
            ft.replace(R.id.frament_question, fr11);
        }else if (flag == 12) {
            fr12 = new Question12();
            ft.replace(R.id.frament_question, fr12);
        }else if (flag == 13) {
            mnext.setText("提交！");
            fr13 = new Question13();
            ft.replace(R.id.frament_question, fr13);
        }
        else if (flag == 14) {
            mnext.setText("关闭！");
            fr14 = new Result();
            ft.replace(R.id.frament_question, fr14);

        } else if (flag == 15) {
            TotalCount tc = TotalCount.getInstance();
                tc.setmCount(0);
            finish();


        }

        ft.commit();
        flag = flag + 1;

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(mContext, "请完成所有题目!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
