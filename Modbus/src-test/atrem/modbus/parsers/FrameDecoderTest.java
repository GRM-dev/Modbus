package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import frames.ResponseFrame;

public class FrameDecoderTest {
	@Test
	public void readNextIntTest() {
		byte[] bytes = { 0, 1, 1, 2 };
		FrameDecoder decoder = new FrameDecoder();
		decoder.receiveBytesFromController(bytes);
		int readInt = decoder.readNextInt();

		assertEquals(1, readInt);
		readInt = decoder.readNextInt();
		assertEquals(258, readInt);
	}

	@Test
	public void getNextModbusFrameTest() {

		byte[] bytes = { 1, 2, 0, 0, 0, 6, 25, 26, 1, 2, 1, 2 };
		FrameDecoder decoder = new FrameDecoder();
		decoder.receiveBytesFromController(bytes);
		ResponseFrame responseFrame = decoder.getNextModbusFrame();
		ResponseFrame exampleResponseFrame = createExmapleFrame();
		assertEqualsFrame(responseFrame, exampleResponseFrame);
		exampleResponseFrame = createExmapleFrame1();
		assertEqualsFrame(exampleResponseFrame, responseFrame);
	}

	private void assertEqualsFrame(ResponseFrame responseFrame,
			ResponseFrame exampleResponseFrame) {
		Assert.assertArrayEquals(exampleResponseFrame.getDataBytes(),
				responseFrame.getDataBytes());
		assertEquals(exampleResponseFrame.getDataLength(),
				responseFrame.getDataLength());
		assertEquals(exampleResponseFrame.getFunctionCode(),
				responseFrame.getFunctionCode());
		assertEquals(exampleResponseFrame.getProtocolIdentifier(),
				responseFrame.getProtocolIdentifier());
		assertEquals(exampleResponseFrame.getTransactionIdentifier(),
				responseFrame.getTransactionIdentifier());
		assertEquals(exampleResponseFrame.getUnitIdentifier(),
				responseFrame.getUnitIdentifier());
	}

	private ResponseFrame createExmapleFrame() {
		byte[] bytes = { 1, 2, 1, 2 };
		ResponseFrame eResponseFrame = mock(ResponseFrame.class);
		when(eResponseFrame.getDataBytes()).thenReturn(bytes);
		when(eResponseFrame.getDataLength()).thenReturn(6);
		when(eResponseFrame.getFunctionCode()).thenReturn(26);
		when(eResponseFrame.getTransactionIdentifier()).thenReturn(258);
		when(eResponseFrame.getUnitIdentifier()).thenReturn(25);
		return eResponseFrame;

	}

	private ResponseFrame createExmapleFrame1() {
		byte[] bytes = { 1, 2, 1, 2 };
		ResponseFrame eResponseFrame = new ResponseFrame();
		eResponseFrame.setDataBytes(bytes);
		eResponseFrame.setDataLength(6);
		eResponseFrame.setFunctionCode(26);
		eResponseFrame.setTransactionIdentifier(258);
		eResponseFrame.setUnitIdentifier(25);
		return eResponseFrame;
	}

}