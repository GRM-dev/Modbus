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

	public RequestFrameFactory getRequestFrameFactory() {
		return requestFrameFactory;
	}

	private Timer timer;
	private FrameStorage frameStorage = new FrameStorage();
	private static final long PEROID = 2000;

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

	}

	public void startNewRequestTask(int id) { // TODO zmiana nazwy, rozbicie na
												// 2
												// metody,

		requestFrameFactory.loadDefinedInformation();
		timer = new Timer();
		timer.schedule(new Task(connection, requestFrameFactory, frameStorage),

		0, PEROID);

		tasks.add(timer);

	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	FrameStorage getFrameStorage() {
		return frameStorage;
	}
}
