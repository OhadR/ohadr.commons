package com.ohadr.common.utils.resultset;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class ResultSetMetaDataImpl extends AbstractResultSetMetaData
{
	private Map<String, Object> row;
	private String[] columnNames;
	/**
	 * 
	 * @param map - the first "row" from ResultSet
	 */
	public ResultSetMetaDataImpl(Map<String, Object> map)
	{
		row = map;
		Set<String> set = row.keySet();
		columnNames = set.toArray(new String[set.size()]);
	}

	@Override
	public String getColumnClassName(int column) throws SQLException
	{
		String columnName = getColumnName(column);
		return row.get(columnName).getClass().getSimpleName();
	}

	@Override
	public int getColumnCount() throws SQLException
	{
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) throws SQLException
	{
		return columnNames[column-1];
	}

	@Override
	public int getColumnType(int column) throws SQLException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnTypeName(int column) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}
}
