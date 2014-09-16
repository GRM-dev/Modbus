package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FrameDekoder {
	private FrameData frameData;
	private ByteBuffer byteBuffer;
	private ModbusFrame modbusFrame;

	private InputStream inputStream;
	private byte[] header;

	public FrameDekoder() {
		modbusFrame = new ModbusFrame();

	}

	public void downloadFrame() {
		readBytes(7);

	}

	private void readBytes(int number) {
		byte[] array = new byte[number];
		try {
			inputStream.read(array);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void byteReader() {
		List<Byte> header = content.subList(0, 2);
		byte[] arrayByte = listBytetoArray(header.size());
		ByteBuffer byteBuffer = ByteBuffer.wrap(arrayByte);
		Integer idTcp = new Integer(byteBuffer.getInt());
		System.out.println(idTcp.toString());
		modbusFrame.setIdTCP(idTcp.toString());
	}

	private byte[] listBytetoArray(int size) {
		byte arrayByte[] = new byte[4];
		for (int i = 3; i >= size; i--) {
			arrayByte[i] = content.get(i).byteValue();
		}
		return arrayByte;
	}

	public ArrayList<Byte> getContent() {
		return content;
	}

	public void setContent(ArrayList<Byte> content) {
		this.content = content;
	}

	public FrameData getFrameData() {
		return frameData;
	}

	public void setFrameData(FrameData frameData) {
		this.frameData = frameData;
	}

	public ModbusFrame getModbusFrame() {
		return modbusFrame;
	}

	public void setModbusFrame(ModbusFrame modbusFrame) {
		this.modbusFrame = modbusFrame;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
