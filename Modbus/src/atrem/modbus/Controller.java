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

	private List<Timer> tasks = new ArrayList<Timer>(); // TODO moze inna nazwa

	private Connection connection;
	private RequestFrameFactory requestFrameFactory;
	private FrameStorage frameStorage;
	private static final long PEROID = 2000;
	private Domino domino; // TODO usunac
	private List<RequestHandler> controllerListener;
	private List<DeviceListener> deviceListeners;

	public Controller() {
		requestFrameFactory = new RequestFrameFactory();
		frameStorage = new FrameStorage();
		controllerListener = new ArrayList<RequestHandler>();
		deviceListeners = new ArrayList<DeviceListener>();
	}

	public Domino getDomino() {
		return domino;
	}

	public void setRequestFrameFactory(RequestFrameFactory requestFrameFactory) {
		this.requestFrameFactory = requestFrameFactory;
	}

	public void addListener(RequestHandler listener) {
		controllerListener.add(listener);
	}

	public void addDeviceListener(DeviceListener listener) {
		deviceListeners.add(listener);
	}

	private void onFrame(ResponseFrame responseFrame) {
		for (RequestHandler controllerListener2 : controllerListener) {
			controllerListener2.frameReceiver(responseFrame);
		}
	}

	private void onDevice(boolean isConnected) {
		for (DeviceListener deviceListener2 : deviceListeners) {
			deviceListener2.showConnectionStatus(isConnected);
		}
	}

	public Controller(Domino domino) {
		this();
		this.domino = domino;
	}

	public void startConnection(String ipAddress, int port) throws IOException {
		connection = new Connection(ipAddress, port, this);
		onDevice(connection.checkConnection()); // TODO
												// usunac,
												// dodac
												// listenera
		connection.startReceiveFrames(); //
	}

	public void loadBytes(byte[] bytes) {
		FrameDecoder frameDecoder = new FrameDecoder();
		ResponseFrame responseFrame = frameDecoder.decodeBytes(bytes);
		frameStorage.addReceivedFrame(responseFrame);
		frameStorage.makePairsOfFrames();
		onFrame(frameStorage.getLastResponseFrame());
		System.out.println(responseFrame);//
	}

	public void startNewRequestTask(int id) { // TODO tu raczej obiekt Request
		requestFrameFactory.loadDefinedInformation();
		Timer timer = new Timer();
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
