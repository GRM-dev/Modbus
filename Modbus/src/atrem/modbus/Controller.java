package atrem.modbus;

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

	public Controller() {
		requestFrameFactory = new RequestFrameFactory();
		frameStorage = new FrameStorage();
	}

	public Controller(Domino domino) {
		this();
		this.domino = domino;
	}

	public void startConnection(String ipAddress, int port) {
		connection = new Connection(ipAddress, port, this);
		connection.startReceiveFrames(this);
	}

	public void loadBytesToDecoder(byte[] bytes) {
		FrameDecoder frameDecoder = new FrameDecoder();
		ResponseFrame responseFrame = frameDecoder
				.receiveBytesFromController(bytes);
		frameStorage.addReceivedFrame(responseFrame);
		frameStorage.makePairsOfFrames();
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

	public void takeConnectionExepction() {
		// domino.showConnextionError();
		connection.closeConnection();
		while (!connection.checkConnection()) {

			connection.makeConnection();
			System.out.println("jestem");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Connection getConnection() {
		return connection;
	}

}
