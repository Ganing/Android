package com.bb.offerapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bb.offerapp.R;
import com.bb.offerapp.fragment.pressuretest.Question;

public class PressTest extends AppCompatActivity {
    private Button mbt;
    private AlertDialog.Builder mbuilder;
    private Context mContext =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.press_main);
        init();
    }
    private void init(){
        mbt= (Button) findViewById(R.id.btn_home);
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbuilder.show();
            }
        });
        mbuilder = new AlertDialog.Builder(mContext);
        mbuilder.setTitle("心理压力测试题");
        mbuilder.setMessage("你有心理压力吗，你想知道你在生活中处理心理压力的能力吗?在下面的测验中找出最接近你实际生活的一种情况,如果没有经历过这类事情,可选择最接近你的想法的一种。");
        mbuilder.setIcon(R.mipmap.icon);
        mbuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "请点击确定开始测试", Toast.LENGTH_SHORT).show();
            }
        });

        // 设定确定按钮
        mbuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =new Intent(mContext,Question.class);
                startActivity(intent);

                finish();
            }
        });
    }
}
