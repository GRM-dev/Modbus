package atrem.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
	Socket socket;
	InputStream inStream;
	Scanner in;
	OutputStream outStream;
	PrintWriter out;

	public Controller(String ipAddress, int port) {

		try {
			this.socket = new Socket("ipAddress", port);
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(ArrayList<Byte> array) {

		for (int i = 0; i < array.size(); i++) {
			out.write(array.get(i));
		}
		out.flush();
	}

	public ArrayList<Byte> receive() {

		List<Byte> array = new ArrayList<Byte>();
		try {
			while (inStream.read() != -1) {
				array.add((byte) inStream.read());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (ArrayList<Byte>) array;
	}

	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
