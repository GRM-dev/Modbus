package atrem.modbus.swing;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

import atrem.modbus.Controller;
import atrem.modbus.Domino;
import atrem.modbus.Request;
import atrem.modbus.RequestHandler;
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

	public void initializeNewRequest(Request request) {
		Controller controller = domino.getController();
		controller.addRequest(request, new RequestHandler() {

			@Override
			public void frameReceiver(ResponseFrame responseFrame) {
				addDataToTable(responseFrame);
			}
		});
		controller.startNewRequestTask(request);
	}

	public TableDemo getTableDemo() {
		return tableDemo;
	}

	public void setTableDemo(TableDemo tableDemo) {
		this.tableDemo = tableDemo;
	}

	private void addDataToTable(final ResponseFrame responseFrame) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Data nextData = new Data(responseFrame.getRegistryValue(),
						responseFrame.getDataValue());
				tableDemo.getMyTableModel().addRow(nextData);

			}
		});
	}
}
