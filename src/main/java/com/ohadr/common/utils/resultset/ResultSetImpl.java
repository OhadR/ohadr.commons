package com.ohadr.common.utils.resultset;

import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetImpl extends AbstractResultSet
{
	private List<Map<String, Object>> rows 	= new ArrayList<Map<String, Object>>();
	private Map<String, Object> currentRow;
	private int currentRowIndex = 0;			//result set contains several rows; this is the index of the current row (starts from 1, not 0: this is ResultSet standard)
	
	
	@Override
	public void updateString(String columnLabel, String x) throws SQLException
	{
		currentRow.put(columnLabel, x);
	}

	@Override
	public void updateInt(String columnLabel, int x) throws SQLException
	{
		currentRow.put(columnLabel, x);
	}

	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException
	{
		currentRow.put(columnLabel, x);
	}

	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException
	{
		currentRow.put(columnLabel, x);
	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException
	{
		currentRow.put(columnLabel, x);
	}
	
	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException
	{
		currentRow.put(columnLabel, x);
	}
	
	
	@Override
	public String getString(String columnLabel) throws SQLException
	{
		Object obj = currentRow.get(columnLabel);
		return (obj != null) ? (String) obj : null;
	}
	
	@Override
	public int getInt(String columnLabel) throws SQLException
	{
		Object obj = currentRow.get(columnLabel);
		return (obj != null) ? (int) obj : 0;		//according to the specs, if value is null we should return 0.
	}

	@Override
	public long getLong(String columnLabel) throws SQLException
	{
		Object obj = currentRow.get(columnLabel);
		return (obj != null) ? (long) obj : 0;		//according to the specs, if value is null we should return 0.
	}
	
	@Override
	public Date getDate(String columnLabel) throws SQLException
	{
		Object obj = currentRow.get(columnLabel);
		return (obj != null) ? (Date) obj : null;
	}

	@Override
	public double getDouble(String columnLabel) throws SQLException
	{
		Object obj = currentRow.get(columnLabel);
		return (obj != null) ? (double) obj : null;
	}
	
	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException
	{
		Object obj = currentRow.get(columnLabel);
		return (obj != null) ? (BigDecimal) obj : null;
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException
	{
		return currentRow.get(columnLabel);
	}
	
	
	
	@Override
	public ResultSetMetaData getMetaData() throws SQLException
	{
		return new ResultSetMetaDataImpl(rows.get(0));
	}


	
	@Override
	public boolean next() throws SQLException
	{
		boolean retVal;
		if(currentRowIndex < rows.size())
		{
			currentRow = rows.get(currentRowIndex);
			++currentRowIndex;
			retVal = true;
		}
		else
		{
			retVal = false;
		}
		return retVal;
	}
	
	@Override
	public void moveToInsertRow() throws SQLException
	{
		currentRow = new HashMap<String, Object>();
		++currentRowIndex;
	}

	@Override
	public void insertRow() throws SQLException
	{
		rows.add(currentRow);
	}

	@Override
	public boolean first() throws SQLException
	{
		currentRowIndex = 0;
		return true;
	}
}
