package atrem.modbus.swing;

import javax.swing.JOptionPane;

public class ErrorsBox {
	public void connectionError() {
		JOptionPane.showMessageDialog(null, "Wyst¹pi³ b³¹d po³¹czenia",
				"Inane warning", JOptionPane.WARNING_MESSAGE);
	}
}
