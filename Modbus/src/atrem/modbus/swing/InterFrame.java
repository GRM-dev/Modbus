package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JInternalFrame;

public class InterFrame extends JInternalFrame {
	public InterFrame(String name, List framesList, String[] columnNames, int rows,
			int columns) {
		framesList.add(this);
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
		DataTablePanel table = new DataTablePanel(columnNames, rows, columns);
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
}
