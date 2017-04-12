package com.bb.offerapp.activity;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.bb.offerapp.R;
import com.bb.offerapp.fragment.wechat.BookFragment;
import com.bb.offerapp.fragment.wechat.ContentFragment;
import com.bb.offerapp.fragment.wechat.FindFragment;
import com.bb.offerapp.fragment.wechat.MeFragment;

public class wechat extends Activity implements View.OnClickListener {
    private Fragment fr01, fr02, fr03, fr04;
    private ImageButton rb01, rb02, rb03, rb04;
    private FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat_layout);


        rb01 = (ImageButton) findViewById(R.id.bt1);
        rb02 = (ImageButton) findViewById(R.id.bt2);
        rb03 = (ImageButton) findViewById(R.id.bt3);
        rb04 = (ImageButton) findViewById(R.id.bt4);
        rb01.setOnClickListener(this);
        rb02.setOnClickListener(this);
        rb03.setOnClickListener(this);
        rb04.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        rb01.setImageResource(R.drawable.sms);
        rb02.setImageResource(R.drawable.list);
        rb03.setImageResource(R.drawable.joy);
        rb04.setImageResource(R.drawable.look);
        FragmentTransaction ft = fm.beginTransaction();
        if (fr01 != null)
            ft.hide(fr01);
        if (fr02 != null)
            ft.hide(fr02);
        if (fr03 != null)
            ft.hide(fr03);
        if (fr04 != null)
            ft.hide(fr04);

        if (v.getId() == R.id.bt1) {
            rb01.setImageResource(R.drawable.sms_p);
            if (fr01 == null) {
                fr01 = new ContentFragment();
                ft.add(R.id.frage, fr01);
            } else
                ft.show(fr01);
        }
        if (v.getId() == R.id.bt2) {
            rb02.setImageResource(R.drawable.list_p);
            if (fr02 == null) {
                fr02 = new BookFragment();
                ft.add(R.id.frage, fr02);
            } else
                ft.show(fr02);
        }
        if (v.getId() == R.id.bt3) {
            rb03.setImageResource(R.drawable.joy_p);
            if (fr03 == null) {
                fr03 = new FindFragment();
                ft.add(R.id.frage, fr03);
            } else
                ft.show(fr03);
        }
        if (v.getId() == R.id.bt4) {
            rb04.setImageResource(R.drawable.look_p);
            if (fr04 == null) {
                fr04 = new MeFragment();
                ft.add(R.id.frage, fr04);
            } else
                ft.show(fr04);
        }
        ft.commit();

    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_wechat, menu);
        return true;
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_wechat, menu);
//        return true;
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    //        int itemId = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}

