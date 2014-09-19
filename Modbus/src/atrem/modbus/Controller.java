package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.parsers.FrameDecoder;
import frames.ResponseFrame;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();
	private Connection connection;
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;
	private FrameStorage frameStorage = new FrameStorage();

	public void startConnection(String ipAddress, int port) {
		connection = new Connection(ipAddress, port);
		connection.startReceiveFrames(this);
	}

	public void loadBytesToDecoder(byte[] bytes) {
		FrameDecoder frameDecoder = new FrameDecoder();
		ResponseFrame responseFrame = frameDecoder
				.receiveBytesFromController(bytes);
		frameStorage.addReceivedFrame(responseFrame);
		frameStorage.compare();
		System.out.println(responseFrame);// TODO chwilowe wyswietlenie ramki
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

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	FrameStorage getFrameStorage() {
		return frameStorage;
	}
}
