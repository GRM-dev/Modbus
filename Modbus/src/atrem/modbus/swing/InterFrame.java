package atrem.modbus.swing;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

import atrem.modbus.Domino;

public class InterFrame extends JInternalFrame {
<<<<<<< HEAD

	private TableDemo tableDemo;
	public Domino domino;

	public InterFrame(String title) {

=======
	private DataTablePanel table;

	public InterFrame(String name, String[] columnNames) {
		ModbusSwing.framesList.put(name, this);
>>>>>>> branch 'master' of https://github.com/PatrykGala/Modbus.git
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setTitle(title);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
<<<<<<< HEAD
		tableDemo = new TableDemo();
		getContentPane().add(tableDemo);

=======
		table = new DataTablePanel(columnNames);
		table.setVisible(true);
		getContentPane().add(table);
		setPreferredSize(new Dimension(300, 300));
		pack();
		try {
			setSelected(true);
			setMaximizable(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
>>>>>>> branch 'master' of https://github.com/PatrykGala/Modbus.git
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

<<<<<<< HEAD
	public TableDemo getTableDemo() {
		return tableDemo;
=======
	public void setupTable(int rows, int columns) {
		table.setTableModel(rows, columns);
>>>>>>> branch 'master' of https://github.com/PatrykGala/Modbus.git
	}

<<<<<<< HEAD
	public void setTableDemo(TableDemo tableDemo) {
		this.tableDemo = tableDemo;
=======
	public void addNewRow(int... values) {
		switch (values.length) {
		case 0:
			table.addNewRow();
			break;
		case 1:
			table.addNewRow(values[0]);
			break;
		default:
			System.out.println("Wrong values count: " + values.length);
			break;
		}
>>>>>>> branch 'master' of https://github.com/PatrykGala/Modbus.git
	}

}
