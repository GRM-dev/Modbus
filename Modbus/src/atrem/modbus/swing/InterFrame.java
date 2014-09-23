package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

public class InterFrame extends JInternalFrame {
	private DataTablePanel	table;
	
	public InterFrame(String name, String[] columnNames) {
		ModbusSwing.framesList.put(name, this);
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
		table = new DataTablePanel(columnNames);
		table.setVisible(true);
		getContentPane().add(table);
		setPreferredSize(new Dimension(300, 300));
		pack();
		try {
			setSelected(true);
			setMaximizable(true);
		}
		catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}
	
	public void setupTable(int rows, int columns) {
		table.setTableModel(rows, columns);
	}
}
