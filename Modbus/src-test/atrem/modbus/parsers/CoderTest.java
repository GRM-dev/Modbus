package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
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

		RequestFrame frame = new RequestFrame(258, 25, 26, 258, 258);
		Coder koder = new Coder();
		koder.codeFrame(frame);
		ArrayList<Byte> bytesList = koder.getBytesList();

		check258(bytesList.subList(0, 2));
		checkBytes(bytesList, 2, 0);
		checkBytes(bytesList, 3, 0);
		checkBytes(bytesList, 4, 0);
		checkBytes(bytesList, 5, 6);
		checkBytes(bytesList, 6, 25);
		checkBytes(bytesList, 7, 26);
		check258(bytesList.subList(8, 10));
		check258(bytesList.subList(10, 12));
	}

	private void checkBytes(ArrayList<Byte> bytesList, int cell, int value) {
		assertEquals(new Byte((byte) value), bytesList.get(cell));
	}

	private void check258(List<Byte> list) {
		assertEquals(new Byte((byte) 1), list.get(0));
		assertEquals(new Byte((byte) 2), list.get(1));
	}

}
