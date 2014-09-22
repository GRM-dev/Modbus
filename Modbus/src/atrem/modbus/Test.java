package atrem.modbus;

public class Test {

	public static void main(String[] args) {
		Controller controller = new Controller();

		controller.startNewRequestTask(69);
		controller.startNewRequestTask(169);

	}

}
