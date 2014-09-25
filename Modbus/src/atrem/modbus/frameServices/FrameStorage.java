package atrem.modbus.frameServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import atrem.modbus.Domino;
import atrem.modbus.PairedFrameListener;
import atrem.modbus.frames.RequestFrame;
import atrem.modbus.frames.ResponseFrame;

public class FrameStorage {

	private List<RequestFrame> sentFrames;
	private FramesPrinter framesPrinter;
	private List<ResponseFrame> receivedFrames;
	private List<FramePairs> framePairsList;
	private ExecutorService executor;
	private ResponseFrame lastResponseFrame;
	private List<PairedFrameListener> pairedFrameListenerList;

	public FrameStorage() {
		executor = Executors.newSingleThreadScheduledExecutor();
		sentFrames = new LinkedList<RequestFrame>();
		receivedFrames = new LinkedList<ResponseFrame>();
		framePairsList = new LinkedList<FramePairs>();
		framesPrinter = new FramesPrinter();
		pairedFrameListenerList = new ArrayList<PairedFrameListener>();
	}

	public void addPairedFrameListener(PairedFrameListener pairedFrameListener) {
		pairedFrameListenerList.add(pairedFrameListener);
	}

	private void onPairedFrame(FramePairs framePairs) {
		for (PairedFrameListener pairedFrameListener : pairedFrameListenerList) {
			pairedFrameListener.onPairFrame(framePairs);
		}

	}

	public void addSentFrame(final RequestFrame requestFrame) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				sentFrames.add(requestFrame);

			}
		});

	}

	public void addReceivedFrame(final ResponseFrame frameIncoming) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				receivedFrames.add(frameIncoming);

			}
		});

	}

	public void makePairsOfFrames() {
		executor.execute(new Runnable() { // wywalic executor
			@Override
			public void run() {
				compare();
			}
		});
	}

	synchronized void compare() {
		for (RequestFrame sentFramesTmp : sentFrames) {
			compareWithResponseFrame(sentFramesTmp);
			if (hasNoResponse(sentFramesTmp)) {
				framesPrinter.saveNoResponseFrame("" + sentFramesTmp);
				sentFrames.remove(sentFramesTmp);
			}
		}
	}

	private void compareWithResponseFrame(RequestFrame requestFrame) {
		for (ResponseFrame receivedFramesTmp : receivedFrames) {
			if (requestFrame.match(receivedFramesTmp)) {
				addToListPairedFrame(requestFrame, receivedFramesTmp);
				// TODO wyswietlanie testowe parowanych ramek
				SysOutPairFrame(requestFrame, receivedFramesTmp);

				removePairedFrame(requestFrame, receivedFramesTmp);
				continue;
			}
		}
	}

	private void SysOutPairFrame(RequestFrame requestFrame,
			ResponseFrame responseFrame) {
		System.out.println(requestFrame.getTransactionIdentifier());
		System.out.println(responseFrame.getTransactionIdentifier());
	}

	private void addToListPairedFrame(RequestFrame requestFrame,
			ResponseFrame responseFrame) {
		FramePairs framePairs = new FramePairs(requestFrame, responseFrame);
		onPairedFrame(framePairs);
		framePairsList.add(framePairs);
		lastResponseFrame = responseFrame;
		lastResponseFrame.setRegistryValue(requestFrame.getStartingAdress());
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

	public ResponseFrame getLastResponseFrame() {
		return lastResponseFrame;
	}
}
