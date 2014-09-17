package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {

	private Socket socket;
	private InputStream inStream;
	private Scanner in;
	private OutputStream outStream;
	private PrintWriter out;

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
				// TODO Auto-generated catch block
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

}
