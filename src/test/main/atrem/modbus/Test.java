package atrem.modbus;

public class Test {

	public static void main(String[] args) {
		ControllerImpl controller = new ControllerImpl();

		controller.startNewRequestTask(69);
		controller.startNewRequestTask(169);

	}

}
