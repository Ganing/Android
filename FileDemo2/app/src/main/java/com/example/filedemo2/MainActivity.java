package com.example.filedemo2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText edt;
	Button but;
	TextView contentvalue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt = (EditText) findViewById(R.id.editText1);
		but = (Button) findViewById(R.id.write);
		contentvalue = (TextView) findViewById(R.id.contentvalue);
		but.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WriteFiles(edt.getText().toString());
				contentvalue.setText(readFiles());
				
			}
		});
	}
	//保存文件内容
	public void WriteFiles(String content){
		 try {
			FileOutputStream fos = openFileOutput("a.txt", MODE_PRIVATE);
			 fos.write(content.getBytes());
			 fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	//读取文件内容
	public String readFiles(){
		String content = null;
		 try {
			FileInputStream fis= openFileInput("a.txt");
			StringBuilder sb = new StringBuilder();

			byte [] buffer =  new byte[1024];
			int len = 0;
			while ((len=fis.read(buffer))!=-1) {
				sb.append(new String(buffer, 0, len));
			}
			content =sb.toString();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
}
