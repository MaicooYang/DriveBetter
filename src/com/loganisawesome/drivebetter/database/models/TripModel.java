package com.loganisawesome.drivebetter.database.models;

import java.util.Date;

import com.loganisawesome.drivebetter.database.objects.Trip;

public class TripModel
{

	private double consumption;
	private double avgSpeed;
	private double distance;
	private Date arrivalTime;
	private String route;
	private String notes;

	public TripModel()
	{
	}

	public TripModel(Trip trip)
	{
		this.consumption = trip.getConsumption();
		this.avgSpeed = trip.getAvgSpeed();
		this.distance = trip.getDistance();
		this.arrivalTime = trip.getArrivalTime();
		this.route = trip.getRoute();
		this.notes = trip.getNotes();
	}

	public TripModel(double consumption, double avgSpeed, double distance, Date arrivalTimem, String route, String notes)
	{
		this.consumption = consumption;
		this.avgSpeed = avgSpeed;
		this.distance = distance;
		this.arrivalTime = arrivalTimem;
		this.route = route;
		this.notes = notes;
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
