package atrem.modbus.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

/*
 * Copyright 2003 Sun Microsystems, Inc. ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found
 * at
 * http://developer.java.sun.com/berkeley_license.html.
 */

public class DataType {
	private int		code;
	private String	SQLType;
	private String	localType		= null;
	private String	params			= null;
	private boolean	needsSetting	= true;
	
	public DataType(int code, String SQLType) {
		this.code = code;
		this.SQLType = SQLType;
	}
	
	public boolean needsToBeSet() {
		return needsSetting;
	}
	
	public static Vector getDataTypes(Connection con) throws SQLException {
		String structName = null, distinctName = null, javaName = null;
		
		// create a vector of class DataType initialized with
		// the SQL code, the SQL type name, and two null entries
		// for the local type name and the creation parameter(s)
		
		Vector dataTypes = new Vector();
		dataTypes.add(new DataType(java.sql.Types.BIT, "BIT"));
		dataTypes.add(new DataType(java.sql.Types.TINYINT, "TINYINT"));
		dataTypes.add(new DataType(java.sql.Types.SMALLINT, "SMALLINT"));
		dataTypes.add(new DataType(java.sql.Types.INTEGER, "INTEGER"));
		dataTypes.add(new DataType(java.sql.Types.BIGINT, "BIGINT"));
		dataTypes.add(new DataType(java.sql.Types.FLOAT, "FLOAT"));
		dataTypes.add(new DataType(java.sql.Types.REAL, "REAL"));
		dataTypes.add(new DataType(java.sql.Types.DOUBLE, "DOUBLE"));
		dataTypes.add(new DataType(java.sql.Types.NUMERIC, "NUMERIC"));
		dataTypes.add(new DataType(java.sql.Types.DECIMAL, "DECIMAL"));
		dataTypes.add(new DataType(java.sql.Types.CHAR, "CHAR"));
		dataTypes.add(new DataType(java.sql.Types.VARCHAR, "VARCHAR"));
		dataTypes.add(new DataType(java.sql.Types.LONGVARCHAR, "LONGVARCHAR"));
		dataTypes.add(new DataType(java.sql.Types.DATE, "DATE"));
		dataTypes.add(new DataType(java.sql.Types.TIME, "TIME"));
		dataTypes.add(new DataType(java.sql.Types.TIMESTAMP, "TIMESTAMP"));
		dataTypes.add(new DataType(java.sql.Types.BINARY, "BINARY"));
		dataTypes.add(new DataType(java.sql.Types.VARBINARY, "VARBINARY"));
		dataTypes.add(new DataType(java.sql.Types.LONGVARBINARY, "LONGVARBINARY"));
		dataTypes.add(new DataType(java.sql.Types.NULL, "NULL"));
		dataTypes.add(new DataType(java.sql.Types.OTHER, "OTHER"));
		dataTypes.add(new DataType(java.sql.Types.BLOB, "BLOB"));
		dataTypes.add(new DataType(java.sql.Types.CLOB, "CLOB"));
		
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet rs = dbmd.getTypeInfo();
		while (rs.next()) {
			int codeNumber = rs.getInt("DATA_TYPE");
			String dbmsName = rs.getString("TYPE_NAME");
			String createParams = rs.getString("CREATE_PARAMS");
			
			if (codeNumber == Types.STRUCT && structName == null)
				structName = dbmsName;
			else if (codeNumber == Types.DISTINCT && distinctName == null)
				distinctName = dbmsName;
			else if (codeNumber == Types.JAVA_OBJECT && javaName == null)
				javaName = dbmsName;
			else {
				for (int i = 0; i < dataTypes.size(); i++) {
					// find entry that matches the SQL code,
					// and if local type and params are not already set,
					// set them
					DataType type = (DataType) dataTypes.get(i);
					if (type.getCode() == codeNumber) {
						type.setLocalTypeAndParams(dbmsName, createParams);
					}
				}
			}
		}
		
		int[] types = {Types.STRUCT, Types.DISTINCT, Types.JAVA_OBJECT};
		rs = dbmd.getUDTs(null, "%", "%", types);
		while (rs.next()) {
			String typeName = null;
			DataType dataType = null;
			
			if (dbmd.isCatalogAtStart())
				typeName = rs.getString(1) + dbmd.getCatalogSeparator() + rs.getString(2)
						+ "." + rs.getString(3);
			else
				typeName = rs.getString(2) + "." + rs.getString(3)
						+ dbmd.getCatalogSeparator() + rs.getString(1);
			
			switch (rs.getInt(5)) {
				case Types.STRUCT :
					dataType = new DataType(Types.STRUCT, typeName);
					dataType.setLocalTypeAndParams(structName, null);
					break;
				case Types.DISTINCT :
					dataType = new DataType(Types.DISTINCT, typeName);
					dataType.setLocalTypeAndParams(distinctName, null);
					break;
				case Types.JAVA_OBJECT :
					dataType = new DataType(Types.JAVA_OBJECT, typeName);
					dataType.setLocalTypeAndParams(javaName, null);
					break;
			}
			dataTypes.add(dataType);
		}
		
		return dataTypes;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getSQLType() {
		return SQLType;
	}
	
	public String getLocalType() {
		return localType;
	}
	
	public String getParams() {
		return params;
	}
	
	public void setLocalTypeAndParams(String local, String p) {
		if (needsSetting) {
			localType = local;
			params = p;
			needsSetting = false;
		}
	}
}
