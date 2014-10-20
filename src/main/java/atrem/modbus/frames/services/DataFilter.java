package atrem.modbus.frames.services;

import atrem.modbus.swing.Data;

public class DataFilter {
	private boolean time, value, register;

	public DataFilter(boolean time, boolean value, boolean register) {
		this.time = time;
		this.value = value;
		this.register = register;
	}

	public String filtrate(Data data) {
		String msg;
		if (time == true)
			msg += data.getTime() + "\t";
		;
		if (register == true)
			msg += data.getRegistryAddress() + "\t";
		if (value == true)
			msg += data.getRegistryValue() + "\t";
		return msg;
	}
}
