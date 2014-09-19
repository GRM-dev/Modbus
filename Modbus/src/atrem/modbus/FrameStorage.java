package atrem.modbus;

import java.util.ArrayList;
import java.util.Date;
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
	private boolean isWorking = false;
	private ExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	public void addSentFrame(RequestFrame modbusFrame) {
		sentFrames.add(modbusFrame);
	}

	public void addReceivedFrame(ResponseFrame frameIncoming) {
		receivedFrames.add(frameIncoming);
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public void makePairsOfFrames() {

		executor.execute(new Runnable() {

			@Override
			public void run() {
				isWorking = true;
				while (sentFrames.size() != 0) {
					compare();
				}
				isWorking = false;
			}

		});

		// isWorking = false;
	}

	public boolean hasNoResponse(int index) {
		Date sendDate = sentFrames.get(index).getSendDate();
		long sendTimeSeconds = sendDate.getTime();
		Date currentDate = new Date();
		long currentTimeSeconds = currentDate.getTime();

		if (currentTimeSeconds - sendTimeSeconds > 5)
			return true;
		else
			return false;

	}

	public void compare() {
		for (int indexOfSentFrames = 0; indexOfSentFrames < sentFrames.size(); indexOfSentFrames++) {
			for (int indexOFReceivedFrames = 0; indexOFReceivedFrames < receivedFrames
					.size(); indexOFReceivedFrames++) {
				SysOutPairFrame(indexOfSentFrames, indexOFReceivedFrames);
				if (isTheSameFrame(indexOfSentFrames, indexOFReceivedFrames)) {
					pairFrame(indexOfSentFrames, indexOFReceivedFrames);
					removePairedFrame(indexOfSentFrames, indexOFReceivedFrames);
				}
			}
		}
	}

	private void SysOutPairFrame(int indexOfSentFrames,
			int indexOFReceivedFrames) {
		System.out.println(sentFrames.get(indexOfSentFrames)
				.getTransactionIdentifier()
				+ "   "
				+ receivedFrames.get(indexOFReceivedFrames)
						.getTransactionIdentifier());
	}

	private boolean isTheSameFrame(int indexOfSentFrames,
			int indexOFReceivedFrames) {
		return sentFrames.get(indexOfSentFrames).getTransactionIdentifier() == receivedFrames
				.get(indexOFReceivedFrames).getTransactionIdentifier();
	}

	private void pairFrame(int indexOfSentFrames, int indexOFReceivedFrames) {
		pair.setRequestFrame(sentFrames.get(indexOfSentFrames));
		pair.setResponseFrame(receivedFrames.get(indexOFReceivedFrames));
		framePairs.add(pair);
		System.out.println("compare test");
		Domino.showRequestAndResponse(pair);

	}

	private void removePairedFrame(int indexOfSentFrames,
			int indexOFReceivedFrames) {
		sentFrames.remove(indexOfSentFrames);
		receivedFrames.remove(indexOFReceivedFrames);
	}

	List<RequestFrame> getSentFrames() {
		return sentFrames;
	}
}
