package atrem.jdbc;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public enum DataType {

	INTEGER("INTEGER", Integer.class), TINYINT("TINYINT", Byte.class), SMALLINT(
			"SMALLINT", Short.class), BIGINT("BIGINT", Long.class), REAL(
			"REAL", Float.class), FLOAT("FLOAT", Double.class), DOUBLE(
			"DOUBLE", Double.class), DECIMAL("DECIMAL", BigDecimal.class), NUMERIC(
			"NUMERIC", BigDecimal.class), BOOLEAN("BOOLEAN", Boolean.class), CHAR(
			"CHAR", String.class), VARCHAR("VARCHAR", String.class), LONGVARCHAR(
			"LONGVARCHAR", String.class), DATE("DATE", Date.class), TIME(
			"TIME", Time.class), TIMESTAMP("TIMESTAMP", Timestamp.class);

	private String typeName;
	private Object className;

	private DataType(String typeName, Object className) {
		this.typeName = typeName;
		this.className = className;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public Object getClassName() {
		return this.className;
	}
}
