package atrem.modbus;

import javax.swing.SwingUtilities;

import swing.ModbusSwing;
import consoleService.ConsoleInputService;
import consoleService.ConsoleOutputService;

public class Domino {

	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;
	private Connection connection;
	private static Controller controller;
	ModbusSwing modbusSwing;
	private static RequestFrameFactory requestFrameFactory = new RequestFrameFactory();

	public static void main(String[] args) {
		Domino domino = new Domino();

	}

	public Domino() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				modbusSwing = new ModbusSwing(Domino.this);

			}
		});

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

	public static void receiveConnectionParameters(String ip2, int port2) {
		ip = ip2;
		port = port2;
		Connection connection = new Connection(ip, port);
		controller = new Controller();
		requestFrameFactory = controller.getRequestFrameFactory();
		if (connection.checkConnection())
			System.out.println("dzia�a");

	}

	public static Connection createConnectionSwing() {
		return new Connection(ip, port);
	}

	public static void showRequestAndResponse(FramePairs framePairs) {
		System.out.println(framePairs);
	}

	public void creatRequestFrameFactory(int unitIdentifier,
			int startingAdress, int quantityOfRegisters, int functionCode) {

		requestFrameFactory.setQuantityOfRegisters(quantityOfRegisters);
		requestFrameFactory.setStartingAdress(startingAdress);
		requestFrameFactory.setUnitIdentifier(unitIdentifier);
		controller.addAndMakeRequest();
	}

}
