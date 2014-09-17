package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FrameDecoder {
	private ByteBuffer byteBuffer;
	private FrameIncoming frameIncoming;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public FrameDecoder() {
		frameIncoming = new FrameIncoming();
	}

	public FrameDecoder(InputStream inputStream) {
		this();
		this.inputStream = inputStream;
	}

	public byte readNextByte() {
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

	public FrameIncoming getNextModbusFrame() {
		readTransactionIdentifier();
		readProtocolIdentifier();
		readLengthField();
		readUnitIdentifier();
		readFunctionCode();
		readDataBytes(frameIncoming.getDataLength());
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
