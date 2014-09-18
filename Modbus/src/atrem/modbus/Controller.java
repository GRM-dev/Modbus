package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import atrem.modbus.parsers.Coder;
import atrem.modbus.parsers.FrameDecoder;

public class Controller {

	private List<Timer> tasks = new ArrayList<Timer>();
	private Connection connection = Domino.createConnectionConstant();
	private Coder coder = new Coder();
	private FrameDecoder frameDecoder = new FrameDecoder(
			connection.getInStream());
	private RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
	private Timer timer;

	public void addAndMakeRequest(int id) {
		requestFrameFactory.loadDefinedInformation();
		coder.codeFrame(requestFrameFactory.createRequestFrame());
		timer = new Timer();
		timer.schedule(new Task(connection, coder.changeListToArray(), id), 0,
				2000); // wysy³anie
						// co
						// 2
						// sekundy
		tasks.add(timer);

	}
}
