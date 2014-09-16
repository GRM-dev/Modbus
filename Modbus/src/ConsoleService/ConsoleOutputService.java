package ConsoleService;

public class ConsoleOutputService {

	public void askSlaveAdress() {
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

	public void showSlaveAdressError() {
		System.out.println("Niepoprawny format adresu! \nWprowadz ponownie:");
	}

	public void showSlaveFunctionCodeError() {
		System.out
				.println("Niepoprawny format kodu funkcji! \nWprowadz ponownie:");
	}
}
