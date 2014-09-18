package atrem.modbus;

public class Test {

	public static void main(String[] args) {
		Controller controller = new Controller();

		controller.addAndMakeRequest(69);
		controller.addAndMakeRequest(169);

	}

}
