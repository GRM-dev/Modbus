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
	private Coder coder = new Coder(); // TODO metoda ma tworzyc koder
	private FrameDecoder frameDecoder = new FrameDecoder(); // TODO metoda ma
															// tworzyc dekoder
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;
	private FrameStorage frameStorage = new FrameStorage();

	public Controller() {
		connection.receive(this);
	}

	public void createBytesFromStream(byte[] bytes) { // TODO zmiana
														// nazwy

		frameDecoder.receiveBytesFromController(bytes); // TODO zlikwidowac
														// rozbicie na 2 metody,
														// wywolac raz, inna
														// nazwa
		System.out.println(frameDecoder.getNextModbusFrame());
		// frameStorage.addReceivedFrame(frameDecoder.getNextModbusFrame());

	}

	public void addAndMakeRequest(int id) { // TODO zmiana nazwy, rozbicie na 2
											// metody,
		requestFrameFactory.loadDefinedInformation();
		RequestFrame requestFrame = requestFrameFactory.createRequestFrame();
		System.out.println("id: " + requestFrame.getTransactionIdentifier());
		frameStorage.addSentFrame(requestFrame);
		coder.codeFrame(requestFrame);
		timer = new Timer();
		timer.schedule(new Task(connection, coder.getFrameAsBytes(), id), 0,
				2000); // wysy�anie
						// co
						// 2
						// sekundy
		tasks.add(timer);

	}
}
