package atrem.modbus.swing;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class ModbusFrameListener implements WindowListener {
	private ModbusSwing modbusSwing;

	public ModbusFrameListener(ModbusSwing modbusSwing) {
		this.modbusSwing = modbusSwing;
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		modbusSwing.closingOperations();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		modbusSwing.closingOperations();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
}