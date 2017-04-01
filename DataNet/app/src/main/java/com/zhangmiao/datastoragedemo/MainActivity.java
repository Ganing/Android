package com.zhangmiao.datastoragedemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private NetworkDBManager mNetworkDBManager;

    private TextView mTableInfo;
    private EditText et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AVOSCloud.initialize(this, "yMNUazdBt872mNtC9aSakjYy-gzGzoHsz", "d4vw3VYdMCjLpsXRhHTBRutC");

        mNetworkDBManager = new NetworkDBManager();

        et1= (EditText) findViewById(R.id.name);
        et2= (EditText) findViewById(R.id.age);
        et3= (EditText) findViewById(R.id.sex);

        Button networkGet = (Button) findViewById(R.id.network_get);
        Button networkPut = (Button) findViewById(R.id.network_put);

        mTableInfo = (TextView) findViewById(R.id.table_info);

        networkGet.setOnClickListener(this);
        networkPut.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.network_put:
                Person person3 = new Person(et1.getText().toString(), Integer.parseInt(et2.getText().toString()), et3.getText().toString());
                mNetworkDBManager.putData(person3);
                Toast.makeText(this,"上传成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.network_get:
                mNetworkDBManager.getData(mTableInfo);
                break;
            default:
                Log.v("MainActivity", "default");
                break;
        }
    }


}
