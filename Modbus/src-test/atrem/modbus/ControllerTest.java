package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.BeforeClass;

public class ControllerTest {

	@BeforeClass
	public void createConnection() {
		byte[] frame = new byte[12];

		InputStream inStream = new InputStream() {
			private int[] frame = { 1, 2, 0, 0, 0, 6, 25, 26, 1, 2, 1, 2, -1 };
			private int index = 0;

			@Override
			public int read() throws IOException {
				int tmp = frame[index];
				index++;
				try {
					wait(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (index == 13) {
					index = 0;
				}
				return tmp;
			}
		};
		OutputStream outStream = new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				System.out.println("write" + b);

			}
		};

		Connection connection = new Connection(inStream, outStream);

	}
}
