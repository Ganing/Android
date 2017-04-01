package com.bb.sharedpr;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userName,userPass;
    private CheckBox checkBox;
    private Button ok,cancel;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化SharedPreferences 及相关组建。
        init();


        //3、取出userInfo中的数据。
        String name00=pref.getString("userName",null);
        if (name00==null) {
            checkBox.setChecked(false);
        }else {
            checkBox.setChecked(true);
            //4、将取到的用户名赋给用户名编辑框。
            userName.setText(name00);
        }

    }

    private void init() {
        userName = (EditText) findViewById(R.id.userName);
        userPass = (EditText) findViewById(R.id.userPass);
        checkBox = (CheckBox) findViewById(R.id.check);
        ok = (Button) findViewById(R.id.join_btn);
        cancel = (Button) findViewById(R.id.cancel_btn);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        //1、获取SharedPreferences对象，并把文件名设为"userInfo"。
        pref =getSharedPreferences("userInfo", MODE_WORLD_READABLE);
        //2、获取SharedPreferences内部接口Editor用来编辑userInfo。
        editor = pref.edit();
    }


    @Override
    public void onClick(View v) {
        //2.1：获取用户输入的用户名密码信息。
        String name = userName.getText().toString();
        String pass = userPass.getText().toString();
        switch (v.getId()) {
            case R.id.join_btn:
                if ("admin".equals(name)&&"123456".equals(pass)){
                    if(checkBox.isChecked()){
                        //2.2.1：判断成功登入并对"保存用户名"打勾之后，
                        //将用户名的键值对添加到文件名为"userInfo"文件中并提交。
                        editor.putString("userName",name);
                        editor.commit();
                    }else{
                        //2.2.2若没打勾，则清空并提交。
                        editor.remove("userName");
                        editor.commit();
                    }
                    Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
                }
                    break;
            case R.id.cancel_btn:
                userName.setText(null);
                userPass.setText(null);
                break;
        }
    }
}
