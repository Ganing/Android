package com.bb.offerapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bb.offerapp.R;

/**
 * Created by bb on 2017/3/16.
 */

public class BodyTest extends Activity {
    EditText txtusername;//用户名
    RadioGroup gendergroup;//性别
    EditText txtHeight;//身高
    EditText txtWeight;//体重

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body);

        txtusername=(EditText)findViewById(R.id.username);
        gendergroup=(RadioGroup)findViewById(R.id.gendergroup);
        txtHeight=(EditText)findViewById(R.id.txtHeight);
        txtWeight=(EditText)findViewById(R.id.txtWeight);
    }



	/*当鼠标按下时来这里执行*/

    public void cacl(View view)
    {
        //获取姓名
        String username=txtusername.getText().toString();
        //获取用户名中输入的姓名
        //Toast.makeText(this, username, Toast.LENGTH_LONG).show();

        //获取性别
        int id=gendergroup.getCheckedRadioButtonId();
        boolean isM=true;
        if (id==R.id.gender0) {
            isM=false;
        }
        //Toast.makeText(this, isM?"男":"女", Toast.LENGTH_LONG).show();

        //获取身高
        String heightstr=txtHeight.getText().toString();
        double height=Double.parseDouble(heightstr);

        //获取体重
        String weightstr=txtWeight.getText().toString();
        double weight=Double.parseDouble(weightstr);

        //根据输入的身高与体重计算身体质量
        double  sandWeight=0;
        if (isM) {
            sandWeight=height-105;
        } else {
            sandWeight=height-100;
        }

        //输出计算结果
        String result=username+(isM?"先生":"女士")
                +"您好！"+"\n"+"你的身高是："+height
                +"\n"+"体重是："+weight;
        if (sandWeight-weight<=10&&sandWeight-weight>=-10) {
            result+=",很棒你的身体完全符合标准";
        }
        if (sandWeight-weight<=-20) {
            result+=",你的身体接近崩溃了";
        }
        else if (sandWeight-weight<=-10) {
            result+="，你的身体有点胖，注意减肥哦";
        }
        if (sandWeight-weight>10) {
            result+=",你的身体有点瘦，注意营养";
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

}
