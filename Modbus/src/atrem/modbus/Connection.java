package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Connection implements Runnable {

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

	public void receive(Controller controller) {
		this.controller = controller;
		new Thread(this, "watek odbierajacy ramki od domino").start();

	}

	@Override
	public void run() {
		while (true) {
			final int HEADER_SIZE = 6;
			byte[] header = new byte[HEADER_SIZE];
			readBytes(header, HEADER_SIZE);

			ByteBuffer byteBuffer = ByteBuffer.wrap(header);
			int dasd = byteBuffer.getShort();
			int ledsdsd = byteBuffer.getShort();
			int length = byteBuffer.getShort();

			byte[] data = new byte[length];
			readBytes(data, length);

			byte[] buff = new byte[HEADER_SIZE + length];
			System.arraycopy(header, 0, buff, 0, HEADER_SIZE);
			System.arraycopy(data, 0, buff, HEADER_SIZE - 1, length);
			controller.createBytesFromStream(length + HEADER_SIZE, buff);
		}
	}

	private void readBytes(byte[] targetArray, int count) {
		for (int i = 0; i < count; i++) {
			try {
				targetArray[i] = (byte) inStream.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
