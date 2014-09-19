package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.parsers.FrameDecoder;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();
	private Connection connection = Domino.createConnectionConstant();
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;
	private FrameStorage frameStorage = new FrameStorage();

	public Controller() {
		connection.startReceiveFrames(this);
	}

	public void pickUpBytes(byte[] bytes) {
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

		requestFrameFactory.loadDefinedInformation();
		timer = new Timer();
		timer.schedule(new Task(connection, requestFrameFactory, frameStorage),
				0, 2000); // wysy³anie
		// co
		// 2
		// sekundy
		tasks.add(timer);

	}

}
