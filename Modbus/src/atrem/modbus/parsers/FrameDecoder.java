package atrem.modbus.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import frames.ResponseFrame;

public class FrameDecoder {

	private ResponseFrame frameIncoming;
	private InputStream inputStream;

	public FrameDecoder(InputStream inputStream) {
		frameIncoming = new ResponseFrame();
		this.inputStream = inputStream;
	}

	private byte readNextByte() {
		try {
			return (byte) inputStream.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int readNextInt() {
		byte[] array = { 0, 0, readNextByte(), readNextByte() };
		ByteBuffer byteBuffer = ByteBuffer.wrap(array);
		byteBuffer.order(ByteOrder.BIG_ENDIAN);
		return byteBuffer.getInt();
	}

	public ResponseFrame getNextModbusFrame() {

		frameIncoming.setTransactionIdentifier(readNextInt());
		frameIncoming.setProtocolIdentifier(readNextInt());
		frameIncoming.setDataLength(readNextInt());
		frameIncoming.setUnitIdentifier(readNextByte());
		frameIncoming.setFunctionCode(readNextByte());
		byte[] dataBytes = readDataBytes(frameIncoming.getDataLength() - 2);
		frameIncoming.setDataBytes(dataBytes);
		return frameIncoming;
	}

	byte[] readDataBytes(int length) {
		byte[] array = new byte[length];
		try {
			inputStream.read(array);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}

}
