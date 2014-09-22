package atrem.modbus.swing;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

	String[] columnNames;
	String[] columnValues = { "10.7.7.121", "502", "5027", "23.3" };
	int rows;
	int columns;

	public DataTableModel(String[] columnNames, int rows, int columns) {
		this.columnNames = columnNames;
		this.rows = rows;
		this.columns = columns;

	}

	@Override
	public int getRowCount() {
		return rows;
	}

	@Override
	public int getColumnCount() {
		return columns;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0)
			return rowIndex;
		else
			return columnValues[columnIndex - 1];
	}

	@Override
	public String getColumnName(int columnNumber) {
		return columnNames[columnNumber];

	}

}