package atrem.modbus;

import ConsoleService.ConsoleInputService;
import ConsoleService.ConsoleOutputService;

public class Domino {

	private ModbusFrame modbusFrame;
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

	}

	public static Connection createConnection() {
		consoleOutput.askTcpIpAdress();
		ip = consoleInput.insertTcpIpAdres();

		consoleOutput.askPort();
		port = consoleInput.insertPort();

		return new Connection(ip, port);// lll
	}
}
