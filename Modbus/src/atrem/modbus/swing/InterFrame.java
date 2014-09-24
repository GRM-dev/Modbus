package atrem.modbus.swing;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

import atrem.modbus.ControllerListener;
import atrem.modbus.Domino;
import atrem.modbus.frames.ResponseFrame;

public class InterFrame extends JInternalFrame {

	private TableDemo tableDemo;
	public Domino domino;

	public InterFrame(String title, Domino domino) {
		this.domino = domino;
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

	public void function() {
		domino.getController().addListener(new ControllerListener() {

			@Override
			public void frameReceiver(ResponseFrame responseFrame) {
				addDataToTable(responseFrame);

			}

		});

	}

	private void addDataToTable(final ResponseFrame responseFrame1) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Data nextData = new Data(responseFrame1.getRegistryValue(),
						responseFrame1.getDataValue());
				tableDemo.getMyTableModel().addRow(nextData);

			}
		});
	}
}
