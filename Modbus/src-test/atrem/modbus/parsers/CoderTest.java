package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import frames.RequestFrame;

public class CoderTest {
	@Test
	public void codeToBytesTest() {
		byte exampleByte = 0;
		Coder koder = new Coder();
		byte[] bytes = koder.codeToBytes(1, 2);
		assertEquals(bytes.length, 2);
		assertEquals(bytes[1], exampleByte);
		exampleByte = 1;
		assertEquals(bytes[0], exampleByte);

	}

	@Test
	public void codeFrameTest() {
		Random generator = new Random();

		RequestFrame frame = new RequestFrame(258, 258, 258, 258, 258);
		Coder koder = new Coder();
		koder.codeFrame(frame);
		ArrayList<Byte> bytesList = koder.getBytesList();

		assertEquals(expected, actual)
		}

	}

}
