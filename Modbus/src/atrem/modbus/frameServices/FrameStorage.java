package atrem.modbus.frameServices;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import atrem.modbus.Domino;
import atrem.modbus.frames.RequestFrame;
import atrem.modbus.frames.ResponseFrame;

public class FrameStorage {

	private List<RequestFrame> sentFrames;
	private FramesPrinter framesPrinter;
	private List<ResponseFrame> receivedFrames;
	private List<FramePairs> framePairsList;
	private ExecutorService executor;

	public FrameStorage() {
		executor = Executors.newSingleThreadScheduledExecutor();
		sentFrames = new LinkedList<RequestFrame>();
		receivedFrames = new LinkedList<ResponseFrame>();
		framePairsList = new LinkedList<FramePairs>();
		framesPrinter = new FramesPrinter();
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
		executor.execute(new Runnable() { // wywalic executor
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
				framesPrinter.saveNoResponseFrame("" + requestFrame);
				sentFrames.remove(requestFrame);
			}
		}
	}

	private void compareWithResponseFrame(RequestFrame requestFrame) {
		for (ResponseFrame responseFrame : receivedFrames) {
			if (requestFrame.equals(responseFrame)) {// TODO zmiana nazwy
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
		framesPrinter.savePairedFrame("" + framePairs);
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
