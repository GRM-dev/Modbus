package ConsoleService;

import java.util.Scanner;

public class ConsoleInputService {
	private Scanner inputScanner;
	private String input;
	private ConsoleOutputService errorOutput;

	public ConsoleInputService() {
		inputScanner = new Scanner(System.in);
		errorOutput = new ConsoleOutputService();
	}

	public String insertSlaveAdress() {
		input = inputScanner.nextLine();
		while (Integer.parseInt(input) > 255) {
			errorOutput.showSlaveAdressError();
			input = inputScanner.nextLine();
		}
		return input;
	}

	public String insertFunctionCode() {
		input = inputScanner.nextLine();
		// input.trim();
		while (input.length() != 2) {// && !(containCharacters(input))) {
			errorOutput.showSlaveFunctionCodeError();
			input = inputScanner.nextLine();
			// input.trim();
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

	public String insertDataContent() {
		input = inputScanner.nextLine();
		input.trim();
		return input;
	}

	public int insertFirstRegister() {
		return inputScanner.nextInt();
	}

	public int insertNumberOfRegisters() {
		return inputScanner.nextInt();
	}

	private boolean containCharacters(String s) {
		if (s.charAt(0) < 48 || s.charAt(1) > 57)
			return true;
		else
			return false;
	}
}
