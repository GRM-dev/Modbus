package atrem.modbus;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;

import frames.RequestFrame;

public class ControllerTest {

	@Test
	public void addAndMakeRequest() {
		Controller controller = new Controller();
		controller.setConnection(createConnection());
		controller.startNewRequestTask(69);
		RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
		requestFrameFactory.loadDefinedInformation();
		RequestFrame sampleRequestFrame = requestFrameFactory
				.createRequestFrame();
		FrameStorage frameStorage = controller.getFrameStorage();
		List<RequestFrame> sentFrames = frameStorage.getSentFrames();
		// RequestFrame requestFrame = sentFrames.get(0);

	}

	private Connection createConnection() {
		byte[] frame = new byte[12];
		InputStream inStream = mock(InputStream.class);
		OutputStream outStream = mock(OutputStream.class);
		try {
			when(inStream.read()).thenReturn(0, 48, 0, 0, 0, 6, 25, 26, 1, 2,
					1, 2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			doNothing().when(outStream).write(frame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Connection connection = new Connection(inStream, outStream);
		return connection;
	}
}
