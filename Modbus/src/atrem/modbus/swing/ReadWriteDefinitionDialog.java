package atrem.modbus.swing;

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

public class ReadWriteDefinitionDialog extends JDialog {
	private final Box contentBox = Box.createHorizontalBox();
	private JButton cancelButton;
	private JButton okButton;
	private JTextField slaveIdTextField;
	private JComboBox<String> functionCodeComboBox;
	private JTextField startingAddressTextField;
	private JTextField quantityTextField;
	private JTextField scanRateTextField;

	private Domino domino;

	public ReadWriteDefinitionDialog(Domino domino) {
		this.domino = domino;
		setTitle("Read/Write Definition");
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
		functionCodeComboBox = new JComboBox<String>();
		startingAddressTextField = new JTextField();
		quantityTextField = new JTextField();
		scanRateTextField = new JTextField();
		box.add(createDialogPanel("Slave ID", slaveIdTextField));
		box.add(createFunctionPanel(functionCodeComboBox));
		box.add(createDialogPanel("First Registry Address:",
				startingAddressTextField));
		box.add(createDialogPanel("Quantity Of Registries:", quantityTextField));
		box.add(createDialogPanel("Scan Rate", scanRateTextField));

		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		return box;
	}

	private JPanel createFunctionPanel(JComboBox comboBox) {
		JLabel label = new JLabel("Function:");
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
			// Integer.parseInt(slaveIdTextField.getText()),
			// Integer.parseInt(startingAddressTextField.getText()),
			// Integer.parseInt(quantityTextField.getText()), 3);

			// domino.creatRequestFrameFactory(5, 3027, 2, 3);
			domino.getModbusSwing().initializeNewFrame();
			dispose();

			// ModbusSwing.framesList.get("10.7.7.121").setupTable(10, 3);
		}
	}

	private class CancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}