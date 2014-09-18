package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.parsers.Coder;
import atrem.modbus.parsers.FrameDecoder;
import frames.RequestFrame;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();
	private Connection connection = Domino.createConnectionConstant();
	private Coder coder = new Coder();
	private FrameDecoder frameDecoder = new FrameDecoder();
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;
	private byte[] bytesFromStream;
	private FrameStorage frameStorage = new FrameStorage();
	private RequestFrame requestFrame;

	public Controller() {
		connection.receive(this);
	}

	public void createBytesFromStream(int length, byte[] bytes) {
		bytesFromStream = new byte[length];
		bytesFromStream = bytes;
		frameDecoder.receiveBytesFromController(bytesFromStream);
		frameStorage.addReceivedFrame(frameDecoder.getNextModbusFrame());

	}

	public void addAndMakeRequest(int id) {
		requestFrameFactory.loadDefinedInformation();
		requestFrame = requestFrameFactory.createRequestFrame();
		System.out.println("id: " + requestFrame.getTransactionIdentifier());
		frameStorage.addSentFrame(requestFrame);
		coder.codeFrame(requestFrame);
		timer = new Timer();
		timer.schedule(new Task(connection, coder.changeListToArray(), id), 0,
				2000); // wysy³anie
						// co
						// 2
						// sekundy
		tasks.add(timer);

	}
}
