package atrem.modbus;

public class ModbusFrame {

	private String slaveAdress;
	private String functionCode;
	private String idTCP;
	FrameData content;

	public String getSlaveAdress() {
		return slaveAdress;
	}

	public void setSlaveAdress(String slaveAdress) {
		this.slaveAdress = slaveAdress;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getIdTCP() {
		return idTCP;
	}

	public void setIdTCP(String idTCP) {
		this.idTCP = idTCP;
	}

	public FrameData getContent() {
		return content;
	}

	public void setContent(FrameData content) {
		this.content = content;
	}

}
