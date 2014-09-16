package atrem.modbus;

public class ModbusFrame {

	private char slaveAdress, functionCode;
	private int idTCP;

	public char getSlaveAdress() {
		return slaveAdress;
	}

	public void setSlaveAdress(char slaveAdress) {
		this.slaveAdress = slaveAdress;
	}

	public char getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(char functionCode) {
		this.functionCode = functionCode;
	}

	public int getIdTCP() {
		return idTCP;
	}

	public void setIdTCP(int idTCP) {
		this.idTCP = idTCP;
	}

	public FrameData getContent() {
		return content;
	}

	public void setContent(FrameData content) {
		this.content = content;
	}

	FrameData content;
}
