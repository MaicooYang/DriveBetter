package com.loganisawesome.drivebetter.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.loganisawesome.drivebetter.database.objects.Trip;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
	/**********************************************************************************************
	 * Database Configuration
	 **********************************************************************************************/
	private static final String DATABASE_NAME = "DriveBetter.db";
	private static final int DATABASE_VERSION = 1;

	/**********************************************************************************************
	 * Constructor
	 **********************************************************************************************/
	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**********************************************************************************************
	 * onCreate and onUpgrade Events
	 **********************************************************************************************/
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
	{
		try
		{
			TableUtils.createTable(connectionSource, Trip.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
	{
		// TODO: might need to come up with clever upgrade methods
		try
		{
			TableUtils.dropTable(connectionSource, Trip.class, true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		onCreate(database, connectionSource);
	}
}
