package frames;

import java.nio.ByteBuffer;

public class ResponseFrame {
	private int transactionIdentifier;
	private int protocolIdentifier;
	private int unitIdentifier;
	private int dataLength;
	private byte[] dataBytes;
	private int functionCode;

	public ResponseFrame() {

	}

	public ResponseFrame(int transactionIdentifier, int protocolIdentifier,
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

	@Override
	public String toString() {
		return "FrameIncoming [transactionIdentifier=" + transactionIdentifier
				+ ", protocolIdentifier=" + protocolIdentifier
				+ ", unitIdentifier=" + unitIdentifier + ", dataLength="
				+ dataLength + ", dataBytes=" + zmiana() + " " + dataBytes[0]
				+ ", functionCode=" + functionCode + "]";
	}

	private float zmiana() {
		ByteBuffer byteBuffer = ByteBuffer.wrap(dataBytes, 1, 4);
		return byteBuffer.getFloat();
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
}
