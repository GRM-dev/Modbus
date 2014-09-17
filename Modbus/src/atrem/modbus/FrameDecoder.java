package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FrameDecoder {
	private ByteBuffer byteBuffer;
	private FrameIncoming modbusFrame;
	private InputStream inputStream;

	public FrameDecoder(InputStream inputStream) {
		modbusFrame = new FrameIncoming();
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

	private int readNextInt() {
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
		readDataBytes(modbusFrame.getDataLength());
		return modbusFrame;
	}

	private void readTransactionIdentifier() {
		modbusFrame.setTransactionIdentifier(readNextInt());
	}

	private void readProtocolIdentifier() {
		modbusFrame.setProtocolIdentifier(readNextInt());
	}

	private void readLengthField() {
		modbusFrame.setDataLength(readNextInt());
	}

	private void readUnitIdentifier() {
		modbusFrame.setUnitIdentifier(readNextByte());
	}

	private void readFunctionCode() {
		modbusFrame.setFunctionCode(readNextByte());

	}

	private void readDataBytes(int length) {
		byte[] array = new byte[length];
		try {
			inputStream.read(array);
		} catch (IOException e) {
			e.printStackTrace();
		}
		modbusFrame.setDataBytes(array);
	}

}
