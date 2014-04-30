package com.loganisawesome.drivebetter.database.objects;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.loganisawesome.drivebetter.database.models.TripModel;

@DatabaseTable(tableName = "Trip")
public class Trip implements Serializable
{
	// Generated Serializable UID
	private static final long serialVersionUID = 1253886401102174401L;
	
	public static final Class<?> ID_CLASS = Integer.class;

	/**********************************************************************************************
	 * Column Names
	 **********************************************************************************************/
	public static final String TRIP_ID_COLUMN_NAME = "TripId";
	public static final String CONSUMPTION_COLUMN_NAME = "Consumption";
	public static final String AVERAGE_SPEED_COLUMN_NAME = "AverageSpeed";
	public static final String DISTANCE_COLUMN_NAME = "Distance";
	public static final String ARRIVAL_TIME_COLUMN_NAME = "ArrivalTime";
	public static final String ROUTE_COLUMN_NAME = "Route";
	public static final String NOTES_COLUMN_NAME = "Notes";

	/**********************************************************************************************
	 * Database Fields
	 **********************************************************************************************/
	@DatabaseField(generatedId = true, columnName = TRIP_ID_COLUMN_NAME)
	@SerializedName(TRIP_ID_COLUMN_NAME)
	private int tripId;

	@DatabaseField(columnName = CONSUMPTION_COLUMN_NAME)
	@SerializedName(CONSUMPTION_COLUMN_NAME)
	private double consumption;

	@DatabaseField(columnName = AVERAGE_SPEED_COLUMN_NAME)
	@SerializedName(AVERAGE_SPEED_COLUMN_NAME)
	private double avgSpeed;

	@DatabaseField(columnName = DISTANCE_COLUMN_NAME)
	@SerializedName(DISTANCE_COLUMN_NAME)
	private double distance;

	@DatabaseField(columnName = ARRIVAL_TIME_COLUMN_NAME)
	@SerializedName(ARRIVAL_TIME_COLUMN_NAME)
	private Date arrivalTime;

	@DatabaseField(columnName = ROUTE_COLUMN_NAME)
	@SerializedName(ROUTE_COLUMN_NAME)
	private String route;

	@DatabaseField(columnName = NOTES_COLUMN_NAME)
	@SerializedName(NOTES_COLUMN_NAME)
	private String notes;
	
	public Trip()
	{
		// all persisted classes must define a no-arg constructor with at least package visibility
	}
	
	public Trip(TripModel model)
	{
		// For ease of creation
		this.consumption = model.getConsumption();
		this.avgSpeed = model.getAvgSpeed();
		this.distance = model.getDistance();
		this.arrivalTime = model.getArrivalTime();
		this.route = model.getRoute();
		this.notes = model.getNotes();
	}

	/**********************************************************************************************
	 * Property Get and Setters
	 **********************************************************************************************/
	public int getTripId()
	{
		return tripId;
	}

	public void setTripId(int tripId)
	{
		this.tripId = tripId;
	}

	public double getConsumption()
	{
		return consumption;
	}

	public void setConsumption(double consumption)
	{
		this.consumption = consumption;
	}

	public double getAvgSpeed()
	{
		return avgSpeed;
	}

	public void setAvgSpeed(double avgSpeed)
	{
		this.avgSpeed = avgSpeed;
	}

	public double getDistance()
	{
		return distance;
	}

	public void setDistance(double distance)
	{
		this.distance = distance;
	}

	public Date getArrivalTime()
	{
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public String getRoute()
	{
		return route;
	}

	public void setRoute(String route)
	{
		this.route = route;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}
}
