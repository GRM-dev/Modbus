package atrem.modbus.parsers;

import java.util.ArrayList;

import frames.RequestFrame;

public class Koder {

	private ArrayList<Byte> bytesList = new ArrayList<Byte>();

	public void codeFrame(RequestFrame frame) {

		codeTCPip(frame.getTransactionIdentifier(), 2); // zapytac sie ile
		codeProtocolIdentifier();
		codeLengthField(6, 2);
		codeAdress(frame.getUnitIdentifier(), 1);
		codeFunction(frame.getFunctionCode(), 1);
		codeFirstRegister(frame.getStartingAdress(), 2);
		codeNumberOfRegisters(frame.getQuantityOfRegisters(), 2);

	}

	public void codeProtocolIdentifier() {
		byte[] bytes = { 0, 0 };
		bytesList.add(bytes[0]);
		bytesList.add(bytes[1]);

	}

	private void codeTCPip(int integer, int numberOfBytes) {
		codeToBytes(integer, numberOfBytes);

	}

	private void codeLengthField(int integer, int numberOfBytes) {
		codeToBytes(integer, numberOfBytes);

	}

	private void codeAdress(int integer, int numberOfBytes) {
		codeToBytes(integer, numberOfBytes);
	}

	private void codeFunction(int integer, int numberOfBytes) {
		codeToBytes(integer, numberOfBytes);
	}

	private void codeNumberOfRegisters(int integer, int numberOfBytes) {
		codeToBytes(integer, numberOfBytes);
	}

	private void codeFirstRegister(int integer, int numberOfBytes) {
		codeToBytes(integer, numberOfBytes);
	}

	void codeToBytes(int integer, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		for (int i = numberOfBytes - 1; i >= 0; i--) {
			bytes[i] = (byte) (integer >>> (i * 8));
			bytesList.add(bytes[i]);
		}

	}

	public byte[] changeListToArray() {
		byte[] bytetab = new byte[bytesList.size()];
		for (int i = 0; i < bytesList.size(); i++) {
			bytetab[i] = bytesList.get(i);
		}
		return bytetab;

	}

	public ArrayList<Byte> getBytesList() {
		return bytesList;
	}
}
