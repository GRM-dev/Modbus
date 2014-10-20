package atrem.modbus.parsers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import atrem.modbus.frames.ResponseFrame;

public class FrameDecoder {

	private ResponseFrame responseFrame;

	private byte[] byteFromStream;
	private int numberBytes = 0;

	public FrameDecoder() {
		responseFrame = new ResponseFrame();
	}

	private byte readNextByte() {
		byte tmp = byteFromStream[numberBytes];
		numberBytes++;

		return tmp;
	}

	int readNextInt() {
		byte[] array = { 0, 0, readNextByte(), readNextByte() };
		ByteBuffer byteBuffer = ByteBuffer.wrap(array);
		byteBuffer.order(ByteOrder.BIG_ENDIAN);
		return byteBuffer.getInt();
	}

	public ResponseFrame decodeBytes(byte[] byteFromStream) {

		this.byteFromStream = byteFromStream;
		ResponseFrame responseFrame = getNextModbusFrame();
		return responseFrame;

	}

	public ResponseFrame getNextModbusFrame() {
		numberBytes = 0;
		ResponseFrame responseFrame = new ResponseFrame();
		responseFrame.setTransactionIdentifier(readNextInt());
		responseFrame.setProtocolIdentifier(readNextInt());
		responseFrame.setDataLength(readNextInt());
		responseFrame.setUnitIdentifier(readNextByte());
		responseFrame.setFunctionCode(readNextByte());
		byte[] dataBytes = readDataBytes(responseFrame.getDataLength() - 2);
		responseFrame.setDataBytes(dataBytes);
		numberBytes = numberBytes - 2;
		responseFrame.setDataValue(readNextInt());
		return responseFrame;
	}

	byte[] readDataBytes(int length) {
		byte[] array = new byte[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = readNextByte();

		}
		return array;
	}

}
