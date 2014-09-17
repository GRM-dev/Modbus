package atrem.modbus;

import atrem.modbus.parsers.FrameDecoder;
import atrem.modbus.parsers.Koder;
import consoleService.ConsoleInputService;
import consoleService.ConsoleOutputService;
import frames.ResponseFrame;

public class Domino {

	private static Connection connection;
	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;

	public static void main(String[] args) {
		connection = createConnectionConstant();
		RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
		requestFrameFactory.loadDefinedInformation();
		Koder koder = new Koder();
		koder.code(requestFrameFactory.createRequestFrame());
		connection.send(koder.changeListToArray());
		FrameDecoder decoder = new FrameDecoder(connection.getInStream());
		ResponseFrame frameIncoming;
		frameIncoming = decoder.getNextModbusFrame();
		System.out.println(frameIncoming);

	}

	public static Connection createConnection() {
		consoleOutput.askTcpIpAdress();
		ip = consoleInput.insertTcpIpAdres();

		consoleOutput.askPort();
		port = consoleInput.insertPort();

		return new Connection(ip, port);// lll
	}

	public static Connection createConnectionConstant() {

		ip = "10.7.7.121";

		port = 502;

		return new Connection(ip, port);// lll
	}

}
