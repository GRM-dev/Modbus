package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class ConnectionSetup extends JDialog {

	private final Box contentBox = Box.createHorizontalBox();

	private JButton cancelButton;
	private JButton okButton;
	private JTextArea ipAddressTextArea;
	private JTextArea portTextArea;

	private String ipAddress;
	private int port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectionSetup dialog = new ConnectionSetup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConnectionSetup() {

		setTitle("Connection Setup");

		setBounds(300, 300, 350, 220);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentBox, BorderLayout.CENTER);

		contentBox.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentBox.add(createQuestionBox());

		getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);

		pack();

	}

	private JPanel createButtonPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		okButton.addActionListener(new OkButtonListener());
		cancelButton.addActionListener(new CancelButtonListener());

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		return buttonPanel;
	}

	private Box createQuestionBox() {

		Box box = Box.createVerticalBox();

		ipAddressTextArea = new JTextArea();
		portTextArea = new JTextArea();

		JPanel ipAddressPanel = createDialogPanel("IP Address: ",
				ipAddressTextArea);
		JPanel serverPort = createDialogPanel("Server Port: ", portTextArea);
		box.add(ipAddressPanel);
		box.add(serverPort);
		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		return box;
	}

	private JPanel createDialogPanel(String labelName, JTextArea textArea) {

		JLabel label = new JLabel(labelName);
		textArea.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(label);
		panel.add(textArea);
		return panel;

	}

	private class OkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();

		}
	}

	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}
