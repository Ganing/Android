package com.bb.offerapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bb.offerapp.R;

public class Toast_1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast_main);
		initEvent();
	}

	private void initEvent(){
		findViewById(R.id.toast_btn1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showToast1();
			}
		});
		findViewById(R.id.toast_btn2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showToast2();
			}
		});
		findViewById(R.id.toast_btn3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showToast3();
			}
		});
		findViewById(R.id.toast_btn4).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showToast4();
			}
		});
	}


	private void showToast1(){

		Toast toast = Toast.makeText(this,R.string.app_name, Toast.LENGTH_SHORT);
		toast.show();
	}

	private void showToast2(){
		Toast toast = Toast.makeText(this,"改变位置Toast!", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	private void showToast3(){
		Toast toast = Toast.makeText(this,"带图片的Toast", Toast.LENGTH_LONG);
		LinearLayout toast_layout = (LinearLayout)toast.getView();
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.topimg);
		toast_layout.addView(iv,0);
		toast.show();
	}


	private void showToast4(){
		LayoutInflater inflater = LayoutInflater.from(this);
		View toast_view = inflater.inflate(R.layout.toast_layout, null);
		Toast toast = new Toast(this);
		toast.setView(toast_view);
        toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}
}
