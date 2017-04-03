package com.ohadr.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class JsonUtils
{
	private static Logger log = Logger.getLogger(JsonUtils.class);

	   /**
     * 
     * @param objectToConvert
     * @return: JSON-String that represents objectToConvert. null if objectToConvert is null.
     */
	public static <T> String convertToJson(T objectToConvert)
    {
        if( objectToConvert == null )
        	return null;
    	
    	ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream bis = new ByteArrayOutputStream();

        String json = null;
        try
        {
            mapper.writeValue(bis, objectToConvert);
            json = bis.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("error converting " + objectToConvert.getClass().getSimpleName() + " to JSON");
        }

        log.trace("convertToJson() result: " + json);
        return json;
    }

	
	public static <T> T convertFromJson(String json, Class<T> typeParameterClass)
	{
	    ObjectMapper mapper = new ObjectMapper();
	    
		log.trace("received json: " + json);

	    T t = null;
		try
		{
			t = (T) mapper.readValue(json, typeParameterClass);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
	        log.error("error converting " + json + " from JSON");
		}
	    return t;
	}
	
	/**
	 * The following code converts a ResultSet to a JSON string using JSONArray and JSONObject.
	 * http://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
	 * @param resultSet
	 * @return JSON-String representation of the given ResultSet
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")		//we use 3rd-party non-type-safe types...
	public static String convertResultSetToJson(ResultSet resultSet) throws SQLException
	{
		if(resultSet == null)
			return null;
		
		JSONArray json = new JSONArray();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numColumns = metadata.getColumnCount();
		
		while(resultSet.next()) 			//iterate rows
		{
			JSONObject obj = new JSONObject();		//extends HashMap
			for (int i = 1; i <= numColumns; ++i) 			//iterate columns
			{
				String column_name = metadata.getColumnName(i);
				obj.put(column_name, resultSet.getObject(column_name));
			}
			json.add(obj);
		}
		return json.toJSONString();
	}


}
