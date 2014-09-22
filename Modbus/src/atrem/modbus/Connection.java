package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

import frames.RequestFrame;

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
			e.printStackTrace(); // TODO pazda do roboty
		}

	}

	Connection(InputStream inStream, OutputStream outStream) {
		this.inStream = inStream;
		this.outStream = outStream;

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

		try {
			outStream.write(frame);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startReceiveFrames(Controller controller) {
		this.controller = controller;
		new Thread(this, "watek odbierajacy ramki").start();

	}

	@Override
	public void run() {

		while (true) {
			System.out.println("w runie");
			byte[] header = new byte[RequestFrame.HEADER_SIZE];
			readBytes(header, RequestFrame.HEADER_SIZE);

			ByteBuffer byteBuffer = ByteBuffer.wrap(header);
			int tid = byteBuffer.getShort(); // TODO byteBuffer.position(4)
			int pid = byteBuffer.getShort();
			int length = byteBuffer.getShort();

			byte[] data = new byte[length];
			readBytes(data, length);

			byte[] buff = new byte[RequestFrame.HEADER_SIZE + length];
			System.arraycopy(header, 0, buff, 0, RequestFrame.HEADER_SIZE);
			System.arraycopy(data, 0, buff, RequestFrame.HEADER_SIZE, length);

			controller.loadBytesToDecoder(buff);
		}
	}

	private void readBytes(byte[] targetArray, int count) {
		for (int i = 0; i < count; i++) {
			try {
				targetArray[i] = (byte) inStream.read();
			} catch (SocketException e) {
				System.out.println("test socketexception");
			} catch (IOException e) { // TODO przechwycenie wyjatku z sensem
				e.printStackTrace();
			}
		}
	}
}
