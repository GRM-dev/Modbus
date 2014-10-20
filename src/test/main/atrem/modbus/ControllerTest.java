package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class ControllerTest {
	// public static void main(String[] args) {
	// DominoTest();
	// }

	@Test
	public void DominoTest() throws InterruptedException {
		Connection connection = createConnection();
		ControllerImpl controller = new ControllerImpl();
		connection.setController(controller);

		@SuppressWarnings("unused")
		Domino domino = new Domino(connection, controller);
		connection.innerStartReceiveFrames(controller).join(150);

	}

	public Connection createConnection() {
		byte[] frame = new byte[12];

		InputStream inStream = new InputStream() {
			private int[] frame = { 1, 2, 0, 0, 0, 6, 25, 26, 1, 2, 1, 2, -1 };
			private int index = 0;
			private int count = 0;

			@Override
			public int read() throws IOException {

				if (count < 2) {
					int tmp = frame[index];
					index++;
					count++;
					if (index == 12) {
						index = 0;
					}
					return tmp;
				}
				return count;

			}
		};
		OutputStream outStream = new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				System.out.println("write" + b);

			}
		};

		return new Connection(inStream, outStream);

	}
}
