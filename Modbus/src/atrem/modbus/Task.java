package atrem.modbus;

import java.util.TimerTask;

import atrem.modbus.frameServices.FrameStorage;
import atrem.modbus.frameServices.RequestFrameFactory;
import atrem.modbus.frames.RequestFrame;
import atrem.modbus.parsers.Coder;

public class Task extends TimerTask {

	private Connection connection;
	private FrameStorage frameStorage;
	private RequestFrameFactory requestFrameFactory;
	private Coder coder = new Coder();

	public Task(Connection connection, RequestFrameFactory requestFrameFactory, // TODO
																				// tu
																				// gdzies
																				// new
																				// factory

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
		System.out.println("wyslalem "
				+ requestFrame.getTransactionIdentifier());

	}
}
