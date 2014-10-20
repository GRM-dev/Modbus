package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

import atrem.modbus.frames.RequestFrame;

public class Connection implements Runnable {

	private Socket socket;
	private InputStream inStream;
	private ControllerImpl controller;
	private OutputStream outStream;
	private String ipAddress;
	private int port;

	public Connection(String ipAddress, int port, ControllerImpl controller)
			throws IOException {
		this.ipAddress = ipAddress;
		this.port = port;
		this.controller = controller;
		makeConnection();

	}

	public void makeConnection() throws IOException {

		socket = new Socket(ipAddress, port);
		inStream = socket.getInputStream();
		outStream = socket.getOutputStream();

		// new SoundPlayer("connect_sound.mp3").play();
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
		return socket.isConnected();

	}

	public void send(byte[] frame) {

		try {
			outStream.write(frame);
		} catch (SocketException e) {

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

	public void startReceiveFrames() {
		innerStartReceiveFrames();
	}

	Thread innerStartReceiveFrames() {
		Thread thread = new Thread(this, "watek odbierajacy ramki");
		thread.start();
		return thread;

	}

	@Override
	public void run() {

		while (true) {
			byte[] header = new byte[RequestFrame.HEADER_SIZE];
			readBytes(header, RequestFrame.HEADER_SIZE);

			ByteBuffer byteBuffer = ByteBuffer.wrap(header);
			byteBuffer.position(4);
			// int tid = byteBuffer.getShort(); // TODO nie wiem czy to dziala
			// int pid = byteBuffer.getShort();
			int length = byteBuffer.getShort();

			byte[] data = new byte[length];
			readBytes(data, length);
			byte[] buff = new byte[RequestFrame.HEADER_SIZE + length];
			System.arraycopy(header, 0, buff, 0, RequestFrame.HEADER_SIZE);
			System.arraycopy(data, 0, buff, RequestFrame.HEADER_SIZE, length);
			if (socket.isClosed())
				return;
			controller.loadBytes(buff);
		}
	}

	private void readBytes(byte[] targetArray, int count) {
		for (int i = 0; i < count; i++) {
			try {
				targetArray[i] = (byte) inStream.read();
			} catch (SocketException e) {

			} catch (IOException e) { // TODO przechwycenie wyjatku z sensem
				e.printStackTrace();

			}
		}
	}

	public void setController(ControllerImpl controller) {
		this.controller = controller;
	}

	public String getIpAddress() {
		return ipAddress;
	}
}
