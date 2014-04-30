package com.loganisawesome.drivebetter.utilities.loaders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loganisawesome.drivebetter.database.DatabaseManager;
import com.loganisawesome.drivebetter.database.models.TripModel;
import com.loganisawesome.drivebetter.database.objects.Trip;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class TripLoader extends AsyncTaskLoader<List<TripModel>>
{
	public TripLoader(Context context)
	{
		super(context);
	}

	@Override
	public void onContentChanged()
	{
		forceLoad();
	}

	@Override
	protected void onStartLoading()
	{
		forceLoad();
	}

	@Override
	public List<TripModel> loadInBackground()
	{
		List<Trip> tripList = new ArrayList<>();
		List<TripModel> modelList = new ArrayList<>();
		
		try
		{
			tripList = DatabaseManager.getInstance().getAll(Trip.class);

			for (Trip trip : tripList)
			{
				modelList.add(new TripModel(trip));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return modelList;
	}
	
	
}
