package atrem.modbus;

import java.util.Random;
import java.util.TimerTask;

import frames.RequestFrame;

public class Task extends TimerTask {

	private Connection connection;
	private byte[] bytes;
	private int id;
	private int transactionId;
	private Random rand;
	private FrameStorage frameStorage;
	private RequestFrame requestFrame;

	public Task(Connection connection, byte[] bytes, int id,
			RequestFrame requestFrame, FrameStorage frameStorage) {
		this.connection = connection;
		this.bytes = bytes;
		this.id = id;
		this.requestFrame = requestFrame;
		rand = new Random();
		this.frameStorage = frameStorage;

	}

	@Override
	public void run() {
		transactionId = rand.nextInt(100);
		for (int i = 1; i >= 0; i--) {
			bytes[i] = (byte) (transactionId >>> (i * 8));
		}
		System.out.println("bytes id" +);
		requestFrame.setTransactionIdentifier(transactionId);
		frameStorage.addSentFrame(requestFrame);
		if (!frameStorage.isWorking()) {
			frameStorage.makePairsOfFrames();
			System.out.println("lll");
		}
		connection.send(bytes);
		System.out.println("wyslalem " + id);

	}
}
