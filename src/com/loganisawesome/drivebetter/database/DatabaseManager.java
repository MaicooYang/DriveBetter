package com.loganisawesome.drivebetter.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;

public class DatabaseManager
{
	/**********************************************************************************************
	 * Fields
	 **********************************************************************************************/
	private static DatabaseManager instance;
	private DatabaseHelper databaseHelper;

	/**********************************************************************************************
	 * Private constructor to make singleton
	 **********************************************************************************************/
	private DatabaseManager(Context context)
	{
		this.databaseHelper = new DatabaseHelper(context);
	}

	/**********************************************************************************************
	 * DatabaseManager Methods
	 **********************************************************************************************/
	static public void init(Context context)
	{
		if (instance == null)
		{
			instance = new DatabaseManager(context);
		}
	}

	static public DatabaseManager getInstance()
	{
		return instance;
	}

	private DatabaseHelper getHelper()
	{
		return this.databaseHelper;
	}

	/**********************************************************************************************
	 * Generic Table Methods
	 **********************************************************************************************/
	public <T, U> void create(T item, Class<T> tableClass, Class<U> idClass) throws SQLException
	{
		Dao<T, U> dao = getHelper().getDao(tableClass);
		dao.create(item);
	}
	
	public <T, U> void createList(List<T> list, Class<T> tableClass, Class<U> idClass) throws Exception
	{
		Dao<T, U> dao = getHelper().getDao(tableClass);
		dao.callBatchTasks(new BulkInsertCallable<T, U>(dao, list));
	}
	
	public <T, U> void deleteAll(Class<T> tableClass) throws Exception
	{
		TableUtils.clearTable(getHelper().getConnectionSource(), tableClass);
	}
	
	public <T> List<T> getAll(Class<T> tableClass) throws SQLException
	{
		// TODO might need to pass in the idClass
		Dao<T, Integer> dao = getHelper().getDao(tableClass);
		return dao.queryForAll();
	}
	
	public <T, U> T getForId(Class<T> tableClass, U id) throws SQLException
	{
		Dao<T, U> dao = getHelper().getDao(tableClass);
		return dao.queryForId(id);
	}
	
	public <T> List<T> getForWhereEquals(Class<T> tableClass, String columnName, Object value) throws SQLException
	{
		// TODO might need to pass in the idClass
		Dao<T, Integer> dao = getHelper().getDao(tableClass);
		return dao.query(dao.queryBuilder().where().eq(columnName, value).prepare());
	}
	
	public <T> List<T> getForWhereIn(Class<T> tableClass, String columnName, Iterable<?> objects) throws SQLException
	{
		Dao<T, Integer> dao = getHelper().getDao(tableClass);
		return dao.query(dao.queryBuilder().where().in(columnName, objects).prepare());
	}
	
	public <T, U> List<U> getColumnForWhereIn(Class<T> tableClass, Class<U> returnClass, String returnColumnName, String inColumnName, Iterable<?> objects) throws SQLException
	{
		// Get helper
		Dao<T, Integer> dao = getHelper().getDao(tableClass);
		// Get queryBuilder
		QueryBuilder<T, Integer> qb = dao.queryBuilder();
		// Prepare query
		qb.selectColumns(returnColumnName).where().in(inColumnName, objects);
		// Query
		GenericRawResults<String[]> rawResults = dao.queryRaw(qb.prepareStatementString());
		// Get results
		List<String[]> results = rawResults.getResults();
		String[] resultsArray = results.get(0);
		
		// Create return list
		List<U> returnList = new ArrayList<U>();
		for (String string : resultsArray)
		{
			returnList.add(returnClass.cast(string));
		}
		
		return returnList;
	}
	
	public <T> List<T> getColumnForWhereEquals(Class<T> tableClass, String returnColumnName, String compareColumnName, Object value) throws SQLException
	{
		// TODO: Make this pass in a return type, and pass only the list back
		// This method only populates the returnColumnName of the object, so care must be taken when using this as the rest of
		// the fields will have default values
		Dao<T, Integer> dao = getHelper().getDao(tableClass);
		return dao.query(dao.queryBuilder().selectColumns(returnColumnName).where().eq(compareColumnName, value).prepare());
	}

	/**********************************************************************************************
	 * Inner Helper Classes
	 **********************************************************************************************/
	class BulkInsertCallable<T, U> implements Callable<Void>
	{
		private Dao<T, U> dao;
		private List<T> list;

		public BulkInsertCallable(Dao<T, U> dao, List<T> list)
		{
			this.dao = dao;
			this.list = list;
		}

		@Override
		public Void call() throws Exception
		{
			for (T item : this.list)
			{
				this.dao.create(item);
			}

			return null;
		}
	}
	
	class BulkQueryCallable<T, U> implements Callable<List<T>>
	{
		private Dao<T, U> dao;
		private List<U> idList;;

		public BulkQueryCallable(Dao<T, U> dao, List<U> idList)
		{
			this.dao = dao;
			this.idList = idList;
		}
		
		@Override
		public List<T> call() throws Exception
		{
			List<T> objectList = new ArrayList<T>();
			for (U id : this.idList)
			{
				objectList.add(this.dao.queryForId(id));
			}
			
			return objectList;
		}
	}

}
