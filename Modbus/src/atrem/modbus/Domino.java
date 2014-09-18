package atrem.modbus;

import consoleService.ConsoleInputService;
import consoleService.ConsoleOutputService;

public class Domino {

	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;

	public static void main(String[] args) {
		Controller controller = new Controller();

		controller.addAndMakeRequest(69);

	}

	public static Connection createConnection() {
		consoleOutput.askTcpIpAdress();
		ip = consoleInput.insertTcpIpAdres();

		consoleOutput.askPort();
		port = consoleInput.insertPort();

		return new Connection(ip, port);
	}

	public static Connection createConnectionConstant() {

		ip = "10.7.7.121";

		port = 502;

		return new Connection(ip, port);
	}

	public static void showRequestAndResponse(FramePairs framePairs) {
		System.out.println(framePairs);
	}

}
