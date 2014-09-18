package atrem.modbus.parsers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class FrameDecoderTest {
	@Test
	public void readNextIntTest() throws IOException {

		InputStream inputStream = mock(InputStream.class);
		when(inputStream.read()).thenReturn(0).thenReturn(2);

		byte[] bytes = new byte[4];
		when(bytes[1]).thenReturn((byte) 2);

		FrameDecoder decoder = new FrameDecoder();
		byte[] bytes = new byte[4];

		int readInt = decoder.readNextInt();
		System.out.println(readInt);
		assertEquals(readInt, 2);
	}

}
