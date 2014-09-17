package atrem.modbus;

import ConsoleService.ConsoleInputService;
import ConsoleService.ConsoleOutputService;

public class Domino {

	static private ModbusFrame modbusFrame;
	private static Connection connection;
	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;

	public static void main(String[] args) {
		consoleInput = new ConsoleInputService();
		consoleOutput = new ConsoleOutputService();
		connection = createConnection();
		consoleOutput.showConnectionStatus(connection.checkConnection());
		modbusFrame = new ModbusFrame();
		createFrame();
		Koder koder = new Koder();
		koder.code(modbusFrame);
		connection.send(koder.changeListToArray());
		FrameDecoder decoder = new FrameDecoder(connection.getInStream());
		decoder.getNextModbusFrame();

	}

	public static Connection createConnection() {
		consoleOutput.askTcpIpAdress();
		ip = consoleInput.insertTcpIpAdres();

		consoleOutput.askPort();
		port = consoleInput.insertPort();

		return new Connection(ip, port);// lll
	}

	public static void createFrame() {

		consoleOutput.askUnitIdentifier();
		modbusFrame.setUnitIdentifier((consoleInput.insertUnitIdentifier()));
		consoleOutput.askFunctionCode();
		modbusFrame.setFunctionCode(consoleInput.insertFunctionCode());
		consoleOutput.askStartingAdress();
		modbusFrame.setStartingAdress(consoleInput.insertFirstRegister());
		consoleOutput.askQuantityOfRegisters();
		modbusFrame.setQuantityOfRegisters(consoleInput
				.insertNumberOfRegisters());

	}
}
