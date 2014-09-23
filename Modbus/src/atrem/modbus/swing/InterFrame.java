package atrem.modbus.swing;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

import atrem.modbus.Domino;

public class InterFrame extends JInternalFrame {

	private TableDemo tableDemo;
	public Domino domino;

	public InterFrame(String title) {

		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setTitle(title);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
		tableDemo = new TableDemo();
		getContentPane().add(tableDemo);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	public TableDemo getTableDemo() {
		return tableDemo;
	}

	public void setTableDemo(TableDemo tableDemo) {
		this.tableDemo = tableDemo;
	}

}
