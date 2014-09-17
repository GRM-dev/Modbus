package atrem.modbus;

import java.util.ArrayList;

public class Koder {
	private ArrayList<Byte> bytesList = new ArrayList<Byte>();

	public void code(ModbusFrame frame) {
		codeTCPip(frame.getIdTCP(), 1); // zapytac sie ile bajt�w
		codeAdress(frame.getSlaveAdress(), 1);
		codeFunction(frame.getFunctionCode(), 1);
		codeFirstRegister(frame.getContent().getFirstRegister(), 2);
		codeNumberOfRegisters(frame.getContent().getNumberOfRegisters(), 2);

	}

	public void codeTCPip(int integer, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		for (int i = 0; i < numberOfBytes; i++) {
			bytes[i] = (byte) (integer >>> (i * 8));
			bytesList.add(bytes[i]);
		}

	}

	public void codeAdress(int integer, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		for (int i = 0; i < numberOfBytes; i++) {
			bytes[i] = (byte) (integer >>> (i * 8));
			bytesList.add(bytes[i]);
		}
	}

	public void codeFunction(int integer, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		for (int i = 0; i < numberOfBytes; i++) {
			bytes[i] = (byte) (integer >>> (i * 8));
			bytesList.add(bytes[i]);
		}
	}

	public void codeFirstRegister(int integer, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		for (int i = numberOfBytes - 1; i >= 0; i--) {
			bytes[i] = (byte) (integer >> (i * 8));

			int w = bytes[i] & 0xff;
			System.out.println(";;" + w);
			bytesList.add(bytes[i]);
		}
	}

	public void codeNumberOfRegisters(int integer, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		for (int i = 0; i < numberOfBytes; i++) {
			bytes[i] = (byte) (integer >>> (i * 8));
			bytesList.add(bytes[i]);
		}
	}

	public ArrayList<Byte> getBytesList() {
		return bytesList;
	}

	public byte[] changeListToArray() {
		byte[] bytetab = new byte[bytesList.size()];
		for (int i = 0; i < bytesList.size(); i++) {
			bytetab[i] = bytesList.get(i);
		}
		return bytetab;

	}
}
