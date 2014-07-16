package com.example.sensor_android;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener,
		OnClickListener {

	private String LOG_TAG = "SensorActivity";

	private SensorManager mSensorManager;
	private ImageView iv;

	private Button buttonOn, buttonOff;
	private TextView x, y, z, gyro, magnet, orientatation;
	private Sensor mSensorProximity, mSensorAxis, mSensorGyroscope,
			mSensorMagnet, mSensorOrient;
	private Sensor mSensorAmbientTemperature;
	private Sensor mSensorLight;
	private Sensor mSensorLinearAcceleration;
	private Sensor mSensorPressure;
	private Sensor mSensorHumidity;
	private Sensor mSensorRotationVector;
	private Sensor mSensorTemperature;

	private TextView ambientTemp;
	private TextView light;
	private TextView linearAcceleration;
	private TextView pressure;
	private TextView humidity;
	private TextView rotationVector;
	private TextView temperature;

	private float xo;
	private float yo;
	private float zo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_screen);

		buttonOn = (Button) findViewById(R.id.button1);
		buttonOff = (Button) findViewById(R.id.button2);
		x = (TextView) findViewById(R.id.x_axis);
		y = (TextView) findViewById(R.id.y_axis);
		z = (TextView) findViewById(R.id.z_axis);
		ambientTemp = (TextView) findViewById(R.id.ambient_temperature);
		light = (TextView) findViewById(R.id.light);
		linearAcceleration = (TextView) findViewById(R.id.linear_acceleration);
		pressure = (TextView) findViewById(R.id.pressure);
		humidity = (TextView) findViewById(R.id.humidity);
		rotationVector = (TextView) findViewById(R.id.rotation_vector);
		temperature = (TextView) findViewById(R.id.temperature);

		gyro = (TextView) findViewById(R.id.textView11);
		magnet = (TextView) findViewById(R.id.mag_field);
		// orientatation = (TextView) findViewById(R.id.textView13);

		buttonOn.setOnClickListener(this);
		buttonOff.setOnClickListener(this);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensorProximity = mSensorManager
				.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		mSensorAxis = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorGyroscope = mSensorManager
				.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		mSensorMagnet = mSensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		// mSensorOrient = mSensorManager.getOrientation(R, values)
		mSensorAmbientTemperature = mSensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		mSensorLinearAcceleration = mSensorManager
				.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		mSensorHumidity = mSensorManager
				.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
		mSensorRotationVector = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		mSensorTemperature = mSensorManager
				.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	protected void onResume() {
		super.onResume();
		Log.v("onResume", "");
	}

	protected void onPause() {
		super.onPause();
		// mSensorManager.unregisterListener(this);
		Log.v("onPause", "");
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		Log.v("onAccuracyChanged", "arg0:" + arg0 + " arg1:" + arg1);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

		switch (event.sensor.getType()) {
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			// Log.v(LOG_TAG , "AMBIENT_TEMPERATURE" + event.values[0]+"");
			ambientTemp.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_LIGHT:
			// Log.v(LOG_TAG , "LIGHT" + event.values[0]+"");
			light.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_LINEAR_ACCELERATION:
			// Log.v(LOG_TAG , "LINEAR_ACCELERATION" + event.values[0]+"");
			linearAcceleration.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_PRESSURE:
			// Log.v(LOG_TAG , "PRESSURE" + event.values[0]+"");
			pressure.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_RELATIVE_HUMIDITY:
			// Log.v(LOG_TAG , "TYPE_RELATIVE_HUMIDITY" + event.values[0]+"");
			humidity.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_ROTATION_VECTOR:
			// Log.v(LOG_TAG , "TYPE_ROTATION_VECTOR" + event.values[0]+"");
			rotationVector.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_TEMPERATURE:
			// Log.v(LOG_TAG , "TYPE_TEMPERATURE" + event.values[0]+"");
			temperature.setText(event.values[0] + "");
			break;

		case Sensor.TYPE_ACCELEROMETER:
			x.setText(event.values[0] + "");
			y.setText(event.values[1] + "");
			z.setText(event.values[2] + "");

			xo = event.values[0];
			yo = event.values[1];
			zo = event.values[2];

			break;
		case Sensor.TYPE_GYROSCOPE:
			gyro.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			magnet.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_ORIENTATION:
			orientatation.setText(event.values[0] + "");
			break;
		case Sensor.TYPE_PROXIMITY:
			
			unlockScreen();

			// Toast.makeText(getApplicationContext(), "SensorChanged!!! =)",
			// Toast.LENGTH_SHORT).show();

			//sdfasdf
			// lkjfkld

			break;
		default:
			break;
		}

		System.out.println();

		// Log.v("onSensorChanged", " theValue: " + event.sensor.getName());

		// Log.v("onSensorChanged", "" + event.values[0]);

		// Toast.makeText(getApplicationContext(), "SensorChanged!!! =)",
		// Toast.LENGTH_SHORT).show();

	}

	private void unlockScreen() {
		Window wind;
		wind = this.getWindow();
		wind.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
		wind.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		wind.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);

		if (Math.abs(xo) < 1 && Math.abs(yo) < 1) {
			WakeLock screenLock = ((PowerManager) getSystemService(POWER_SERVICE))
					.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
							| PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
			screenLock.acquire();
		}

		Log.v(LOG_TAG, "should be on");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			System.out.println("Register sensors");
			mSensorManager.registerListener(this, mSensorLight,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorLinearAcceleration,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorPressure,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorHumidity,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorRotationVector,
					SensorManager.SENSOR_DELAY_NORMAL);
			mSensorManager.registerListener(this, mSensorTemperature,
					SensorManager.SENSOR_DELAY_NORMAL);
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

			mSensorManager.registerListener(this, mSensorAmbientTemperature,
					SensorManager.SENSOR_DELAY_NORMAL);

			break;
		case R.id.button2:
			System.out.println("unregister sensors");
			mSensorManager.unregisterListener(this);
			break;

		default:
			break;
		}
	}

}
