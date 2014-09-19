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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import atrem.modbus.Domino;

public class ReadWriteDefinition extends JDialog {

	private final Box contentBox = Box.createHorizontalBox();
	private JButton cancelButton;
	private JButton okButton;
	private JTextArea slaveIdTextArea;
	private JComboBox functionCodeComboBox;
	private JTextArea startingAddressTextArea;
	private JTextArea quantityTextArea;
	private JTextArea scanRateTextArea;

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
		slaveIdTextArea = new JTextArea();
		functionCodeComboBox = new JComboBox();
		startingAddressTextArea = new JTextArea();
		quantityTextArea = new JTextArea();
		scanRateTextArea = new JTextArea();
		box.add(createDialogPanel("Slave ID", slaveIdTextArea));
		box.add(createFunctionPanel(functionCodeComboBox));
		box.add(createDialogPanel("Address:", startingAddressTextArea));
		box.add(createDialogPanel("Quantity:", quantityTextArea));
		box.add(createDialogPanel("Scan Rate", "[ms]", scanRateTextArea));

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

	private JPanel createDialogPanel(String labelName, JTextArea textArea) {
		JLabel label = new JLabel(labelName);
		textArea = new JTextArea();
		textArea.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(label);
		panel.add(textArea);
		return panel;
	}

	private JPanel createDialogPanel(String labelName, String labelName2,
			JTextArea textArea) {
		JLabel label = new JLabel(labelName);
		textArea = new JTextArea();
		textArea.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JLabel label2 = new JLabel(labelName2);
		JPanel smallPanel = new JPanel();
		smallPanel.setLayout(new GridLayout(0, 2));
		smallPanel.add(textArea);
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