package atrem.modbus;

import java.util.ArrayList;
import java.util.List;

public class Comparator {

	private List<ModbusFrame> sentFrames = new ArrayList<ModbusFrame>();
	private List<ModbusFrame> receivedFrames = new ArrayList<ModbusFrame>();

	public int getSentFrameIdentifier(int i) {
		return sentFrames.get(i).getIdTCP();
	}

	public int getReceivedFrameIdentifier(int i) {
		return receivedFrames.get(i).getIdTCP();
	}

	public void addSentFrame(ModbusFrame modbusFrame) {
		sentFrames.add(modbusFrame);
	}

	public void addReceivedFrame(ModbusFrame modbusFrame) {
		receivedFrames.add(modbusFrame);
	}
}
