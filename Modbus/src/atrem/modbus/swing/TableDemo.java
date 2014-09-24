package atrem.modbus.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
		scrollPane.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					@Override
					public void adjustmentValueChanged(AdjustmentEvent e) {
						e.getAdjustable().setValue(
								e.getAdjustable().getMaximum());

					}
				});
		add(scrollPane);
	}

	class MyTableModel extends AbstractTableModel {

		// private Object[][] data = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
		private String[] columnNames = {"No.", "Registry Address",
				"Registry Value"};
		private List<Data> dataList = new ArrayList<Data>();

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return dataList.size();
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {
			if (col == 0)
				return row;
			if (col == 1)
				return dataList.get(row).getRegistryAddress();
			if (col == 2)
				return dataList.get(row).getRegistryValue();
			return 0;

		}

		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public void addRow(Data nextData) {
			dataList.add(nextData);
			fireTableRowsInserted(dataList.size() - 1, dataList.size());
		}

	}

	public MyTableModel getMyTableModel() {
		return myTableModel;
	}

}
