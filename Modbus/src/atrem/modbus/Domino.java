package atrem.modbus;

import java.awt.Color;
import java.io.IOException;

import javax.swing.SwingUtilities;

import atrem.modbus.frameServices.FramePairs;
import atrem.modbus.swing.ModbusSwing;

public class Domino {

	private Controller controller;
	ModbusSwing modbusSwing;

	public static void main(String[] args) {

		Domino domino = new Domino();
		domino.init();
	}

	public Domino() {
	}

	public Controller getController() {
		return controller;
	}

	private void init() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				modbusSwing = new ModbusSwing(Domino.this);
			}
		});
	}

	public void connect(String ip, int port) // TODO wyjatek obslugiwany w
												// prezenterze
			throws IOException {

		controller = new Controller();
		controller.addDeviceListener(new DeviceListener() {

			@Override
			public void showConnectionStatus(boolean isConnected) {
				showConnectionStatus1(isConnected);
			}
		});
		controller.startConnection(ip, port);

	}

	public static void showRequestAndResponse(FramePairs framePairs) {
		System.out.println(framePairs);
	}

	private void showConnectionStatus1(boolean isConnected) {
		if (isConnected)
			modbusSwing.setStatus("CONNECTED TO "
					+ controller.getConnection().getIpAddress(), new Color(0,
					255, 0));
		else
			modbusSwing.setStatus("DISCONNECTED", new Color(255, 0, 0));
	}
}
