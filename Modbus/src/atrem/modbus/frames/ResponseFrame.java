package atrem.modbus.frames;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import atrem.modbus.frames.services.FramesPrinter;

public class ResponseFrame {
	private int transactionIdentifier;
	private int protocolIdentifier;
	private int unitIdentifier;
	private int dataLength;
	private byte[] dataBytes;
	private int functionCode;
	private Date date;
	private SimpleDateFormat timeFormat;
	private String time;
	private int dataValue;
	private int registryValue;

	public ResponseFrame() {
		date = new Date();
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		time = timeFormat.format(date);
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

	public Date getDate() {
		return date;
	}

	public int getDataValue() {
		return dataValue;
	}

	public void setDataValue(int dataValue) {
		this.dataValue = dataValue;
	}

	public int getRegistryValue() {
		return registryValue;
	}

	public void setRegistryValue(int registryValue) {
		this.registryValue = registryValue;
	}

	@Override
	public String toString() {

		return "*ResponseFrame* " + time + FramesPrinter.NEW_LINE
				+ " [transactionIdentifier=" + transactionIdentifier
				+ ", protocolIdentifier=" + protocolIdentifier
				+ ", unitIdentifier=" + unitIdentifier + ", dataLength="
				+ dataLength + ", dataBytes=" + Arrays.toString(dataBytes)
				+ ", functionCode=" + functionCode + "]";
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
