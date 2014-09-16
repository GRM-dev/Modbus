package atrem.modbus;

import java.util.ArrayList;

public class FrameData {
	ArrayList<Byte> content = new ArrayList<Byte>();
	private int firstRegister;
	private int numberOfRegisters;

	public int getFirstRegister() {
		return firstRegister;
	}

	public void setFirstRegister(int firstRegister) {
		this.firstRegister = firstRegister;
	}

	public int getNumberOfRegisters() {
		return numberOfRegisters;
	}

	public void setNumberOfRegisters(int numberOfRegisters) {
		this.numberOfRegisters = numberOfRegisters;
	}

}
