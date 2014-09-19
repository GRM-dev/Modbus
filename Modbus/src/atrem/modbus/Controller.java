package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.parsers.Coder;
import atrem.modbus.parsers.FrameDecoder;
import frames.RequestFrame;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();
	private Connection connection = Domino.createConnectionConstant();
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;
	private FrameStorage frameStorage = new FrameStorage();

	public Controller() {
		connection.receive(this);
	}

	public void pickUpBytes(byte[] bytes) { // TODO zmiana
														// nazwy
		FrameDecoder frameDecoder = new FrameDecoder();
		frameDecoder.receiveBytesFromController(bytes); // TODO zlikwidowac
														// rozbicie na 2 metody,
														// wywolac raz, inna
														// nazwa
		// System.out.println(frameDecoder.getNextModbusFrame());
		frameStorage.addReceivedFrame(frameDecoder.getNextModbusFrame());

	}

	public void addAndMakeRequest(int id) { // TODO zmiana nazwy, rozbicie na 2
											// metody,
		Coder coder = new Coder();
		requestFrameFactory.loadDefinedInformation();
		RequestFrame requestFrame = requestFrameFactory.createRequestFrame();
		System.out.println("id: " + requestFrame.getTransactionIdentifier());

		coder.codeFrame(requestFrame);
		timer = new Timer();
		timer.schedule(new Task(connection, coder.getFrameAsBytes(), id,
				requestFrame, frameStorage), 0, 2000); // wysy³anie
		// co
		// 2
		// sekundy
		tasks.add(timer);

	}
}
