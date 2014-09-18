package atrem.modbus;

import java.util.TimerTask;

public class Task extends TimerTask {

	private Connection connection;
	private byte[] bytes;
	private int id;

	public Task(Connection connection, byte[] bytes, int id) {
		this.connection = connection;
		this.bytes = bytes;
		this.id = id;
	}

	@Override
	public void run() {
		connection.send(bytes);
		System.out.println("wyslalem " + id);

	}

}
