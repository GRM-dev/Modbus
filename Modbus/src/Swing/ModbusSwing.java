package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ModbusSwing extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModbusSwing dialog = new ModbusSwing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModbusSwing() {

		setBounds(300, 300, 200, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel ipLabel = new JLabel("IP:");
		JTextArea ipTextArea = new JTextArea(1, 10);
		ipTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Container ipContainer = new Container();
		ipContainer.setLayout(new GridLayout(0, 2));
		ipContainer.add(ipLabel);
		ipContainer.add(ipTextArea);

		JLabel slaveIdLabel = new JLabel("Slave ID:");
		JTextArea slaveIdTextArea = new JTextArea("");
		slaveIdTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Container slaveIdContainer = new Container();
		slaveIdContainer.setLayout(new GridLayout(0, 2));
		slaveIdContainer.add(slaveIdLabel);
		slaveIdContainer.add(slaveIdTextArea);

		JLabel functionLabel = new JLabel("Function:");
		JComboBox functionComboBox = new JComboBox();
		functionComboBox.setEditable(false);
		functionComboBox.addItem("03 Read Holding Registers");
		functionComboBox.addItem("06 Write Single Register");
		Container functionContainer = new Container();
		functionContainer.setLayout(new GridLayout(0, 2));
		functionContainer.add(functionLabel);
		functionContainer.add(functionComboBox);

		JLabel addressLabel = new JLabel("Address:");
		JTextArea addressTextArea = new JTextArea("");
		addressTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Container addressContainer = new Container();
		addressContainer.setLayout(new GridLayout(0, 2));
		addressContainer.add(addressLabel);
		addressContainer.add(addressTextArea);

		JLabel quantityLabel = new JLabel("Quantity:");
		JTextArea quantityTextArea = new JTextArea("");
		quantityTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Container quantityContainer = new Container();
		quantityContainer.setLayout(new GridLayout(0, 2));
		quantityContainer.add(quantityLabel);
		quantityContainer.add(quantityTextArea);

		JLabel scanRateLabel = new JLabel("ScanRate:");
		JTextArea scanRateTextArea = new JTextArea("");
		scanRateTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel timeUnitLabel = new JLabel("[ms]");

		Container textAreaContainer = new Container();
		textAreaContainer.setLayout(new GridLayout(0, 2));
		textAreaContainer.add(scanRateTextArea);
		textAreaContainer.add(timeUnitLabel);

		Container scanRateContainer = new Container();
		scanRateContainer.setLayout(new GridLayout(0, 2));
		scanRateContainer.add(scanRateLabel);
		scanRateContainer.add(textAreaContainer);

		contentPanel.add(ipContainer);
		contentPanel.add(Box.createHorizontalStrut(10));
		contentPanel.add(slaveIdContainer);
		contentPanel.add(Box.createHorizontalStrut(10));
		contentPanel.add(functionContainer);
		contentPanel.add(Box.createHorizontalStrut(10));
		contentPanel.add(quantityContainer);
		contentPanel.add(Box.createHorizontalStrut(10));
		contentPanel.add(scanRateContainer);

	}
}
