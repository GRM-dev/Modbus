package atrem.modbus.swing;

import javax.swing.JOptionPane;

public class ErrorsBox {
	public void connectionError() {
		JOptionPane.showMessageDialog(null, "Wyst�pi� b��d po��czenia",
				"Inane warning", JOptionPane.WARNING_MESSAGE);
	}
}
