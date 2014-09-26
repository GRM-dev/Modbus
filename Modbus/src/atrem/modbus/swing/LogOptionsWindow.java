package atrem.modbus.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LogOptionsWindow extends JDialog {
	public LogOptionsWindow() {
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 388, 97);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(189, 70, 89, 23);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(288, 70, 89, 23);
		panel.add(btnCancel);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(78, 11, 299, 36);
		panel.add(textPane);

		JLabel pathLabel = new JLabel("Log path:");
		pathLabel.setBounds(10, 11, 58, 36);
		panel.add(pathLabel);
	}
}
