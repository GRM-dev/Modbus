package atrem.modbus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import atrem.modbus.frames.RequestFrame;
import atrem.modbus.frames.ResponseFrame;
import atrem.modbus.frames.services.FramePairs;
import atrem.modbus.frames.services.FrameStorage;
import atrem.modbus.frames.services.RequestFrameFactory;
import atrem.modbus.parsers.FrameDecoder;

public class ControllerImpl implements Controller {

	private Map tasks;
	private Connection connection;
	private RequestFrameFactory requestFrameFactory;
	private FrameStorage frameStorage;
	private static final long PEROID = 2000;
	private List<DeviceListener> deviceListeners;
	private Map requestMap;
	private List<Request> requestList;

	public ControllerImpl() {
		requestFrameFactory = new RequestFrameFactory();
		frameStorage = new FrameStorage();
		deviceListeners = new ArrayList<DeviceListener>();
		requestMap = new HashMap<Request, InterFrameService>();
		tasks = new HashMap<Request, Timer>();
		requestList = new ArrayList<Request>();
		addToframeStorage();
	}

	private void addToframeStorage() {
		frameStorage.addPairedFrameListener(new PairedFrameListener() {

			@Override
			public void onPairFrame(FramePairs framePairs) {
				RequestFrame requestFrame = framePairs.getRequestFrame();
				for (Request requestTmp : requestList) {
					if (requestTmp.compareFrames(requestFrame)) {
						onNewFrame(requestTmp, framePairs.getResponseFrame());
					}
				}
			}
		});
	}

	private void onNewFrame(Request request, ResponseFrame responseFrame) {
		InterFrameService interFrameService = (InterFrameService) requestMap
				.get(request);
		interFrameService.addResponseFrame(responseFrame);
	}

	public void setRequestFrameFactory(RequestFrameFactory requestFrameFactory) {
		this.requestFrameFactory = requestFrameFactory;
	}

	@Override
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
		onDevice(connection.checkConnection());
		// TODO usunac, dodac listenera
		connection.startReceiveFrames(); //
	}

	public void loadBytes(byte[] bytes) {
		FrameDecoder frameDecoder = new FrameDecoder();
		ResponseFrame responseFrame = frameDecoder.decodeBytes(bytes);
		frameStorage.addReceivedFrame(responseFrame);
		frameStorage.makePairsOfFrames();
		System.out.println(responseFrame);//
	}

	@SuppressWarnings("unchecked")
	private void startNewRequestTask(Request request) {
		RequestFrameFactory requestFrameFactory = new RequestFrameFactory(
				request);
		Timer timer = new Timer();
		timer.schedule(new Task(connection, requestFrameFactory, frameStorage),
				0, request.getScanRate());
		tasks.put(request, timer);
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	FrameStorage getFrameStorage() {
		return frameStorage;
	}

	public RequestFrameFactory getRequestFrameFactory() {
		return requestFrameFactory;
	}

	@Override
	public void addRequestHandler(Request request,
			InterFrameService interFrameService) {
		requestMap.put(request, interFrameService);
	}

	@Override
	public void removeRequest(Request request) {
		requestDelete(request);

	}

	@Override
	public void pauseRequest(Request request) {
		requestDelete(request);
	}

	@Override
	public void startRequest(Request request) {
		requestList.add(request);
		startNewRequestTask(request);
	}

	private Timer getTask(Request request) {
		return (Timer) tasks.get(request);
	}

	private void requestDelete(Request request) {
		Timer timer = getTask(request);
		timer.cancel();
		tasks.remove(request);
		requestList.remove(request);
	}

}
