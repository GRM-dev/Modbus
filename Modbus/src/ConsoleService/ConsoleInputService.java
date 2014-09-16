package ConsoleService;

import java.util.Scanner;

public class ConsoleInputService {
	private Scanner inputScanner;
	private String input;
	private ConsoleOutputService errorOutput;

	public ConsoleInputService() {
		inputScanner = new Scanner(System.in);
	}

	public String insertSlaveAdress() {
		input = inputScanner.nextLine();
		while (input.length() != 2) {
			errorOutput.showSlaveAdressError();
			input = inputScanner.nextLine();
		}
		return input;
	}

	public String insertFunctionCode() {
		input = inputScanner.nextLine();
		input.trim();
		while (input.length() != 2 && !(containCharacters(input))) {
			errorOutput.showSlaveFunctionCodeError();
			input = inputScanner.nextLine();
			input.trim();
		}
		return input;
	}

	public String insertTcpIpAdres() {
		input = inputScanner.nextLine();
		input.trim();
		return input;

	}

	public int insertPort() {
		return inputScanner.nextInt();

	}

	public byte[] insertDataContent() {
		input = inputScanner.nextLine();
		input.trim();
		return input.getBytes();
	}

	private boolean containCharacters(String s) {
		if (s.charAt(0) < 48 || s.charAt(1) > 57)
			return true;
		else
			return false;
	}
}
