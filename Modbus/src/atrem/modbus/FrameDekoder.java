package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class FrameDekoder {

	private ByteBuffer byteBuffer;
	private FrameIncoming frameIncoming;
	private int dataLength;
	private InputStream inputStream;

	public FrameDekoder(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public FrameDekoder() {

	}

	public FrameIncoming downloadFrame() {
		byte[] readByte = readBytes(7);
		analyzeHeader(readByte);

		readByte = readBytes(dataLength + 1);
		analyzeData(readByte);
		return frameIncoming;

	}

	private void analyzeData(byte[] readByte) {
		int function = analyzeByteToInt(readByte, 0, 1);
		byteBuffer = ByteBuffer.wrap(readByte, 1, dataLength);
	}

	private void analyzeHeader(byte[] readByte) {
		int TransactionIdentifier = analyzeByteToInt(readByte, 0, 2);// tcpIp
		int ProtocolIdentifier = analyzeByteToInt(readByte, 2, 2);
		dataLength = analyzeByteToInt(readByte, 4, 2);
		int UnitIdentifier = analyzeByteToInt(readByte, 6, 1);
		createFrame(TransactionIdentifier, ProtocolIdentifier, UnitIdentifier);

	}

	private void createFrame(int transactionIdentifier, int protocolIdentifier,
			int unitIdentifier) {
		frameIncoming = new FrameIncoming(transactionIdentifier,
				protocolIdentifier, unitIdentifier);

	}

	private int analyzeByteToInt(byte[] readByte, int offset, int length) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(readByte, offset, length);
		return byteBuffer.getInt();
	}

	private byte[] readBytes(int number) {
		byte[] array = new byte[number];
		try {
			inputStream.read(array);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
