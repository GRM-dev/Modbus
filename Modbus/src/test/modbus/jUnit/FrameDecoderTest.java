package test.modbus.jUnit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import atrem.modbus.FrameDecoder;

public class FrameDecoderTest {
	@Test
	public void readNextIntTest() throws IOException {

		InputStream inputStream = org.mockito.Mockito.mock(InputStream.class);
		FrameDecoder decoder = new FrameDecoder(inputStream);
		// when(inputStream.read()).thenReturn(0).thenReturn(1);

		int readInt = decoder.readNextInt();
		System.out.println(readInt);
		assertEquals(readInt, 1);

	}

}
