package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connection implements Runnable {

	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private Socket socket;
	private InputStream inStream;
	private Controller controller;

	private OutputStream outStream;

	public Connection(String ipAddress, int port) {
		try {
			this.socket = new Socket(ipAddress, port);
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getInStream() {
		return inStream;
	}

	public OutputStream getOutStream() {
		return outStream;
	}

	public boolean checkConnection() {
		if (socket.isConnected())
			return true;
		else
			return false;

	}

	public void send(byte[] frame) {
		{
			try {
				outStream.write(frame);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receive(Controller controller) { // o co chodzi z tym
													// final?
		this.controller = controller;
		new Thread(this, "watek odbierajacy ramki od domino").start();

	}

	@Override
	public void run() {
		while (true) {
			final int HEADER_SIZE = 6;
			byte[] header = new byte[HEADER_SIZE];
			for (int i = 0; i < HEADER_SIZE; i++) {
				try {
					header[i] = (byte) inStream.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ByteBuffer byteBuffer = ByteBuffer.wrap(header);
			// int tid = byteBuffer.getShort();
			// int pid = byteBuffer.getShort();
			byteBuffer.position(HEADER_SIZE - 2);
			int length = byteBuffer.getShort();

			byte[] data = new byte[length];
			for (int i = 0; i < length; i++) {
				try {
					header[i] = (byte) inStream.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			byte[] buff = new byte[HEADER_SIZE + length];
			System.arraycopy(header, 0, buff, 0, HEADER_SIZE);
			System.arraycopy(data, 0, buff, HEADER_SIZE - 1, length);
			controller.createBytesFromStream(length + HEADER_SIZE, buff);
		}
	}
}
