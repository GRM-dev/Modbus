package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ModbusSwing extends JFrame {

	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		try {
			ModbusSwing dialog = new ModbusSwing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModbusSwing() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new AbstractAction("Exit") {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu connectionMenu = new JMenu("Connection");
		connectionMenu.add(new AbstractAction("Connect...") {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ConnectionSetup dialogCS = new ConnectionSetup();
					dialogCS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialogCS.setVisible(true);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		JMenu setupMenu = new JMenu("Setup");
		setupMenu.add(new AbstractAction("Read/Write Definition...") {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ReadWriteDefinition dialogRWD = new ReadWriteDefinition();
					dialogRWD
							.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialogRWD.setVisible(true);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		menuBar.add(fileMenu);
		menuBar.add(connectionMenu);
		menuBar.add(setupMenu);

		String[] columnNames = {"No.", "IP Address", "Port", "Registry Number",
				"Registry Value"};
		int rows = 100;
		int columns = 5;
		DataTablePanel dataTablePanel = new DataTablePanel(columnNames, rows,
				columns);
		add(dataTablePanel);

		pack();
	}

}

class DataTablePanel extends JPanel {

	public DataTablePanel(String[] columnNames, int rows, int columns) {

		TableModel tableModel = new DataTableModel(columnNames, rows, columns);
		JTable table = new JTable(tableModel);
		add(new JScrollPane(table));

	}
}

class DataTableModel extends AbstractTableModel {

	String[] columnNames;
	String[] columnValues = {"10.7.7.121", "502", "5027", "23.3"};
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