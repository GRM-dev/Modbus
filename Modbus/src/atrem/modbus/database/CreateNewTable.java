package atrem.modbus.database;

/*
 * Copyright 2003 Sun Microsystems, Inc. ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found
 * at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Vector;

public class CreateNewTable {
	String			url	= "jdbc:mySubprotocol:myDataSource";
	Connection		con;
	Statement		stmt;
	private String	tableName;
	
	public CreateNewTable(String tableName, Map<String, String> columnNamesAndTypes) {
		try {
			con = DriverManager.getConnection(url, "myLogin", "myPassword");
			
			stmt = con.createStatement();
			
			Vector dataTypes = DataType.getDataTypes(con);
			
			String columnName = null;
			String sqlType = null;
			this.tableName = tableName;
			String createTableString = "create table " + tableName + " (";
			
			String cAS = ", ";
			boolean firstTime = true;
			while (true) {
				// TODO wartoœci z mapy
				// columnName = columnNamesAndTypes;
				if (firstTime) {
					if (columnName.length() == 0) {
						System.out.print("Need at least one column;");
						System.out.println(" please try again");
						continue;
					} else {
						createTableString += columnName + " ";
						firstTime = false;
					}
				} else if (columnName.length() == 0) {
					break;
				} else {
					createTableString += cAS + columnName + " ";
				}
				
				String localTypeName = null;
				String paramString = "";
				while (true) {
					System.out.println("");
					System.out.println("LIST OF TYPES YOU MAY USE:  ");
					boolean firstPrinted = true;
					int length = 0;
					for (int i = 0; i < dataTypes.size(); i++) {
						DataType dataType = (DataType) dataTypes.get(i);
						if (!dataType.needsToBeSet()) {
							if (!firstPrinted)
								System.out.print(cAS);
							else
								firstPrinted = false;
							System.out.print(dataType.getSQLType());
							length += dataType.getSQLType().length();
							if (length > 50) {
								System.out.println("");
								length = 0;
								firstPrinted = true;
							}
						}
					}
					System.out.println("");
					
					int index;
					// sqlType = columnTypes[0];
					for (index = 0; index < dataTypes.size(); index++) {
						DataType dataType = (DataType) dataTypes.get(index);
						if (dataType.getSQLType().equalsIgnoreCase(sqlType)
								&& !dataType.needsToBeSet()) {
							break;
						}
					}
					
					localTypeName = null;
					paramString = "";
					if (index < dataTypes.size()) { // there was a match
						String params;
						DataType dataType = (DataType) dataTypes.get(index);
						params = dataType.getParams();
						localTypeName = dataType.getLocalType();
						if (params != null) {
							
							paramString = "(" + "" + ")";
						}
						break;
					} else {  						// use the name as given
					
						String check = "" + " ";
						check = check.toLowerCase().substring(0, 1);
						if (check.equals("n"))
							continue;
						else {
							localTypeName = sqlType;
							break;
						}
					}
				}
				
				createTableString += localTypeName + paramString;
				
			}
			
			createTableString += ")";
			System.out.println("");
			System.out.print("Your CREATE TABLE statement as ");
			System.out.println("sent to your DBMS:  ");
			System.out.println(createTableString);
			System.out.println("");
			
			stmt.executeUpdate(createTableString);
			
			stmt.close();
			con.close();
			
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
	
	private static String getInput(String prompt) throws SQLException {
		
		System.out.print(prompt);
		System.out.flush();
		
		try {
			java.io.BufferedReader bin;
			bin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			
			String result = bin.readLine();
			return result;
			
		}
		catch (java.io.IOException ex) {
			System.out.println("Caught java.io.IOException:");
			System.out.println(ex.getMessage());
			return "";
		}
	}
}
