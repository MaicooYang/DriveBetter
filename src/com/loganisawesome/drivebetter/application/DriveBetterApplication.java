package com.loganisawesome.drivebetter.application;


import com.loganisawesome.drivebetter.database.DatabaseManager;

import android.app.Application;

public class DriveBetterApplication extends Application
{
	// Careful with me, memory leaks and null references are easy to make here

	@Override
	public void onCreate()
	{
		super.onCreate();

		// Initialize the DBManager
		DatabaseManager.init(getApplicationContext());
	}
}
