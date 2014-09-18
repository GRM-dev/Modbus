package atrem.modbus;

import atrem.modbus.parsers.Coder;
import atrem.modbus.parsers.FrameDecoder;
import consoleService.ConsoleInputService;
import consoleService.ConsoleOutputService;
import frames.RequestFrame;
import frames.ResponseFrame;

public class Domino {
	private static Connection connection;
	static private String ip;
	static private int port;
	static private ConsoleInputService consoleInput;
	static private ConsoleOutputService consoleOutput;

	public static void main(String[] args) {
		FrameStorage frameStorage = new FrameStorage();
		connection = createConnectionConstant();
		RequestFrameFactory requestFrameFactory = new RequestFrameFactory();
		requestFrameFactory.loadDefinedInformation();
		Coder koder = new Coder();
		FrameDecoder decoder = new FrameDecoder(connection.getInStream());
		while (true) {
			RequestFrame frame = requestFrameFactory.createRequestFrame();

			koder.codeFrame(frame);
			frameStorage.addSentFrame(frame);
			connection.send(koder.changeListToArray());

			ResponseFrame frameIncoming;

			frameIncoming = decoder.getNextModbusFrame();
			System.out.println(frameIncoming);
			frameStorage.addReceivedFrame(frameIncoming);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static Connection createConnection() {
		consoleOutput.askTcpIpAdress();
		ip = consoleInput.insertTcpIpAdres();

		consoleOutput.askPort();
		port = consoleInput.insertPort();

		return new Connection(ip, port);// lll
	}

	public static Connection createConnectionConstant() {

		ip = "10.7.7.121";

		port = 502;

		return new Connection(ip, port);// lll
	}

}
