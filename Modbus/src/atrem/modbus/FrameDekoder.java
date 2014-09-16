package atrem.modbus;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FrameDekoder {
	private FrameData frameData;
	private ArrayList<Byte> content;
	private ByteBuffer byteBuffer;
	private ModbusFrame modbusFrame;

	public FrameData getFrameData() {
		return frameData;
	}

	public void setFrameData(FrameData frameData) {
		this.frameData = frameData;
	}

	public void headerReader() {
		List<Byte> header = content.subList(0, 2);
		byte[] arrayByte = listBytetoArray();
		byteBuffer = ByteBuffer.wrap(arrayByte);
		int idTcp = byteBuffer.getInt();
		modbusFrame.setIdTCP(idTcp);

	}

	private byte[] listBytetoArray() {
		byte arrayByte[] = new byte[2];
		for (int i = 0; i < content.size(); i++) {
			arrayByte[i] = content.get(i).byteValue();
		}
		return arrayByte;
	}
}
