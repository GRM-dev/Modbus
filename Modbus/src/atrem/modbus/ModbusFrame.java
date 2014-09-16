package atrem.modbus;

public class ModbusFrame {

	private String slaveAdress;
	private String functionCode;
	private String idTCP;
	private int slaveAdress, functionCode, idTCP;
	FrameData content;

	public int getSlaveAdress() {
		return slaveAdress;
	}

	public void setSlaveAdress(int slaveAdress) {
		this.slaveAdress = slaveAdress;
	}

	public int getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(int functionCode) {
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

}
