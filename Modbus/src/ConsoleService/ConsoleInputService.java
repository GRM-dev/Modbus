package ConsoleService;

import java.util.Scanner;

public class ConsoleInputService {
	private Scanner inputScanner;
	private String inputString;
	private int inputInt;
	private ConsoleOutputService errorOutput;

	public ConsoleInputService() {
		inputScanner = new Scanner(System.in);
		errorOutput = new ConsoleOutputService();
	}

	public int insertSlaveAdress() {

		while (true) {
			if (inputScanner.hasNextInt()) {
				inputInt = inputScanner.nextInt();
				if (inputInt <= 255 && inputInt > 0) {
					return inputInt;
				} else {
					errorOutput.showSlaveAdressError();
				}
			} else {
				errorOutput.showFormatError();
				inputScanner.next();
			}
		}
	}

	public int insertFunctionCode() {

		while (true) {
			if (inputScanner.hasNextInt()) {
				inputInt = inputScanner.nextInt();
				if (correctFunctionCode(inputInt)) {
					return inputInt;
				} else {
					errorOutput.showFunctionCodeError();
				}
			} else {
				errorOutput.showFormatError();
				inputScanner.next();
			}
		}
	}

	public String insertTcpIpAdres() {
		inputString = inputScanner.nextLine();
		inputString.trim();
		return inputString;

	}

	public int insertPort() {
		while (true) {
			if (inputScanner.hasNextInt()) {
				inputInt = inputScanner.nextInt();
				if (inputInt <= 9999 || inputInt > 0) {
					return inputInt;
				} else {
					errorOutput.showSlaveAdressError();
				}
			} else {
				errorOutput.showFormatError();
				inputScanner.next();
			}
		}

	}

	public String insertDataContent(int functionCode) {
		// TODO zastanowic sie na tym PAWEL
		inputString = inputScanner.nextLine();
		inputString.trim();
		return inputString;
	}

	public int insertFirstRegister() {
		return inputScanner.nextInt();
	}

	public int insertNumberOfRegisters() {
		return inputScanner.nextInt();
	}

	private boolean correctFunctionCode(int arg) {
		if (arg == 1 || arg == 2 || arg == 3 || arg == 4 || arg == 5
				|| arg == 6 || arg == 15 || arg == 16)
			return true;
		else
			return false;
	}
}
