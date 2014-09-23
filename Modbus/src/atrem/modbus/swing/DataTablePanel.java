package atrem.modbus.swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataTablePanel extends JPanel {
	
	private String[]		columnNames;
	private int				rows;
	private int				columns;
	private DataTableModel	tableModel;
	private JTable			table;
	
	/**
	 * Panel with Table.
	 * 
	 * @param columnNames
	 */
	public DataTablePanel(String[] columnNames) {
		this.columnNames = columnNames;
		tableModel = new DataTableModel(columnNames);
		table = new JTable(tableModel);
		add(new JScrollPane(table));
	}
	
	/**
	 * setupTable after connect.
	 * 
	 * @param rows
	 * @param columns
	 */
	public void setTableModel(int rows, int columns) {
		tableModel.setTableModel(rows, columns);
		table.updateUI();
	}
	
	public void addNewRow() {
		
	}
	
	public void addNewRow(int value) {
		
	}
}