package atrem.modbus;

import atrem.modbus.frames.RequestFrame;

public class Request {

	private int unitIdentifier;
	private int functionCode;
	private int starttingAdress;
	private int quantityOfRegisters;
	private int scanRate;

	public int compareFrames(Request r, RequestFrame rf) {

		if (!(r != null && rf != null))
			return -1;
		if (starttingAdress != rf.getStartingAdress())
			return -1;
		if (functionCode != rf.getFunctionCode())
			return -1;
		if (quantityOfRegisters != rf.getQuantityOfRegisters())
			return -1;
		if (unitIdentifier != rf.getUnitIdentifier())
			return -1;

		return 1;

	}

	public Request(int unitIdentifier, int functionCode,
			int firstRegistryAddress, int quantityOfRegisters, int scanRate) {
		this.unitIdentifier = unitIdentifier;
		this.functionCode = functionCode;
		this.starttingAdress = firstRegistryAddress;
		this.quantityOfRegisters = quantityOfRegisters;
		this.scanRate = scanRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + starttingAdress;
		result = prime * result + functionCode;
		result = prime * result + quantityOfRegisters;
		result = prime * result + scanRate;
		result = prime * result + unitIdentifier;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (starttingAdress != other.starttingAdress)
			return false;
		if (functionCode != other.functionCode)
			return false;
		if (quantityOfRegisters != other.quantityOfRegisters)
			return false;
		if (unitIdentifier != other.unitIdentifier)
			return false;
		return true;
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

	public int getFirstRegistryAddress() {
		return starttingAdress;
	}

	public void setFirstRegistryAddress(int firstRegistryAddress) {
		this.starttingAdress = firstRegistryAddress;
	}

	public int getQuantityOfRegisters() {
		return quantityOfRegisters;
	}

	public void setQuantityOfRegisters(int quantityOfRegisters) {
		this.quantityOfRegisters = quantityOfRegisters;
	}

	public int getScanRate() {
		return scanRate;
	}

	public void setScanRate(int scanRate) {
		this.scanRate = scanRate;
	}

}
