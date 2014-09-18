package frames;

public class RequestFrame {

	private int transactionIdentifier;
	private int protocolIdentifier;
	private int lengthField;
	private int unitIdentifier;
	private int functionCode;

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
		System.out.println("transactionID:" + transactionIdentifier);
		this.unitIdentifier = unitIdentifier;
		this.functionCode = functionCode;
		this.quantityOfRegisters = quantityOfRegisters;
		this.startingAdress = startingAdress;

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

}
