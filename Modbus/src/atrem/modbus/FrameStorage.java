package atrem.modbus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FrameStorage {

	private List<RequestFrame> sentFrames = new ArrayList<RequestFrame>();
	private List<ResponseFrame> receivedFrames = new ArrayList<ResponseFrame>();

	public void addSentFrame(RequestFrame modbusFrame) {
		sentFrames.add(modbusFrame);
	}

	public void addReceivedFrame(ResponseFrame frameIncoming) {
		receivedFrames.add(frameIncoming);
	}

	public void sortReceivedFrames() {
		Collections.sort(receivedFrames, new Comparator<ResponseFrame>() {
			@Override
			public int compare(ResponseFrame f1, ResponseFrame f2) {
				return f1.getTransactionIdentifier()
						- f2.getTransactionIdentifier();
			}
		});
	}
}
