package atrem.jdbc;

/*
 * Copyright 2003 Sun Microsystems, Inc. ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found
 * at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRow {
	String		url		= "jdbc:mySubprotocol:myDataSource";
	Connection	con;
	Statement	stmt;
	String		query	= "select COF_NAME, PRICE from COFFEES";
	
	public InsertRow() {
		
		try {
			con = DriverManager.getConnection(url, "myLogin", "myPassword");
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs = stmt.executeQuery("SELECT * FROM COFFEES");
			
			uprs.moveToInsertRow();
			
			uprs.updateString("COF_NAME", "Kona");
			uprs.updateInt("SUP_ID", 150);
			uprs.updateFloat("PRICE", 10.99f);
			uprs.updateInt("SALES", 0);
			uprs.updateInt("TOTAL", 0);
			
			uprs.insertRow();
			uprs.beforeFirst();
			
			System.out.println("Table COFFEES after insertion:");
			while (uprs.next()) {
				String s = uprs.getString("COF_NAME");
				int sup = uprs.getInt("SUP_ID");
				float f = uprs.getFloat("PRICE");
				int sales = uprs.getInt("SALES");
				int t = uprs.getInt("TOTAL");
				System.out.print(s + "   " + sup + "   " + f + "   ");
				System.out.println(sales + "   " + t);
			}
			
			uprs.close();
			stmt.close();
			con.close();
			
		}
		catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
