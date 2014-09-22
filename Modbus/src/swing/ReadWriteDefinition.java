package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import atrem.modbus.Domino;

public class ReadWriteDefinition extends JDialog {

	private final Box contentBox = Box.createHorizontalBox();
	private JButton cancelButton;
	private JButton okButton;
	private JTextField slaveIdTextField;
	private JComboBox functionCodeComboBox;
	private JTextField startingAddressTextField;
	private JTextField quantityTextField;
	private JTextField scanRateTextField;

	private int slaveId;
	private int functionCode;
	private int startingAddress;
	private int quantity;
	private int scanRate;
	private Domino domino;

	/**
	 * Launch the application.
	 */

	public ReadWriteDefinition(Domino domino) {
		setTitle("Read/Write Definition");
		this.domino = domino;
		setBounds(300, 300, 332, 261);
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
		slaveIdTextField = new JTextField();
		functionCodeComboBox = new JComboBox();
		startingAddressTextField = new JTextField();
		quantityTextField = new JTextField();
		scanRateTextField = new JTextField();
		box.add(createDialogPanel("Slave ID", slaveIdTextField));
		box.add(createFunctionPanel(functionCodeComboBox));
		box.add(createDialogPanel("Address:", startingAddressTextField));
		box.add(createDialogPanel("Quantity:", quantityTextField));
		box.add(createDialogPanel("Scan Rate", scanRateTextField));

		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		return box;
	}

	private JPanel createFunctionPanel(JComboBox comboBox) {

		JLabel label = new JLabel("Function:");
		comboBox = new JComboBox();
		comboBox.setEditable(false);
		comboBox.addItem("03 Read Holding Registers");
		comboBox.addItem("06 Write Single Register");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(label);
		panel.add(comboBox);
		return panel;
	}

	private JPanel createDialogPanel(String labelName, JTextField textField) {
		JLabel label = new JLabel(labelName);
		textField = new JTextField();
		textField.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(label);
		panel.add(textField);
		return panel;
	}

	private JPanel createDialogPanel(String labelName, String labelName2,
			JTextField textField) {
		JLabel label = new JLabel(labelName);
		textField = new JTextField();
		textField.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JLabel label2 = new JLabel(labelName2);
		JPanel smallPanel = new JPanel();
		smallPanel.setLayout(new GridLayout(0, 2));
		smallPanel.add(textField);
		smallPanel.add(label2);

		JPanel bigPanel = new JPanel();
		bigPanel.setLayout(new GridLayout(0, 2));
		bigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bigPanel.add(label);
		bigPanel.add(smallPanel);
		return bigPanel;
	}

	private class OkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// domino.creatRequestFrameFactory(
			// Integer.parseInt(slaveIdTextArea.getText()),
			// Integer.parseInt(startingAddressTextArea.getText()),
			// Integer.parseInt(quantityTextArea.getText()), 3);

			domino.creatRequestFrameFactory(5, 3027, 2, 3);

		}
	}

	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}