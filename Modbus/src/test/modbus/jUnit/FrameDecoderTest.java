package test.modbus.jUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import atrem.modbus.parsers.FrameDecoder;

public class FrameDecoderTest {
	@Test
	public void readNextIntTest() throws IOException {

		InputStream inputStream = mock(InputStream.class);
		when(inputStream.read()).thenReturn(0).thenReturn(2);
		FrameDecoder decoder = new FrameDecoder(inputStream);

		int readInt = decoder.readNextInt();
		System.out.println(readInt);
		assertEquals(readInt, 2);

	}

}
