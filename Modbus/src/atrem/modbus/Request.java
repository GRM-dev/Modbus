package atrem.modbus;

public class Request {

	private int unitIdentifier;
	private int functionCode;
	private int firstRegistryAddress;
	private int quantityOfRegisters;
	private int scanRate;

	public Request(int unitIdentifier, int functionCode,
			int firstRegistryAddress, int quantityOfRegisters, int scanRate) {
		this.unitIdentifier = unitIdentifier;
		this.functionCode = functionCode;
		this.firstRegistryAddress = firstRegistryAddress;
		this.quantityOfRegisters = quantityOfRegisters;
		this.scanRate = scanRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstRegistryAddress;
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
		if (firstRegistryAddress != other.firstRegistryAddress)
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
		return firstRegistryAddress;
	}

	public void setFirstRegistryAddress(int firstRegistryAddress) {
		this.firstRegistryAddress = firstRegistryAddress;
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
