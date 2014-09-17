package atrem.modbus;

public class FrameIncoming {
	private int transactionIdentifier;
	private int protocolIdentifier;
	private int unitIdentifier;
	private int dataLength;
	private int numberOfDataBytes;
	private byte[] dataBytes;
	private int functionCode;

	public FrameIncoming() {

	}

	public FrameIncoming(int transactionIdentifier, int protocolIdentifier,
			int unitIdentifier) {
		super();
		this.transactionIdentifier = transactionIdentifier;
		this.protocolIdentifier = protocolIdentifier;
		this.unitIdentifier = unitIdentifier;
	}

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

	public int getUnitIdentifier() {
		return unitIdentifier;
	}

	public void setUnitIdentifier(int unitIdentifier) {
		this.unitIdentifier = unitIdentifier;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}

	public int getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(int functionCode) {
		this.functionCode = functionCode;
	}

	public int getNumberOfDataBytes() {
		return numberOfDataBytes;
	}

	public void setNumberOfDataBytes(int numberOfDataBytes) {
		this.numberOfDataBytes = numberOfDataBytes;
	}
}
