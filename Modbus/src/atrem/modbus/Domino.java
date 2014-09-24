package atrem.modbus;

import java.io.IOException;

import javafx.embed.swing.JFXPanel;

import javax.swing.SwingUtilities;

import atrem.modbus.frameServices.FramePairs;
import atrem.modbus.frameServices.RequestFrameFactory;
import atrem.modbus.swing.ModbusSwing;

public class Domino {
	
	private Controller					controller;
	ModbusSwing							modbusSwing;
	
	private static RequestFrameFactory	requestFrameFactory	= new RequestFrameFactory();	// TODO
																							// nie
																							// ma
																							// byc
																							// statyczne
																							
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
				modbusSwing = new ModbusSwing(Domino.this); // TODO do metody
															// init
				new JFXPanel();
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
	
	public void connect(String ip, int port) // TODO wyjatek obslugiwany w
												// prezenterze
			throws IOException {
		
		controller = new Controller(this);
		controller.startConnection(ip, port);
		
		requestFrameFactory = controller.getRequestFrameFactory(); // TODO
																	// raczej
																	// niepotrzebne
		
	}
	
	public static void showRequestAndResponse(FramePairs framePairs) {
		System.out.println(framePairs);
	}
	
	public void creatRequestFrameFactory(int unitIdentifier, // TODO to tez nie
																// bardzo
			int startingAdress, int quantityOfRegisters, int functionCode) {
		
		requestFrameFactory.setQuantityOfRegisters(quantityOfRegisters);
		requestFrameFactory.setStartingAdress(startingAdress);
		requestFrameFactory.setUnitIdentifier(unitIdentifier);
		controller.startNewRequestTask(0); // TODO nie wiem o co loto pawel
	}
	
	public ModbusSwing getModbusSwing() { // TODO do wywalenia
		return modbusSwing;
	}
	
	public void showConnectionStatus(boolean isConnected) { // TODO ewentualnie
															// przez interfejs
		if (isConnected)
			modbusSwing.setStatus("CONNECTED!");
		else
			modbusSwing.setStatus("NOT CONNECTED");
	}
}
