package atrem.modbus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.frameServices.FrameStorage;
import atrem.modbus.frameServices.RequestFrameFactory;
import atrem.modbus.frames.ResponseFrame;
import atrem.modbus.parsers.FrameDecoder;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();

	private Connection connection;
	private RequestFrameFactory requestFrameFactory;
	private Timer timer;
	private FrameStorage frameStorage;
	private static final long PEROID = 2000;
	private Domino domino;
	private List<ControllerListener> controllerListener;

	public Controller() {
		requestFrameFactory = new RequestFrameFactory();
		frameStorage = new FrameStorage();
		controllerListener = new ArrayList<ControllerListener>();
	}

	public Domino getDomino() {
		return domino;
	}

	public void setRequestFrameFactory(RequestFrameFactory requestFrameFactory) {
		this.requestFrameFactory = requestFrameFactory;
	}

	public void addListener(ControllerListener listener) {
		controllerListener.add(listener);
	}

	private void onFrame(ResponseFrame responseFrame) {
		for (ControllerListener controllerListener2 : controllerListener) {
			controllerListener2.frameReceiver(responseFrame);
		}
	}

	public Controller(Domino domino) {
		this();
		this.domino = domino;
	}

	public void startConnection(String ipAddress, int port) throws IOException {
		connection = new Connection(ipAddress, port, this);
		domino.showConnectionStatus(connection.checkConnection());
		connection.startReceiveFrames(this);
	}

	public void loadBytesToDecoder(byte[] bytes) {
		FrameDecoder frameDecoder = new FrameDecoder();
		ResponseFrame responseFrame = frameDecoder
				.receiveBytesFromController(bytes);
		frameStorage.addReceivedFrame(responseFrame);
		frameStorage.makePairsOfFrames();
		onFrame(frameStorage.getLastResponseFrame());
		System.out.println(responseFrame);//
	}

	public void startNewRequestTask(int id) {

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

	public RequestFrameFactory getRequestFrameFactory() {
		return requestFrameFactory;
	}

}
