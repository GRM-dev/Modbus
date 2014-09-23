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
	private Controller controller;
	private OutputStream outStream;
	private String ipAddress;
	private int port;

	public Connection(String ipAddress, int port, Controller controller) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.controller = controller;
		makeConnection();
	}

	public void makeConnection() {
		try {
			socket = new Socket(ipAddress, port);
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();

		} catch (SocketException e) {
			controller.takeConnectionExepction();
		} catch (IOException e) {
			e.printStackTrace();
			controller.takeConnectionExepction();// TODO pazda do robotyy
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
		} catch (SocketException e) {
			controller.takeConnectionExepction();
		} catch (IOException e) {
			e.printStackTrace();
			controller.takeConnectionExepction();
		}

	}

	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			controller.takeConnectionExepction();
		}
	}

	public void startReceiveFrames(Controller controller) {
		innerStartReceiveFrames(controller);
	}

	Thread innerStartReceiveFrames(Controller controller) {
		this.controller = controller;
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
				System.out.println(targetArray[i]);
			} catch (SocketException e) {
				controller.takeConnectionExepction();
			} catch (IOException e) { // TODO przechwycenie wyjatku z sensem
				e.printStackTrace();
				controller.takeConnectionExepction();
			}
		}
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
