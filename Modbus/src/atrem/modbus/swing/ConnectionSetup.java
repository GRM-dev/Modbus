package atrem.modbus.swing;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import atrem.modbus.Domino;

public class ConnectionSetup extends JDialog {
	private final Box	contentBox	= Box.createHorizontalBox();
	private JButton		cancelButton;
	private JButton		okButton;
	private JTextField	ipAddressTextField;
	private JTextField	portTextField;
	private String		ipAddress	= "10.7.7.121";
	private int			port		= 502;
	JPanel				ipAddressPanel, serverPort;
	private ModbusSwing	swing;
	
	/**
	 * @param modbusSwing
	 */
	public ConnectionSetup(ModbusSwing modbusSwing) {
		this.swing = modbusSwing;
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
		ipAddressTextField = new JTextField();
		portTextField = new JTextField();
		ipAddressPanel = createDialogPanel("IP Address: ", ipAddressTextField);
		serverPort = createDialogPanel("Server Port: ", portTextField);
		box.add(ipAddressPanel);
		box.add(serverPort);
		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		return box;
	}
	
	private JPanel createDialogPanel(String labelName, JTextField textField) {
		JLabel label = new JLabel(labelName);
		textField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(label);
		panel.add(textField);
		return panel;
	}
	
	private class OkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Domino.receiveConnectionParameters(ipAddressTextField.getText(),
			// Integer.parseInt(portTextField.getText()));
			Domino.receiveConnectionParameters(ipAddress, port);
			swing.newFrame(ipAddress);
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