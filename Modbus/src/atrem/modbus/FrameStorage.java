package atrem.modbus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import frames.RequestFrame;
import frames.ResponseFrame;

public class FrameStorage {

	private List<RequestFrame> sentFrames = new ArrayList<RequestFrame>();
	private List<ResponseFrame> receivedFrames = new ArrayList<ResponseFrame>();
	private List<FramePairs> framePairs = new ArrayList<FramePairs>();
	private FramePairs pair = new FramePairs();

	private ExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	public void addSentFrame(RequestFrame modbusFrame) {
		sentFrames.add(modbusFrame);
	}

	public void addReceivedFrame(ResponseFrame frameIncoming) {
		receivedFrames.add(frameIncoming);
	}

	public void makeOrder() {

		while (sentFrames.size() != 0) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					compare();

				}
			});

		}

	}

	public void compare() {
		for (int indexOfSentFrames = 0; indexOfSentFrames < sentFrames.size(); indexOfSentFrames++) {
			for (int indexOFReceivedFrames = 0; indexOFReceivedFrames < receivedFrames
					.size(); indexOFReceivedFrames++) {
				if (sentFrames.get(indexOfSentFrames)
						.getTransactionIdentifier() == receivedFrames.get(
						indexOFReceivedFrames).getTransactionIdentifier()) {
					pair.setRequestFrame(sentFrames.get(indexOfSentFrames));
					pair.setResponseFrame(receivedFrames
							.get(indexOFReceivedFrames));
					framePairs.add(pair);
					sentFrames.remove(indexOfSentFrames);
					receivedFrames.remove(indexOFReceivedFrames);

				}
			}
		}
	}
}
