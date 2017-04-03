package com.ohadr.common.utils.resultset;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.json.simple.JSONObject;

public abstract class ResultSetConverters
{
	/**
	 * writes a given ResultSet object to a PrintWriter. Every 1000 rows, data is flushed to the writer.
	 * 
	 * @param resultSet - the input ResultSet that should be written to a writer.
	 * @param writer - a PrintWriter object to write the data to. Must be initialized ahead, assumably with a File Object:
	 * <pre>
	 *	String fileName = "c:/ohad/dev/tmp/RESULTS_FILE_NAME.txt";
	 *	FileWriter writerFile = new FileWriter(fileName, true);
	 *	PrintWriter writer = new PrintWriter(writerFile);
	 *</pre>
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")		//we use 3rd-party non-type-safe types...
	public static void writeResultSetToWriter(ResultSet resultSet, PrintWriter writer) throws SQLException
	{
		if(resultSet == null || writer == null)
			return;
		
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numColumns = metadata.getColumnCount();
		int numRows = 0;
		
		while(resultSet.next()) 			//iterate rows
		{
			++numRows;
			JSONObject obj = new JSONObject();		//extends HashMap
			for (int i = 1; i <= numColumns; ++i) 			//iterate columns
			{
				String column_name = metadata.getColumnName(i);
				obj.put(column_name, resultSet.getObject(column_name));
			}
			writer.println(obj.toJSONString());
			
			if(numRows % 1000 == 0)
				writer.flush();
		}
	}


}
