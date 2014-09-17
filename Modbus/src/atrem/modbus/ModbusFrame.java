package atrem.modbus;

public class ModbusFrame {

	private int transactionIdentifier;
	private int protocolIdentifier;
	private int lengthField;
	private int unitIdentifier;
	private int functionCode;

	public int getTransactionIdentifier() {
		return transactionIdentifier;
	}
	public void setTransactionIdentifier(int transactionIdentifier) {
		this.transactionIdentifier = transactionIdentifier;
	}
	public int getProtocolIdentifier() {
		return protocolIdentifier;
	}
	public void setProtocolIdentifier(int protocolIdentifier) {
		this.protocolIdentifier = protocolIdentifier;
	}
	public int getLengthField() {
		return lengthField;
	}
	public void setLengthField(int lengthField) {
		this.lengthField = lengthField;
	}
	public int getUnitIdentifier() {
		return unitIdentifier;
	}
	public void setUnitIdentifier(int unitIdentifier) {
		this.unitIdentifier = unitIdentifier;
	}
	public int getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(int functionCode) {
		this.functionCode = functionCode;
	}

}
