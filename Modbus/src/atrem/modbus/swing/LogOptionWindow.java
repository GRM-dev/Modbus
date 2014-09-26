package atrem.modbus.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LogOptionWindow extends JDialog {
	public LogOptionWindow() {
		// setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(300, 300, 456, 299);
		;
		JPanel pathOptions = new JPanel();
		pathOptions.setBounds(0, 128, 434, 134);
		getContentPane().add(pathOptions);
		pathOptions.setLayout(null);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBrowse.setBounds(347, 100, 67, 23);
		pathOptions.add(btnBrowse);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(43, 66, 371, 23);
		pathOptions.add(textPane);

		JLabel lblPathlabel = new JLabel("Log path:");
		lblPathlabel.setBounds(43, 38, 200, 23);
		pathOptions.add(lblPathlabel);

		JPanel filters = new JPanel();
		filters.setBounds(0, 0, 434, 128);
		getContentPane().add(filters);
	}
}
