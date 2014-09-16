package test.modbus.jUnit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import atrem.modbus.FrameDekoder;

public class FramDekoderTest {
	@Test
	public void headerReaderTest() {

		FrameDekoder frameDekoder = new FrameDekoder();
		ArrayList<Byte> content = new ArrayList<Byte>();
		content.add(new Byte("0"));
		content.add(new Byte("0"));
		content.add(new Byte("1"));// te dwie
		content.add(new Byte("0"));// i ta

		frameDekoder.setContent(content);
		frameDekoder.headerReader();
		assertEquals("256", frameDekoder.getModbusFrame().getIdTCP());
	}

}
