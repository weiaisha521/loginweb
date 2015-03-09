package com.pyou.lighting;



import java.io.IOException;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private ToggleButton lightButton;
	private Camera camera;
	private Camera.Parameters param ;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		camera=Camera.open();
		lightButton=(ToggleButton)findViewById(R.id.light_id);
		lightButton.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToggleButton tb=(ToggleButton)v;
			
				 try {
					 param = camera.getParameters();  
					camera.setPreviewTexture(new SurfaceTexture(0));//这一句话很重要，不加的话在nexus 5上灯泡不亮
					 if(!(tb).isChecked()){  
				            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);  
				            lightButton.setBackgroundColor(0x30ffffff);  
				        }else{  
				            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);  
				            lightButton.setBackgroundColor(0xffffffff);  
				        }  
				        camera.setParameters(param);  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(camera==null){
		camera=Camera.open();
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	/*@Override
	protected void onStop() {
		// TODO Auto-generatedethod stuba
		Toast.makeText(this, "闪光灯销毁", 1).show();
		super.onStop();
		camera.release();
	}*/
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("LIGHTING","闪光灯销毁	。。。");
		Toast.makeText(this, "闪光灯销毁", 1).show();
		super.onStop();
		camera.release();
	}
}
