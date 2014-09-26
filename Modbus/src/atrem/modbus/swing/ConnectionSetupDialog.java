package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import atrem.modbus.Domino;
import atrem.modbus.swing.exceptionhandlers.ConnectionErrorDialog;

public class ConnectionSetupDialog extends JDialog {
	private final Box contentBox = Box.createHorizontalBox();
	private JButton cancelButton;
	private JButton okButton;
	private JTextField ipAddressTextField;
	private JTextField portTextField;
	private static final String DEFAULTIPADRESS = "10.7.7.121";
	private static final int DEFAULTPORT = 502;
	private JPanel ipAddressPanel, serverPort;
	private JPanel buttonPanel;
	private Box box;
	private JPanel panel;
	private Container contentPane;
	private Domino domino;
	private ModbusSwing modbusSwing;

	public Domino getDomino() {
		return domino;
	}

	public ConnectionSetupDialog(ModbusSwing modbusSwing) {
		this.modbusSwing = modbusSwing;
		setTitle("Connection Setup");
		setBounds(300, 300, 350, 220);
		setResizable(false);
		setModal(true);
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(contentBox, BorderLayout.CENTER);
		contentBox.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentBox.add(createQuestionBox());
		contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
		pack();
	}

	private JPanel createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okButton = new JButton("Connect");
		okButton.setDefaultCapable(true);
		okButton.setMnemonic('c');
		cancelButton = new JButton("Cancel");
		okButton.addActionListener(new OkButtonListener());
		cancelButton.addActionListener(new CancelButtonListener());
		cancelButton.setMnemonic('a');
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		return buttonPanel;
	}

	private Box createQuestionBox() {
		box = Box.createVerticalBox();
		ipAddressTextField = new JTextField(DEFAULTIPADRESS);
		portTextField = new JTextField(String.valueOf(DEFAULTPORT));
		ipAddressPanel = createDialogPanel("IP Address: ", ipAddressTextField);
		serverPort = createDialogPanel("Server Port: ", portTextField);
		box.add(ipAddressPanel);
		box.add(serverPort);
		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		return box;
	}

	private JPanel createDialogPanel(String labelName, JTextField textField) {
		JLabel label = new JLabel(labelName);
		textField.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		panel = createEmptyPanel();
		panel.add(label);
		panel.add(textField);
		return panel;
	}

	private JPanel createEmptyPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		return panel;
	}

	private class OkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Integer port = Integer.parseInt(portTextField.getText());
			String ip = ipAddressTextField.getText();
			boolean connected = false;
			domino = new Domino(modbusSwing);
			while (!connected) {
				try {
					domino.connect(ip, port);
					connected = true;
				} catch (IOException e1) {
					ConnectionErrorDialog.show(e1);
					e1.printStackTrace();
					connected = false;
				}
			}
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