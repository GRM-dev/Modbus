package atrem.modbus;

import java.util.Random;
import java.util.TimerTask;

import atrem.modbus.parsers.Coder;
import frames.RequestFrame;

public class Task extends TimerTask {

	private Connection connection;
	private byte[] bytes;
	private int id;
	private int transactionId;
	private Random rand;
	private FrameStorage frameStorage;
	private RequestFrameFactory requestFrameFactory;
	private Coder coder = new Coder();

	public Task(Connection connection, RequestFrameFactory requestFrameFactory,
			FrameStorage frameStorage) {
		this.connection = connection;
		this.frameStorage = frameStorage;
		this.requestFrameFactory = requestFrameFactory;

	}

	@Override
	public void run() {
		byte[] bytes;
		RequestFrame requestFrame = requestFrameFactory.createRequestFrame();
		coder.codeFrame(requestFrame);
		bytes = coder.getFrameAsBytes();
		connection.send(bytes);
		frameStorage.addSentFrame(requestFrame);

		if (!frameStorage.isWorking()) {
			frameStorage.makePairsOfFrames();

		}

		System.out.println("wyslalem " + id);

	}
}
