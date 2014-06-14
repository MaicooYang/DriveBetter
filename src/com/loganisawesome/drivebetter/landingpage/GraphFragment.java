package com.loganisawesome.drivebetter.landingpage;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GraphFragment extends Fragment implements SensorEventListener
{
	private SensorManager sensorManager;
	private Sensor accSensor;
	
	public static GraphFragment newInstance()
	{
		GraphFragment fragment = new GraphFragment();
		// Set args
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		this.sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		this.accSensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// do stuff
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		this.sensorManager.registerListener(this, this.accSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		this.sensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			float ax = event.values[0];
			float ay = event.values[1];
			float az = event.values[2];
			Log.i("Accel", "ax=" + String.valueOf(ax) + ";ay=" + String.valueOf(ay) + ";az=" + String.valueOf(az));
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	
}
