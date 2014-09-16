package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
<<<<<<< master

	private Socket socket;
	private InputStream inStream;
	private Scanner in;
	private OutputStream outStream;
	private PrintWriter out;
=======
	Socket socket;
	InputStream inStream;
	Scanner in;
	OutputStream outStream;
	PrintWriter out;
>>>>>>> 870816f main rozbudowany

	public Connection(String ipAddress, int port) {

		try {
			this.socket = new Socket("ipAddress", port);
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public InputStream getInStream() {
		return inStream;
	}

<<<<<<< master
	public OutputStream getOutStream() {
		return outStream;
	}

	public boolean checkConnection() {
		if (socket.isConnected())
			return true;
		else
			return false;
=======
	public ArrayList<Byte> receive() {

		List<Byte> array = new ArrayList<Byte>();
		try {
			while (inStream.read() != -1) {
				array.add((byte) inStream.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (ArrayList<Byte>) array;
>>>>>>> 870816f main rozbudowany
	}

	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
