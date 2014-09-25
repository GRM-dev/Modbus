package atrem.modbus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private List<DeviceListener> deviceListeners;
	private Map requestMap;

	public Controller() {
		requestFrameFactory = new RequestFrameFactory();
		frameStorage = new FrameStorage();
		deviceListeners = new ArrayList<DeviceListener>();
		requestMap = new HashMap<Request, RequestHandler>();
	}

	public void addRequest(Request request, RequestHandler requestHandler) {
		requestMap.put(request, requestHandler);
	}

	private void onNewFrame(Request request, ResponseFrame responseFrame) {
		RequestHandler requestHandler = (RequestHandler) requestMap
				.get(request);
		requestHandler.frameReceiver(responseFrame);
	}

	public void setRequestFrameFactory(RequestFrameFactory requestFrameFactory) {
		this.requestFrameFactory = requestFrameFactory;
	}

	public void addDeviceListener(DeviceListener listener) {
		deviceListeners.add(listener);
	}

	private void onDevice(boolean isConnected) {
		for (DeviceListener deviceListener2 : deviceListeners) {
			deviceListener2.showConnectionStatus(isConnected);
		}
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
		System.out.println(responseFrame);//
	}

	public void startNewRequestTask(Request request) {
		RequestFrameFactory requestFrameFactory = new RequestFrameFactory(
				request);
		Timer timer = new Timer();
		timer.schedule(new Task(connection, requestFrameFactory, frameStorage),
				0, request.getScanRate());
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
