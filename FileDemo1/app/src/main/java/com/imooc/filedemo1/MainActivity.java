package com.imooc.filedemo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends Activity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView= (TextView) findViewById(R.id.tv);

		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录  "/sdcard"
			Log.i("dir",Environment.getExternalStorageDirectory().toString());
			File saveFile = new File(sdCardDir,"a.txt");

			//写数据
			try {
				FileOutputStream fos= new FileOutputStream(saveFile);
				fos.write("bobobo".getBytes());
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			//读数据
			try {
				FileInputStream fis= new FileInputStream(saveFile);
				int len =0;
				byte[] buf = new byte[1024];
				StringBuffer sb = new StringBuffer();
				while((len=fis.read(buf))!=-1){
					sb.append(new String(buf, 0, len));
				}
				textView.setText(sb.toString());
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

	

}
