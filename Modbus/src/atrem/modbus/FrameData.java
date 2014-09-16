package atrem.modbus;


public class FrameData {
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
