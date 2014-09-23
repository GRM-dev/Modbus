package atrem.modbus.swing;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {
	
	String[]	columnNames;
	String[]	columnValues	= {"5027", "23.3"};
	int			rows			= 0;
	int			columns			= 2;
	
	public DataTableModel(String[] columnNames) {
		this.columnNames = columnNames;
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
	
	public void setTableModel(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		fireTableDataChanged();
	}
}