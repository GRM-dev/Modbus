/**
 * 
 */
package atrem.jdbc;

import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

/**
 *
 */
public class DbQueryInt extends AbstractDbSQuery {
	
	/**
	 * @param ds
	 * @throws SQLException
	 */
	public DbQueryInt(OracleDataSource ds) throws SQLException {
		super(ds);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void query() throws SQLException {
		this.rs = stmt.executeQuery("SELECT * FROM Pracownicy");
	}
}
