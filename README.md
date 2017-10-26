ohadr.commons   [![Build Status](https://travis-ci.org/OhadR/ohadr.commons.svg?branch=master)](https://travis-ci.org/OhadR/ohadr.commons)   [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.ohadr/ohadr.commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.ohadr/ohadr.commons)
=============


## `JsonUtils`

    public abstract class JsonUtils
    {
      public static <T> String convertToJson(T objectToConvert)
      public static <T> T convertFromJson(String json, Class<T> typeParameterClass)
      public static String convertResultSetToJson(ResultSet resultSet) throws SQLException
    }


## c3p0 utils


## `ResultSet` implementation

    abstract class AbstractResultSet implements ResultSet

An abstract class that implements all `ResultSet` methods. The implementation throws `UnsupportedOperationException()`.

    abstract class AbstractResultSetMetaData implements ResultSetMetaData

Similar, but for `ResultSetMetaData`.

    public class ResultSetImpl extends AbstractResultSet

An implementation for ResultSet, for most updates() and gets(). If something is missing - contributions are most welcome! There are also implementations for:

	next()
	moveToInsertRow()
	insertRow()
	first()
	
	class ResultSetMetaDataImpl extends AbstractResultSetMetaData
	
An implementation for ResultSetMetaData. Used by `ResultSetImpl`.

	
	ResultSetConverters
	
An easy-to-use converter for ResultSet. Currently it has a method

	writeResultSetToWriter(ResultSet resultSet, Writer writer)
	
user code may pass a `PrinterWriter` object so it will be written to a file (an example can be found in the unit tests).
