package com.bb.offerapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bb.offerapp.R;
import com.bb.offerapp.adapter.MsgAdapter;
import com.bb.offerapp.option.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bb on 2017/3/16.
 */

public class MsgActivity extends Activity {

    private List<Msg> msgList=new ArrayList<Msg>();
    private ListView list_view;
    private EditText input_text;
    private Button send;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.msg);

        initData();
        list_view = (ListView) findViewById(R.id.list_view);
        input_text = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        adapter = new MsgAdapter(this,R.layout.msg_item, msgList);
        list_view.setAdapter(adapter);

        //发送消息
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = input_text.getText().toString();
                if(!"".equals(text)){
                    Msg msg = new Msg(text, Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，更新ListView
                    adapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    list_view.setSelection(msgList.size());
                    input_text.setText("");//情况输入框的内容
                }
            }
        });
    }
    private void initData() {
        // TODO Auto-generated method stub
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }




}

