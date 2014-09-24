package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import atrem.modbus.frames.ResponseFrame;

public class FrameDecoderTest {
	FrameDecoder decoder;

	@Before
	public void Inizcjalizacja() {
		decoder = new FrameDecoder();
	}

	@Test
	public void readNextIntTest() {
		byte[] bytes = { 0, 1, 1, 2 };
		decoder.decodeBytes(bytes);
		int readInt = decoder.readNextInt();

		assertEquals(1, readInt);
		readInt = decoder.readNextInt();
		assertEquals(258, readInt);
	}

	@Test
	public void getNextModbusFrameTest() {

		byte[] bytes = { 1, 2, 0, 0, 0, 6, 25, 26, 1, 2, 1, 2 };
		decoder.decodeBytes(bytes);
		ResponseFrame responseFrame = decoder.getNextModbusFrame();
		ResponseFrame exampleResponseFrame = createExmapleFrame();
		Assert.assertTrue(exampleResponseFrame.equals(responseFrame));
	}

	private ResponseFrame createExmapleFrame() {
		byte[] bytes = { 1, 2, 1, 2 };
		ResponseFrame eResponseFrame = new ResponseFrame();
		eResponseFrame.setDataBytes(bytes);
		eResponseFrame.setDataLength(6);
		eResponseFrame.setFunctionCode(26);
		eResponseFrame.setTransactionIdentifier(258);
		eResponseFrame.setUnitIdentifier(25);
		return eResponseFrame;
	}

	@AfterClass
	public static void koniec() {
		System.out.println("przeszlo");
	}

}