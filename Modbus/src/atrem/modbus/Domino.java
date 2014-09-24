package atrem.modbus;

import java.io.IOException;

import javax.swing.SwingUtilities;

import atrem.modbus.consoleService.ConsoleInputService;
import atrem.modbus.consoleService.ConsoleOutputService;
import atrem.modbus.frameServices.FramePairs;
import atrem.modbus.frameServices.RequestFrameFactory;
import atrem.modbus.swing.ModbusSwing;

public class Domino {

	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;
	private Connection connection;
	private Controller controller;
	ModbusSwing modbusSwing;

	private static RequestFrameFactory requestFrameFactory = new RequestFrameFactory();

	public static void main(String[] args) {

		Domino domino = new Domino();

	}

	public Controller getController() {
		return controller;
	}

	public Domino() {
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

	public void receiveConnectionParameters(String ip, int port)
			throws IOException {

		controller = new Controller(this);
		controller.startConnection(ip, port);

		requestFrameFactory = controller.getRequestFrameFactory();

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

	public ModbusSwing getModbusSwing() {
		return modbusSwing;
	}

	public void showConnectionStatus(boolean isConnected) {
		if (isConnected)
			modbusSwing.setStatus("CONNECTED!");
		else
			modbusSwing.setStatus("NOT CONNECTED");
	}
}
