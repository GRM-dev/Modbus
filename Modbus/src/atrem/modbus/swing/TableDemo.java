package atrem.modbus.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableDemo extends JPanel {
	private JTable table;
	private MyTableModel myTableModel;

	public TableDemo() {

		super(new GridLayout(1, 0));
		myTableModel = new MyTableModel();
		table = new JTable(myTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

	class MyTableModel extends AbstractTableModel {

		// private Object[][] data = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
		private String[] columnNames = {"No.", "Registry Address",
				"Registry Value"};
		private List<Integer[]> data = new ArrayList<Integer[]>();

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data.get(row)[col];
		}

		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public void addRow(Integer[] nextData) {
			data.add(nextData);
			fireTableCellUpdated(data.size() - 2, data.size() - 1);
		}

	}

	public MyTableModel getMyTableModel() {
		return myTableModel;
	}

}
