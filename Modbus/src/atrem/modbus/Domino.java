package atrem.modbus;

import java.awt.Color;
import java.io.IOException;

import javax.swing.SwingUtilities;

import atrem.modbus.frameServices.FramePairs;
import atrem.modbus.frameServices.RequestFrameFactory;
import atrem.modbus.swing.ModbusSwing;

public class Domino {

	private Controller controller;
	ModbusSwing modbusSwing;

	private RequestFrameFactory requestFrameFactory;

	public static void main(String[] args) {

		Domino domino = new Domino();
		domino.init();
	}

	public Controller getController() {
		return controller;
	}

	public Domino() {
		requestFrameFactory = new RequestFrameFactory();

		// Controller controller = new Controller();
		// controller.startConnection("10.7.7.121", 502);
		// controller.startNewRequestTask(1);

	}

	private void init() {
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

	public void connect(String ip, int port) // TODO wyjatek obslugiwany w
												// prezenterze
			throws IOException {

		controller = new Controller(this);
		controller.addDeviceListener(new DeviceListener() {

			@Override
			public void showConnectionStatus(boolean isConnected) {
				showConnectionStatus1(isConnected);
			}
		});
		controller.startConnection(ip, port);

		requestFrameFactory = controller.getRequestFrameFactory(); // TODO
																	// raczej
																	// niepotrzebne

	}

	public static void showRequestAndResponse(FramePairs framePairs) {
		System.out.println(framePairs);
	}

	public void createRequestFrameFactory(int unitIdentifier, // TODO to tez nie
																// bardzo
			int startingAdress, int quantityOfRegisters, int functionCode) {
		RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
		requestFrameFactory.setQuantityOfRegisters(quantityOfRegisters);
		requestFrameFactory.setStartingAdress(startingAdress);
		requestFrameFactory.setUnitIdentifier(unitIdentifier);
		controller.setRequestFrameFactory(requestFrameFactory);
		controller.startNewRequestTask(0); // TODO nie wiem o co loto pawel
	}

	public ModbusSwing getModbusSwing() { // TODO do wywalenia
		return modbusSwing;
	}

	private void showConnectionStatus1(boolean isConnected) {
		if (isConnected)
			modbusSwing.setStatus("CONNECTED!", new Color(0, 255, 0));
		else
			modbusSwing.setStatus("NOT CONNECTED", new Color(255, 0, 0));
	}
}
