package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class KoderTest {
	@Test
	public void codeToBitTest() {
		byte bytes = 0;
		Koder koder = new Koder();
		koder.codeToBytes(1, 2);
		ArrayList<Byte> bytesList = koder.getBytesList();
		assertEquals(bytesList.size(), 2);
		assertEquals(new Byte(bytesList.get(0)), new Byte(bytes));
		bytes = 1;
		assertEquals(new Byte(bytesList.get(1)), new Byte(bytes));

	}
}
