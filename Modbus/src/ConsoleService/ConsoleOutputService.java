package ConsoleService;

public class ConsoleOutputService {

	public void askUnitIdentifier() {
		System.out.println("Wprowadz adress Slave'a: ");
	}

	public void askFunctionCode() {
		System.out.println("Wprowadz kod funkcji: ");
	}

	public void askDataContent() {
		System.out.println("Wprowadz dane: ");
	}

	public void askTcpIpAdress() {
		System.out.println("Wprowadz adres IP: ");
	}

	public void askPort() {
		System.out.println("Wprowadz port: ");
	}

	public void showUnitIdentifierError() {
		System.out
				.println("Niepoprawny numer adresu! <0;255>\nWprowadz ponownie:");
	}

	public void showFunctionCodeError() {
		System.out.println("Niepoprawny kod funkcji! \nWprowadz ponownie:");
	}

	public void askStartingAdress() {
		System.out.println("Wprowadz pierwszy rejestr do odczytu: ");
	}

	public void askQuantityOfRegisters() {
		System.out.println("Wprowadz ilosc rejestrow do odczytu: ");
	}

	public void showFormatError() {
		System.out.println("Bledny format danych!");
	}

	public void shoowPortError() {
		System.out
				.println("Niepoprawny numer portu! <0;9999>\nWprowadz ponownie: ");
	}

	public void showConnectionStatus(boolean status) {
		if (status)
			System.out.println("Connected");
		else
			System.out.println("connection ERROR");
	}
}
