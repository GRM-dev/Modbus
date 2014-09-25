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

import atrem.modbus.Request;

public class ReadWriteDefinitionDialog extends JDialog {
	private final Box contentBox = Box.createHorizontalBox();
	private JButton cancelButton;
	private JButton okButton;
	private JTextField slaveIdTextField;
	private JComboBox<String> functionCodeComboBox;
	private JTextField startingAddressTextField;
	private JTextField quantityTextField;
	private JTextField scanRateTextField;
	private ModbusSwing modbusSwing;
	private String[] functionNames = { "01 Read Coils",
			"02 Read Discrete Inputs", "03 Read Holding Registers",
			"04 Read Input Registers", "05 Write Single Coil",
			"06 Write Single Register" };

	// private Domino domino;

	public ReadWriteDefinitionDialog(ModbusSwing modbusSwing) {
		this.modbusSwing = modbusSwing;
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
		slaveIdTextField = new JTextField("5");
		functionCodeComboBox = new JComboBox(functionNames);
		startingAddressTextField = new JTextField("3027");
		quantityTextField = new JTextField("2");
		scanRateTextField = new JTextField("10000");
		box.add(createDialogPanel("Slave ID", slaveIdTextField));
		box.add(createFunctionPanel(functionCodeComboBox));
		box.add(createDialogPanel("First Registry Address:",
				startingAddressTextField));
		box.add(createDialogPanel("Quantity Of Registries:", quantityTextField));
		box.add(createDialogPanel("Scan Rate", "[ms]", scanRateTextField));

		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		return box;
	}

	private JPanel createFunctionPanel(JComboBox comboBox) {
		JLabel label = new JLabel("Function:");
		comboBox.setSelectedIndex(2);
		comboBox.setEditable(false);
		JPanel panel = createEmptyPanel();
		panel.add(label);
		panel.add(comboBox);
		return panel;
	}

	private JPanel createDialogPanel(String labelName, JTextField textField) {
		JLabel label = new JLabel(labelName);
		textField.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = createEmptyPanel();
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
		JPanel bigPanel = createEmptyPanel();
		bigPanel.add(label);
		bigPanel.add(smallPanel);
		return bigPanel;

	}

	private JPanel createEmptyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		return panel;
	}

	private class OkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Request request = new Request(Integer.parseInt(slaveIdTextField
					.getText()), functionCodeComboBox.getSelectedIndex() + 1,
					Integer.parseInt(startingAddressTextField.getText()),
					Integer.parseInt(quantityTextField.getText()),
					Integer.parseInt(scanRateTextField.getText()));

			modbusSwing.initializeNewFrame(startingAddressTextField.getText());
			dispose();
			modbusSwing.onListener(request); // TODO mzoe usunac paweligala
		}
	}

	private class CancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}
