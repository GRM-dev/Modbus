package atrem.jdbc;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum DataType {
		
	DataType("INTEGER", Integer.class),
	DataType("TINYINT", Byte.class),
	DataType("SMALLINT", Short.class),
	DataType("BIGINT", Long.class),
	DataType("REAL", Float.class),
		TYPE.put("FLOAT", Double.class),
		TYPE.put("DOUBLE", Double.class),
		TYPE.put("DECIMAL", BigDecimal.class),
		TYPE.put("NUMERIC", BigDecimal.class),
		TYPE.put("BOOLEAN", Boolean.class),
		TYPE.put("CHAR", String.class),
		TYPE.put("VARCHAR", String.class),
		TYPE.put("LONGVARCHAR", String.class),
		TYPE.put("DATE", Date.class),
		TYPE.put("TIME", Time.class),
		TYPE.put("TIMESTAMP", Timestamp.class);
	
private DataType(String typeName, Object className) {
	
}
}
