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
	private static final long PEROID = 2000;
	private Domino domino;

	public void startConnection(String ipAddress, int port) {
		connection = new Connection(ipAddress, port, this);
		connection.startReceiveFrames(this);
	}

	public Controller(Domino domino) {
		this.domino = domino;
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
