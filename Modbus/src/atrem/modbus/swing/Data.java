package atrem.modbus.swing;

import java.text.SimpleDateFormat;

import atrem.modbus.frames.ResponseFrame;

public class Data {

	private int registryAddress;
	private int registryValue;
	private SimpleDateFormat dataFormat;
	private String time;

	public Data(ResponseFrame responseFrame) {
		dataFormat = new SimpleDateFormat("HH-mm-ss");
		time = this.registryAddress = responseFrame.getRegistryValue();
		this.registryValue = responseFrame.getDataValue();
	}

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

	@Override
	public String toString() {
		return "Data [registryAddress=" + registryAddress + ", registryValue="
				+ registryValue + "]";
	}

}
