package com.loganisawesome.drivebetter.database.objects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.loganisawesome.drivebetter.database.models.AccelModel;

@DatabaseTable(tableName = "Accel")
public class Accel implements Serializable
{
	// Generated Serializable UID
	private static final long serialVersionUID = -8636369699414408255L;

	public static final Class<?> ID_CLASS = Integer.class;
	
	/**********************************************************************************************
	 * Column Names
	 **********************************************************************************************/
	public static final String ACCEL_ID_COLUMN_NAME = "AccelId";
	public static final String ACCEL_X_COLUMN_NAME = "AccelX";
	public static final String ACCEL_Y_COLUMN_NAME = "AccelY";
	public static final String ACCEL_Z_COLUMN_NAME = "AccelZ";
	public static final String TIMESTAMP_COLUMN_NAME = "Timestamp";

	/**********************************************************************************************
	 * Database Fields
	 **********************************************************************************************/
	@DatabaseField(generatedId = true, columnName = ACCEL_ID_COLUMN_NAME)
	@SerializedName(ACCEL_ID_COLUMN_NAME)
	private int accelId;

	@DatabaseField(columnName = ACCEL_X_COLUMN_NAME)
	@SerializedName(ACCEL_X_COLUMN_NAME)
	private double accelX;

	@DatabaseField(columnName = ACCEL_Y_COLUMN_NAME)
	@SerializedName(ACCEL_Y_COLUMN_NAME)
	private double accelY;

	@DatabaseField(columnName = ACCEL_Z_COLUMN_NAME)
	@SerializedName(ACCEL_Z_COLUMN_NAME)
	private double accelZ;

	@DatabaseField(columnName = TIMESTAMP_COLUMN_NAME)
	@SerializedName(TIMESTAMP_COLUMN_NAME)
	private long timestamp;
	
	public Accel()
	{
		// all persisted classes must define a no-arg constructor with at least package visibility
	}
	
	public Accel(AccelModel model)
	{
		this.accelX = model.getAccelX();
		this.accelY = model.getAccelY();
		this.accelZ = model.getAccelZ();
		this.timestamp = model.getTimestamp();
	}

	/**********************************************************************************************
	 * Property Get and Setters
	 **********************************************************************************************/
	public int getAccelId()
	{
		return accelId;
	}

	public void setAccelId(int accelId)
	{
		this.accelId = accelId;
	}

	public double getAccelX()
	{
		return accelX;
	}

	public void setAccelX(double accelX)
	{
		this.accelX = accelX;
	}

	public double getAccelY()
	{
		return accelY;
	}

	public void setAccelY(double accelY)
	{
		this.accelY = accelY;
	}

	public double getAccelZ()
	{
		return accelZ;
	}

	public void setAccelZ(double accelZ)
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
