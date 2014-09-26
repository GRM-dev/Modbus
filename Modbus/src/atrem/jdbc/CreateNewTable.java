package atrem.jdbc;

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

public class CreateNewTable {
	String url = "jdbc:mySubprotocol:myDataSource";
	Connection con;
	Statement stmt;
	private String tableName = null;
	private DataType dataType;

	/**
	 * @param tableName
	 * @param columnNamesAndTypes
	 */
	public CreateNewTable(String tableName,
			Map<String, String> columnNamesAndTypes) {
		String localTypeName;
		String paramString;
		try {
			con = DriverManager.getConnection(url, "myLogin", "myPassword");
			stmt = con.createStatement();

			String columnName = null;
			String sqlType = null;
			this.tableName = tableName;
			String createTableString = "create table " + tableName + " (" + ""
					+ ")";
			System.out.println(createTableString);
			stmt.executeUpdate(createTableString);

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	private static String getInput(String prompt) throws SQLException {
		System.out.print(prompt);
		System.out.flush();
		try {
			java.io.BufferedReader bin;
			bin = new java.io.BufferedReader(new java.io.InputStreamReader(
					System.in));
			String result = bin.readLine();
			return result;

		} catch (java.io.IOException ex) {
			System.out.println("Caught java.io.IOException:");
			System.out.println(ex.getMessage());
			return "";
		}
	}
}
