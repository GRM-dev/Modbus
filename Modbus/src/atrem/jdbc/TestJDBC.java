package atrem.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class TestJDBC {

	public static void main(String[] args) throws SQLException {
		OracleDataSource ds = createDatasource();

		//
		doSomething(ds);

	}

	private static OracleDataSource createDatasource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL("jdbc:oracle:thin:@10.7.7.152:1521:xe");
		ds.setUser("patryk");
		ds.setPassword("atrem");

		// pule po³¹czeñ
		ds.setConnectionCachingEnabled(true);
		Properties p = new Properties();
		p.setProperty("MinLimit", "1");
		p.setProperty("MaxLimit", "10");
		ds.setConnectionCacheProperties(p);

		return ds;
	}

	private static void doSomething(DataSource ds) throws SQLException {
		Connection con = ds.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select nazwisko from pracownicy");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		rs.close();
		st.close();
		con.close();
	}

}
