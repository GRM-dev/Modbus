package atrem.modbus;

import java.util.TimerTask;

import atrem.modbus.frames.RequestFrame;
import atrem.modbus.frames.services.FrameStorage;
import atrem.modbus.frames.services.RequestFrameFactory;
import atrem.modbus.parsers.Coder;

/**
 * Task executing by the time shedule.
 */
public class Task extends TimerTask {

	private Connection connection;
	private FrameStorage frameStorage;
	private RequestFrameFactory requestFrameFactory;
	private Coder coder;

	public Task(Connection connection, RequestFrameFactory requestFrameFactory,
			FrameStorage frameStorage) {
		this.connection = connection;
		this.frameStorage = frameStorage;
		this.requestFrameFactory = requestFrameFactory;
		coder = new Coder();
	}

	@Override
	public void run() {
		byte[] bytes;
		RequestFrame requestFrame = requestFrameFactory.createRequestFrame();
		coder.codeFrame(requestFrame);
		bytes = coder.getFrameAsBytes();
		connection.send(bytes);
		frameStorage.addSentFrame(requestFrame);
		System.out.println("wyslalem "
				+ requestFrame.getTransactionIdentifier());

	}
}
