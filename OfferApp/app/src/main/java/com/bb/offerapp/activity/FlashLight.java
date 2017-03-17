package com.bb.offerapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.StateCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

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
			// TODO Auto-generated method stub
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
