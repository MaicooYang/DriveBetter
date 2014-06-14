package com.loganisawesome.drivebetter.database.models;

import com.loganisawesome.drivebetter.database.objects.Accel;

public class AccelModel
{
	private float accelX;
	private float accelY;
	private float accelZ;
	
	private long timestamp;
	
	public AccelModel(Accel accel)
	{
		
	}

	public float getAccelX()
	{
		return accelX;
	}

	public void setAccelX(float accelX)
	{
		this.accelX = accelX;
	}

	public float getAccelY()
	{
		return accelY;
	}

	public void setAccelY(float accelY)
	{
		this.accelY = accelY;
	}

	public float getAccelZ()
	{
		return accelZ;
	}

	public void setAccelZ(float accelZ)
	{
		this.accelZ = accelZ;
	}

	public long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}
}
