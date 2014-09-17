package atrem.modbus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FrameStorage {

	private List<ModbusFrame> sentFrames = new ArrayList<ModbusFrame>();
	private List<FrameIncoming> receivedFrames = new ArrayList<FrameIncoming>();

	public void addSentFrame(ModbusFrame modbusFrame) {
		sentFrames.add(modbusFrame);
	}

	public void addReceivedFrame(FrameIncoming frameIncoming) {
		receivedFrames.add(frameIncoming);
	}

	public void sortReceivedFrames() {
		Collections.sort(receivedFrames, new Comparator<FrameIncoming>() {
			@Override
			public int compare(FrameIncoming f1, FrameIncoming f2) {
				return f1.getTransactionIdentifier()
						- f2.getTransactionIdentifier();
			}
		});
	}
}
