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
		inputInt = inputScanner.nextInt();
		while (inputInt > 255 || inputInt < 0) {
			errorOutput.showSlaveAdressError();
			inputInt = inputScanner.nextInt();
			// TODO zabezpieczyc przed literalami PAWEL
		}
		return inputInt;
	}

	public int insertFunctionCode() {
		inputInt = inputScanner.nextInt();
		// TODO zabezpieczyc przed nieznanym kodem funkcji PAWEL
		return inputInt;
	}

	public String insertTcpIpAdres() {
		inputString = inputScanner.nextLine();
		inputString.trim();
		return inputString;

	}

	public int insertPort() {
		return inputScanner.nextInt();
		// TODO zabezpieczyc numer portu PAWEL

	}

	public String insertDataContent() {
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

	private boolean containCharacters(String s) {
		if (s.charAt(0) < 48 || s.charAt(1) > 57)
			return true;
		else
			return false;
	}
}
