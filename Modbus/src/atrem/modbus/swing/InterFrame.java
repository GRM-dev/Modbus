package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import atrem.modbus.Domino;

public class InterFrame extends JInternalFrame {

	private TableDemo tableDemo;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu connectionMenu;
	private JMenu setupMenu;
	public Domino domino;

	public InterFrame(String name, String[] columnNames) {

		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
		tableDemo = new TableDemo(columnNames);
		tableDemo.setVisible(true);
		getContentPane().add(tableDemo);
		setPreferredSize(new Dimension(300, 300));
		pack();
		try {
			setSelected(true);
			setMaximizable(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		connectionMenu = new JMenu("Connection");
		connectionMenu.add(new AbstractAction("Connect") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					newConnection();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		setupMenu = new JMenu("Setup");
		setupMenu.add(new AbstractAction("Read/Write Definition...") {
			@Override
			public void actionPerformed(ActionEvent e) {
				setupDefinition(domino);
			}
		});

		menuBar.add(connectionMenu);
		menuBar.add(setupMenu);

	}

	public TableDemo getTableDemo() {
		return tableDemo;
	}

	public void setTableDemo(TableDemo tableDemo) {
		this.tableDemo = tableDemo;
	}

	private void newConnection() {
		ConnectionSetup dialogCS = new ConnectionSetup(this);
		dialogCS.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialogCS.setVisible(true);
	}

	private void setupDefinition(final Domino domino) {
		try {
			ReadWriteDefinition dialogRWD = new ReadWriteDefinition(domino,
					this);
			dialogRWD
					.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialogRWD.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
