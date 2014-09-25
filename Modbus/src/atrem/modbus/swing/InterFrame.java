package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

import atrem.modbus.Controller;
import atrem.modbus.Domino;
import atrem.modbus.Request;
import atrem.modbus.RequestListener;
import atrem.modbus.frames.ResponseFrame;

public class InterFrame extends JInternalFrame {

	private TableDemo tableDemo;
	public Domino domino;

	public InterFrame(String title, Domino domino) {
		this.domino = domino;
		setResizable(true);
		setMinimumSize(new Dimension(100, 60));
		setClosable(true);
		setIconifiable(true);
		setTitle(title);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
		tableDemo = new TableDemo();
		getContentPane().add(tableDemo);
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setMaximizable(true);

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	public void initializeNewRequest(Request request) {
		Controller controller = domino.getController();
		controller.addRequest(request, new RequestListener() {
			@Override
			public void receiveFrame(ResponseFrame responseFrame) {
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
