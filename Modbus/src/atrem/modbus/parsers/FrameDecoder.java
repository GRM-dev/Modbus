package atrem.modbus.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import frames.ResponseFrame;

public class FrameDecoder {
	private ByteBuffer byteBuffer;
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

	public int readNextInt() {
		byte[] array = { 0, 0, readNextByte(), readNextByte() };
		ByteBuffer bb = ByteBuffer.wrap(array);
		bb.order(ByteOrder.BIG_ENDIAN);
		return bb.getInt();
	}

	public ResponseFrame getNextModbusFrame() {
		readTransactionIdentifier();
		readProtocolIdentifier();
		readLengthField();
		readUnitIdentifier();
		readFunctionCode();
		readDataBytes(frameIncoming.getDataLength() - 2);
		return frameIncoming;
	}

	private void readTransactionIdentifier() {
		frameIncoming.setTransactionIdentifier(readNextInt());
	}

	private void readProtocolIdentifier() {
		frameIncoming.setProtocolIdentifier(readNextInt());
	}

	private void readLengthField() {
		frameIncoming.setDataLength(readNextInt());
	}

	private void readUnitIdentifier() {
		frameIncoming.setUnitIdentifier(readNextByte());
	}

	private void readFunctionCode() {
		frameIncoming.setFunctionCode(readNextByte());

	}

	private void readDataBytes(int length) {
		byte[] array = new byte[length];
		try {
			inputStream.read(array);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frameIncoming.setDataBytes(array);
	}

}