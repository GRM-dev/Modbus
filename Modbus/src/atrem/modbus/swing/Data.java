package atrem.modbus.swing;

public class Data {
	private int registryAddress;

	public Data(int registryAddress, int registryValue) {
		this.registryAddress = registryAddress;
		this.registryValue = registryValue;
	}

	private int registryValue;

	public int getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(int registryAddress) {
		this.registryAddress = registryAddress;
	}

	public int getRegistryValue() {
		return registryValue;
	}

	public void setRegistryValue(int registryValue) {
		this.registryValue = registryValue;
	}
}
