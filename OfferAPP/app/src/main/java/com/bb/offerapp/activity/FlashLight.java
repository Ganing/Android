package com.bb.offerapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bb.offerapp.R;

@SuppressLint({"NewApi", "HandlerLeak"})
@SuppressWarnings("deprecation")
public class FlashLight extends Activity {
	private ImageView switch_button;
	private Camera camera;
	private boolean isOpen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flashlight);

		initView();
	}

	private void initView() {


		switch_button = (ImageView) findViewById(R.id.switch_button);
		switch_button.setOnClickListener(new SwitchListener());



	}
	class SwitchListener implements View.OnClickListener {



		@Override
		public void onClick(View v) {
			if(!isOpen){
				switch_button.setImageResource(R.drawable.on);

				camera = Camera.open();
				Camera.Parameters parameters = camera.getParameters();

				parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				camera.setParameters(parameters);
				camera.startPreview();//开启手电筒
				isOpen = true;
			}else{
				switch_button.setImageResource(R.drawable.off);

				Camera.Parameters parameters = camera.getParameters();

				camera.setParameters(parameters);
				camera.stopPreview();//关闭手电筒
				camera.release();
				isOpen = false;
			}
		}

	}


}
