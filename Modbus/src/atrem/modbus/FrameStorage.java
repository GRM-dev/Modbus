package atrem.modbus;

import java.util.Date;
import java.util.LinkedList;
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
	private List<FramePairs> framePairsList;
	private ExecutorService executor;

	private static final String NO_RESPONSE_FRAMES_FILE = "NoResponseFrames ";
	private static final String PAIRED_FRAMES_FILE = "PairedFrames ";

	public FrameStorage() {
		executor = Executors.newSingleThreadScheduledExecutor();
		sentFrames = new LinkedList<RequestFrame>();
		receivedFrames = new LinkedList<ResponseFrame>();
		framePairsList = new LinkedList<FramePairs>();
	}

	public FrameStorage(List<RequestFrame> sentFrames) {
		this.sentFrames = sentFrames;
	}

	public void addSentFrame(RequestFrame requestFrame) {
		sentFrames.add(requestFrame);
	}

	public void addReceivedFrame(ResponseFrame frameIncoming) {
		receivedFrames.add(frameIncoming);
	}

	public void makePairsOfFrames() {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				compare();
			}
		});
	}

	void compare() {
		for (RequestFrame requestFrame : sentFrames) {
			compareWithResponseFrame(requestFrame);
			if (hasNoResponse(requestFrame)) {
				noResponseFramesPrinter.writeToLog("" + requestFrame);
				sentFrames.remove(requestFrame);
			}
		}
	}

	private void compareWithResponseFrame(RequestFrame requestFrame) {
		for (ResponseFrame responseFrame : receivedFrames) {
			if (requestFrame.equals(responseFrame)) {
				pairFrame(requestFrame, responseFrame);
				// TODO wyswietlanie testowe parowanych ramek
				SysOutPairFrame(requestFrame, responseFrame);

				removePairedFrame(requestFrame, responseFrame);
				continue;
			}
		}
	}

	private void SysOutPairFrame(RequestFrame requestFrame,
			ResponseFrame responseFrame) {
		System.out.println(requestFrame.getTransactionIdentifier());
		System.out.println(responseFrame.getTransactionIdentifier());
	}

	private void pairFrame(RequestFrame requestFrame,
			ResponseFrame responseFrame) {
		FramePairs framePairs = new FramePairs(requestFrame, responseFrame);
		framePairsList.add(framePairs);
		pairedFramesPrinter.writeToLog("" + framePairs);
		Domino.showRequestAndResponse(framePairs);

	}

	private void removePairedFrame(RequestFrame requestFrame,
			ResponseFrame responseFrame) {
		sentFrames.remove(requestFrame);
		receivedFrames.remove(responseFrame);
	}

	boolean hasNoResponse(RequestFrame sentFrame) {
		Date sendDate = sentFrame.getSendDate();
		long sendTimeSeconds = sendDate.getTime() / 1000;

		long currentTimeSeconds = System.currentTimeMillis() / 1000;

		if (currentTimeSeconds - sendTimeSeconds > 5)
			return true;
		else
			return false;
	}

}
