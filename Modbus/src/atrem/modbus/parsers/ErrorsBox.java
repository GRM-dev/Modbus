package atrem.modbus.parsers;

import javax.swing.JOptionPane;

public class ErrorsBox {
	public void ConnectionError() {
		JOptionPane.showMessageDialog(null, "Wyst�pi� b��d po��czenia",
				"Inane warning", JOptionPane.WARNING_MESSAGE);
	}
}
