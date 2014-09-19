package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.parsers.Coder;
import atrem.modbus.parsers.FrameDecoder;
import frames.RequestFrame;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();
	private Connection connection;
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;
	private FrameStorage frameStorage = new FrameStorage();

	public Controller() {

	}

	public void startConnection(String ipAddress, int port) {
		connection = new Connection(ipAddress, port);
	}

	public void pickUpBytes(byte[] bytes) {
		FrameDecoder frameDecoder = new FrameDecoder();
		frameDecoder.receiveBytesFromController(bytes); // TODO zlikwidowac
														// rozbicie na 2 metody,
														// wywolac raz, inna
														// nazwa
	}

	public void addAndMakeRequest(int id) { // TODO zmiana nazwy, rozbicie na 2
											// metody,

		requestFrameFactory.loadDefinedInformation();
		RequestFrame requestFrame = requestFrameFactory.createRequestFrame();

		// frameStorage.addSentFrame(requestFrame);
		Coder coder = new Coder();

		coder.codeFrame(requestFrame);
		timer = new Timer();
		timer.schedule(new Task(connection, coder.getFrameAsBytes(), id,
				requestFrame, frameStorage), 0, 2000); // wysy³anie
		// co
		// 2
		// sekundy
		tasks.add(timer);

	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// public void scheduleRequest(Coder coder) {
	// timer = new Timer();
	// timer.schedule(new Task(connection, coder.getFrameAsBytes(), id), 0,
	// 2000); // wysy³anie
	// // co
	// // 2
	// // sekundy
	// tasks.add(timer);
	// }
}
