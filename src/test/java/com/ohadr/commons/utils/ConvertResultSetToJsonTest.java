package com.ohadr.commons.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.ohadr.common.utils.JsonUtils;

public class ConvertResultSetToJsonTest 
{
	private DataSource dataSource;
	private Connection connection;
	private Statement statement = null;

	public void testConvertResultSetToJson() throws SQLException
	{
		open();					//TODO init dataSource
		ResultSet resultSet = null;

		String sql = "select * from CAMPAIGN_BONUS";
		resultSet = executeScriptWithResultSet(sql);
		
		String json = JsonUtils.convertResultSetToJson(resultSet);
		
		System.out.println(json);

		close();
	}
	
	
	public void open() throws SQLException
	{
		if (connection != null)
		{
			return;
		}

		connection = dataSource.getConnection();
		statement = connection.createStatement();
	}

	
	public void close() throws SQLException
	{
		if (connection == null)
		{
			return;
		}

		if (statement != null)
		{
			statement.close();
		}
		connection.close();
	}

	/**
	 * Executes a script that does not updates database and returns a result
	 * set.
	 * 
	 * @param sql
	 * @return the result set.
	 * @throws SQLException 
	 */
	public ResultSet executeScriptWithResultSet(String sql) throws SQLException
	{
		ResultSet result = statement.executeQuery(sql);
		return result;
	}

}
