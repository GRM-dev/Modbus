package frames;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

public class ResponseFrame {
	@Override
	public String toString() {
		return "ResponseFrame [transactionIdentifier=" + transactionIdentifier
				+ ", protocolIdentifier=" + protocolIdentifier
				+ ", unitIdentifier=" + unitIdentifier + ", dataLength="
				+ dataLength + ", dataBytes=" + Arrays.toString(dataBytes)
				+ ", functionCode=" + functionCode + "]";
	}

	private int transactionIdentifier;
	private int protocolIdentifier;
	private int unitIdentifier;
	private int dataLength;
	private byte[] dataBytes;
	private int functionCode;
	private Date date;

	public ResponseFrame() {

	}

	public ResponseFrame(int transactionIdentifier, int protocolIdentifier,
			int unitIdentifier) {
		super();
		this.transactionIdentifier = transactionIdentifier;
		this.protocolIdentifier = protocolIdentifier;
		this.unitIdentifier = unitIdentifier;
		date = new Date();
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

	private double zmiana() {
		ByteBuffer byteBuffer = ByteBuffer.wrap(dataBytes, 1, 4);
		return byteBuffer.getInt();
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

	public Date getDate() {
		return date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseFrame other = (ResponseFrame) obj;
		if (!Arrays.equals(dataBytes, other.dataBytes))
			return false;
		if (dataLength != other.dataLength)
			return false;
		if (functionCode != other.functionCode)
			return false;
		if (protocolIdentifier != other.protocolIdentifier)
			return false;
		if (transactionIdentifier != other.transactionIdentifier)
			return false;
		if (unitIdentifier != other.unitIdentifier)
			return false;
		return true;
	}
}
