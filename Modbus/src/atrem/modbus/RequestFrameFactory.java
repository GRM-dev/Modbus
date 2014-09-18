package atrem.modbus;

import consoleService.ConsoleInputService;
import consoleService.ConsoleOutputService;
import frames.RequestFrame;

public class RequestFrameFactory {
	private int transactionIdentifier;
	private int unitIdentifier;
	private int startingAdress;
	private int quantityOfRegisters;
	private int functionCode;
	private ConsoleOutputService consoleOutput;
	private ConsoleInputService consoleInput;

	private final int FUNCTION_CODE = 3, UNIT_IDENTIFER = 5,
			STARTING_ADRESS = 3027, QUANTITY_OF_REGISTERS = 2;

	public RequestFrameFactory() {
		consoleInput = new ConsoleInputService();
		consoleOutput = new ConsoleOutputService();
	}

	public void loadInformationFromConsole() {

		consoleOutput.askUnitIdentifier();
		setUnitIdentifier((consoleInput.insertUnitIdentifier()));
		consoleOutput.askFunctionCode();
		setFunctionCode(consoleInput.insertFunctionCode());
		consoleOutput.askStartingAdress();
		setStartingAdress(consoleInput.insertFirstRegister());
		consoleOutput.askQuantityOfRegisters();
		setQuantityOfRegisters(consoleInput.insertNumberOfRegisters());

	}

	public RequestFrame createRequestFrame() {

		return new RequestFrame(0, unitIdentifier, functionCode,
				startingAdress, quantityOfRegisters);
	}

	public void loadDefinedInformation() {

		setUnitIdentifier(UNIT_IDENTIFER);

		setFunctionCode(FUNCTION_CODE);

		setStartingAdress(STARTING_ADRESS);

		setQuantityOfRegisters(QUANTITY_OF_REGISTERS);

	}

	public int getTransactionIdentifier() {
		return transactionIdentifier;
	}

	public void setTransactionIdentifier(int transactionIdentifier) {
		this.transactionIdentifier = transactionIdentifier;
	}

	public int getUnitIdentifier() {
		return unitIdentifier;
	}

	public void setUnitIdentifier(int unitIdentifier) {
		this.unitIdentifier = unitIdentifier;
	}

	public int getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(int functionCode) {
		this.functionCode = functionCode;
	}

	public int getStartingAdress() {
		return startingAdress;
	}

	public void setStartingAdress(int startingAdress) {
		this.startingAdress = startingAdress;
	}

	public int getQuantityOfRegisters() {
		return quantityOfRegisters;
	}

	public void setQuantityOfRegisters(int quantityOfRegisters) {
		this.quantityOfRegisters = quantityOfRegisters;
	}

}
