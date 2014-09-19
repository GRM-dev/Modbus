package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import frames.RequestFrame;

public class CoderTest {
	private Coder koder;

	@Before
	public void createCoder() {

		koder = new Coder();
	}

	@Test
	public void codeToBytesTest() {
		byte exampleByte = 0;
		byte[] bytes = koder.codeToBytes(1, 2);
		assertEquals(bytes.length, 2);
		assertEquals(bytes[1], exampleByte);
		exampleByte = 1;
		assertEquals(bytes[0], exampleByte);

	}

	@Test
	public void codeFrameTest() {
		Random generator = new Random();
		RequestFrame frame = new RequestFrame(258, 25, 26, 258, 258);
		koder.codeFrame(frame);
		byte[] byteArray = koder.getFrameAsBytes();
		byte[] exampleByteArray = createBytesList();
		Assert.assertArrayEquals(exampleByteArray, byteArray);

	}

	private byte[] createBytesList() {
		byte[] bytes = { 1, 2, 0, 0, 0, 6, 25, 26, 1, 2, 1, 2 };
		return bytes;

	}
}
