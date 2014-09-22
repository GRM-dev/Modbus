package atrem.modbus.frames;

import java.util.Date;

public class RequestFrame {

	private int transactionIdentifier;
	private int protocolIdentifier;
	private int lengthField;
	private int unitIdentifier;
	private int functionCode;
	private Date sendDate;

	public static final int HEADER_SIZE = 6;

	@Override
	public String toString() {
		return "RequestFrame [transactionIdentifier=" + transactionIdentifier
				+ ", protocolIdentifier=" + protocolIdentifier
				+ ", lengthField=" + lengthField + ", unitIdentifier="
				+ unitIdentifier + ", functionCode=" + functionCode
				+ ", startingAdress=" + startingAdress
				+ ", quantityOfRegisters=" + quantityOfRegisters + "]";
	}

	private int startingAdress;
	private int quantityOfRegisters;

	public RequestFrame(int transactionIdentifier, int unitIdentifier,
			int functionCode, int startingAdress, int quantityOfRegisters) {
		this.transactionIdentifier = transactionIdentifier;
		this.unitIdentifier = unitIdentifier;
		this.functionCode = functionCode;
		this.quantityOfRegisters = quantityOfRegisters;
		this.startingAdress = startingAdress;
		sendDate = new Date();

	}

	public int getStartingAdress() {
		return startingAdress;
	}

	public void setStartingAdress(int startingAdress) {
		this.startingAdress = startingAdress;
	}

	public int getQuantityOfRegisters() {
		return quantityOfRegisters;
	}

	public void setQuantityOfRegisters(int quantityOfRegisters) {
		this.quantityOfRegisters = quantityOfRegisters;
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

	public Date getSendDate() {
		return sendDate;
	}

	public boolean equals(ResponseFrame responseFrame) {
		if (responseFrame == null)
			return false;
		if (functionCode != responseFrame.getFunctionCode())
			return false;
		if (protocolIdentifier != responseFrame.getProtocolIdentifier())
			return false;
		if (transactionIdentifier != responseFrame.getTransactionIdentifier())
			return false;
		if (unitIdentifier != responseFrame.getUnitIdentifier())
			return false;
		return true;
	}

}
