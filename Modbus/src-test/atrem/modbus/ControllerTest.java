package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class ControllerTest {

	@Test
	public void addAndMakeRequest() {
		Controller controller = new Controller();
		Connection connection = createConnection();
		controller.setConnection(connection);

		connection.startReceiveFrames(controller);
		controller.addAndMakeRequest();

		// RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
		// requestFrameFactory.loadDefinedInformation();
		// RequestFrame sampleRequestFrame = requestFrameFactory
		// .createRequestFrame();
		// FrameStorage frameStorage = controller.getFrameStorage();
		// List<RequestFrame> sentFrames = frameStorage.getSentFrames();
		// RequestFrame requestFrame = sentFrames.get(0);

	}

	private Connection createConnection() {
		byte[] frame = new byte[12];
		// InputStream inStream = mock(InputStream.class);
		// // OutputStream outStream = mock(OutputStream.class);
		// try {
		// when(inStream.read()).thenReturn(0, 48, 0, 0, 0, 6, 25, 26, 1, 2,
		// 1, 2);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		InputStream inStream = new InputStream() {

			@Override
			public int read() throws IOException {

				return 0;
			}
		};
		OutputStream outStream = new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				System.out.println("tu" + b);

			}
		};

		Connection connection = new Connection(inStream, outStream);
		return connection;
	}
}
