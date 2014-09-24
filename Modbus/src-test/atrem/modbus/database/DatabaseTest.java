package atrem.modbus.database;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import oracle.jdbc.pool.OracleDataSource;

import org.junit.Test;

import atrem.jdbc.DbQueryInt;

public class DatabaseTest {
	
	@Test
	public void testdBConnection() {
		try {
			OracleDataSource ds = new OracleDataSource();
			AbstractDbSQuery query = new DbQueryInt(ds);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail("Db Connect Error!" + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testQuerryINTSelect() {
		try {
			OracleDataSource ds = new OracleDataSource();
			AbstractDbSQuery query;
			query = new DbQueryInt(ds);
			query.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail("Querry Error." + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testDataGetter() {
		AbstractDbSQuery query = null;
		try {
			OracleDataSource ds = new OracleDataSource();
			query = new DbQueryInt(ds);
			query.execute();
			if (query.rs == null)
				fail("rs jest null");
			HashMap<Integer, int[]> records = query.getQueryRecords();
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail("getRecordsFailed." + e.getLocalizedMessage() + query.rs.toString());
		}
	}
	
	@Test
	public void testDataVerify() {
		AbstractDbSQuery query = null;
		HashMap<Integer, int[]> records = null;
		try {
			OracleDataSource ds = new OracleDataSource();
			query = new DbQueryInt(ds);
			records = query.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		for (Entry<Integer, int[]> entry : records.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
	
	@Test
	public void testGetAllData() {
		AbstractDbSQuery query = null;
		HashMap<Integer, int[]> records;
		try {
			OracleDataSource ds = new OracleDataSource();
			query = new DbQueryInt(ds);
			query.execute();
			if (query.rs == null)
				fail("rs jest null");
			records = query.getQueryRecords();
		}
		catch (SQLException e) {}
		try {
			query.getAllData();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
