package atrem.modbus;

import consoleService.ConsoleInputService;
import consoleService.ConsoleOutputService;
import frames.RequestFrame;
import frames.ResponseFrame;

public class Domino {

	static private RequestFrame modbusFrame;
	private static Connection connection;
	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;

	public static void main(String[] args) {
		consoleInput = new ConsoleInputService();
		consoleOutput = new ConsoleOutputService();
		connection = createConnectionConstant();
		consoleOutput.showConnectionStatus(connection.checkConnection());
		modbusFrame = new RequestFrame();
		createFrameConstant();
		Koder koder = new Koder();
		// while (true) {
		koder.code(modbusFrame);
		connection.send(koder.changeListToArray());
		FrameDecoder decoder = new FrameDecoder(connection.getInStream());
		ResponseFrame frameIncoming;
		frameIncoming = decoder.getNextModbusFrame();
		System.out.println(frameIncoming);
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

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

	public static void createFrameConstant() {

		modbusFrame.setUnitIdentifier(5);

		modbusFrame.setFunctionCode(3);

		modbusFrame.setStartingAdress(5027);

		modbusFrame.setQuantityOfRegisters(2);

	}

	public static Connection createConnectionConstant() {

		ip = "10.7.7.243";

		port = 502;

		return new Connection(ip, port);// lll
	}

}
