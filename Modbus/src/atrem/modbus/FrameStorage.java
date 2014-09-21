package atrem.modbus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import frames.RequestFrame;
import frames.ResponseFrame;

public class FrameStorage {

	private List<RequestFrame> sentFrames;
	private FramesPrinter noResponseFramesPrinter = new FramesPrinter(
			NO_RESPONSE_FRAMES_FILE);
	private FramesPrinter pairedFramesPrinter = new FramesPrinter(
			PAIRED_FRAMES_FILE);
	private List<ResponseFrame> receivedFrames;
	private List<FramePairs> framePairs;
	private FramePairs pair = new FramePairs();
	private boolean isWorking = false;
	private ExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	private static final String NO_RESPONSE_FRAMES_FILE = "NoResponseFrames ";
	private static final String PAIRED_FRAMES_FILE = "PairedFrames ";

	public FrameStorage() {
		sentFrames = new ArrayList<RequestFrame>();
		receivedFrames = new ArrayList<ResponseFrame>();
		framePairs = new ArrayList<FramePairs>();

	}

	public FrameStorage(List<RequestFrame> sentFrames) {
		this.sentFrames = sentFrames;
	}

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
				compare();
			}

		});

	}

	boolean hasNoResponse(int index) {
		if (sentFrames.size() >= 0 && index < sentFrames.size()) {
			Date sendDate = sentFrames.get(index).getSendDate();
			long sendTimeSeconds = sendDate.getTime() / 1000;

			long currentTimeSeconds = System.currentTimeMillis() / 1000;

			if (currentTimeSeconds - sendTimeSeconds > 5)
				return true;
			else
				return false;
		}
		return false;

	}

	public void compare() {
		for (int indexOfSentFrames = 0; indexOfSentFrames < sentFrames.size(); indexOfSentFrames++) {
			for (int indexOFReceivedFrames = 0; indexOFReceivedFrames < receivedFrames
					.size(); indexOFReceivedFrames++) {
				SysOutPairFrame(indexOfSentFrames, indexOFReceivedFrames);
				if (isPairFrame(indexOfSentFrames, indexOFReceivedFrames)) {

					pairFrame(indexOfSentFrames, indexOFReceivedFrames);
					removePairedFrame(indexOfSentFrames, indexOFReceivedFrames);

					continue;
				}
			}
			if (hasNoResponse(indexOfSentFrames)) {
				noResponseFramesPrinter.writeToLog(""
						+ sentFrames.get(indexOfSentFrames));
				sentFrames.remove(indexOfSentFrames);

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

	private boolean isPairFrame(int indexOfSentFrames, int indexOFReceivedFrames) {
		RequestFrame requestFrame = sentFrames.get(indexOfSentFrames);
		ResponseFrame responseFrame = receivedFrames.get(indexOFReceivedFrames);
		return requestFrame.equals(responseFrame);
	}

	private void pairFrame(int indexOfSentFrames, int indexOFReceivedFrames) {
		pair.setRequestFrame(sentFrames.get(indexOfSentFrames));
		pair.setResponseFrame(receivedFrames.get(indexOFReceivedFrames));
		framePairs.add(pair);
		Domino.showRequestAndResponse(pair);
		pairedFramesPrinter.writeToLog("" + pair);

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
