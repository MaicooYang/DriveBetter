package com.loganisawesome.drivebetter.utilities.asynctasks;

import java.sql.SQLException;

import com.loganisawesome.drivebetter.database.DatabaseManager;
import com.loganisawesome.drivebetter.database.models.TripModel;
import com.loganisawesome.drivebetter.database.objects.Trip;
import com.loganisawesome.drivebetter.utilities.interfaces.AsyncTaskCallbacks;

import android.os.AsyncTask;

public class SaveTripTask extends AsyncTask<Void, Void, Void>
{
	private AsyncTaskCallbacks listener;
	private TripModel trip;
	
	private Boolean success = false;
	
	public SaveTripTask(AsyncTaskCallbacks listener, TripModel trip)
	{
		this.listener = listener;
		this.trip = trip;
	}

	@Override
	protected Void doInBackground(Void... params)
	{
		try
		{
			DatabaseManager.getInstance().create(new Trip(this.trip), Trip.class, Trip.ID_CLASS);
			this.success = true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result)
	{
		if (this.listener != null)
		{
			this.listener.onTaskComplete(this.success);
		}
	}
}
