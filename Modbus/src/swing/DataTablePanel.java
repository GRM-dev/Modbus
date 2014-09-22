package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class DataTablePanel extends JPanel {

	public DataTablePanel(String[] columnNames, int rows, int columns) {

		TableModel tableModel = new DataTableModel(columnNames, rows, columns);
		JTable table = new JTable(tableModel);
		add(new JScrollPane(table));

	}
}
