package atrem.modbus;

import javax.swing.SwingUtilities;

import atrem.modbus.consoleService.ConsoleInputService;
import atrem.modbus.consoleService.ConsoleOutputService;
import atrem.modbus.frameServices.FramePairs;
import atrem.modbus.frameServices.RequestFrameFactory;
import atrem.modbus.parsers.ErrorsBox;
import atrem.modbus.swing.ModbusSwing;

public class Domino {

	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;
	private static Controller controller;
	ModbusSwing modbusSwing;
	private static RequestFrameFactory requestFrameFactory = new RequestFrameFactory();

	public static void main(String[] args) {

		Domino domino = new Domino();
		domino.runModbusGUI();
	}

	public Domino() {

		// Controller controller = new Controller();
		// controller.startConnection("10.7.7.121", 502);
		// controller.startNewRequestTask(1);

	}

	private void runModbusGUI() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				modbusSwing = new ModbusSwing(Domino.this);

			}
		});
	}

	Domino(Connection connection, Controller controller) {

		controller.setConnection(connection);
		controller.startNewRequestTask(1);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				modbusSwing = new ModbusSwing(Domino.this);

			}
		});

		// Controller controller = new Controller();
		// controller.startConnection("10.7.7.121", 502);
		// controller.startNewRequestTask(1);

	}

	public static Connection createConnection() {
		consoleOutput.askTcpIpAdress();
		ip = consoleInput.insertTcpIpAdres();

		consoleOutput.askPort();
		port = consoleInput.insertPort();

		return new Connection(ip, port, controller);
	}

	public static Connection createConnectionConstant() {

		ip = "10.7.7.121";
		port = 502;
		return new Connection(ip, port, controller);

	}

	public void receiveConnectionParameters(String ip, int port) {

		controller = new Controller(this);
		controller.startConnection(ip, port);
		requestFrameFactory = controller.getRequestFrameFactory();

	}

	public static Connection createConnectionSwing() {
		return new Connection(ip, port, controller);
	}

	public static void showRequestAndResponse(FramePairs framePairs) {
		System.out.println(framePairs);
	}

	public void creatRequestFrameFactory(int unitIdentifier,
			int startingAdress, int quantityOfRegisters, int functionCode) {

		requestFrameFactory.setQuantityOfRegisters(quantityOfRegisters);
		requestFrameFactory.setStartingAdress(startingAdress);
		requestFrameFactory.setUnitIdentifier(unitIdentifier);
		controller.startNewRequestTask(0); // TODO nie wiem o co loto pawel
	}

	public void showConnextionError() {
		ErrorsBox error = new ErrorsBox();
		error.ConnectionError();
	}

	public Controller getController() {
		return controller;
	}
}
