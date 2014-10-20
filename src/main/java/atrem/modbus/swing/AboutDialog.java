package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class AboutDialog extends JDialog {
	public AboutDialog() {
		setTitle("About ...");
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(90, 100, 451, 215);

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		layeredPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel aboutLabel = new JLabel(
				"Modbus Controller Program.\r\n\r\nMade in Atrem by AiR");
		aboutLabel.setToolTipText("");
		aboutLabel.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		panel.add(aboutLabel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		layeredPane.add(buttonPanel, BorderLayout.SOUTH);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(closeButton);
	}

	public static void open() {
		AboutDialog abDialog = new AboutDialog();
		abDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		abDialog.setVisible(true);

	}
}
