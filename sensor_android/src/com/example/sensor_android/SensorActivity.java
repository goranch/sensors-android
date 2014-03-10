package com.example.sensor_android;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends Activity implements SensorEventListener, OnClickListener {

	
	private SensorManager mSensorManager;
	private Sensor mSensorProximity, mSensorAxis, mSensorGyroscope, mSensorMagnet, mSensorOrient;
	private ImageView iv;
	
	private Button buttonOn, buttonOff;
	private TextView x, y, z, gyro, magnet, orientatation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_screen);
		
		buttonOn = (Button) findViewById(R.id.button1);
		buttonOff = (Button) findViewById(R.id.button2);
		x = (TextView) findViewById(R.id.textView1);
		y = (TextView) findViewById(R.id.textView2);
		z = (TextView) findViewById(R.id.textView3);
		
		gyro = (TextView) findViewById(R.id.textView11);
		magnet = (TextView) findViewById(R.id.textView12);
		orientatation = (TextView) findViewById(R.id.textView13);
		
		
		buttonOn.setOnClickListener(this);
		buttonOff.setOnClickListener(this);
			
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		mSensorAxis = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		mSensorMagnet = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mSensorOrient = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}
	
	protected void onResume() {
		super.onResume();
	Log.v("onResume","");
	}
	
	protected void onPause() {
		super.onPause();
		//mSensorManager.unregisterListener(this);
		Log.v("onPause","");
	}
	
	

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		Log.v("onAccuracyChanged","arg0:" + arg0 + " arg1:" + arg1);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			x.setText(event.values[0]+"");
			y.setText(event.values[1]+"");
			z.setText(event.values[2]+"");

			break;
		case Sensor.TYPE_GYROSCOPE:
			gyro.setText(event.values[0]+"");
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			magnet.setText(event.values[0]+"");
			break;
		case Sensor.TYPE_ORIENTATION:
			orientatation.setText(event.values[0]+"");
			break;
		case Sensor.TYPE_PROXIMITY:
			unlockScreen();
			Toast.makeText(getApplicationContext(), "SensorChanged!!! =)",
					   Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		
		System.out.println();
		
		Log.v("onSensorChanged", " theValue: " + event.sensor.getName());
		
		Log.v("onSensorChanged", "" + event.values[0]);
			
//		Toast.makeText(getApplicationContext(), "SensorChanged!!! =)",
//				   Toast.LENGTH_SHORT).show();
		
		
		
		
	}
	
	private void unlockScreen() {
		Window wind;
		wind = this.getWindow();
		wind.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
		wind.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		wind.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			System.out.println("button1switch");
			mSensorManager.registerListener(this, mSensorProximity,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorAxis,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorGyroscope,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorMagnet,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorOrient,
					SensorManager.SENSOR_DELAY_NORMAL);
			break;
		case R.id.button2:
			System.out.println("button2switch");
			mSensorManager.unregisterListener(this);
			break;

		default:
			break;
		}
	}

}