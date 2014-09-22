package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import atrem.modbus.Domino;

public class ModbusSwing extends JFrame {
	private final JPanel contentPanel;
	String[] columnNames = { "No.", "IP Address", "Port", "Registry Number",
			"Registry Value" };
	int rows = 100;
	int columns = 5;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu connectionMenu;
	private JMenu setupMenu;
	private JDesktopPane desk;
	private List<JInternalFrame> framesList;
	private Dimension screenSize;

	public ModbusSwing(final Domino domino) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		contentPanel = new JPanel();
		setTitle("Domino");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		framesList = new ArrayList<JInternalFrame>();
		setVisible(true);
		setMinimumSize(new Dimension(screenSize.width / 3,
				screenSize.height / 3));
		setBounds(100, 100, screenSize.width, screenSize.height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		desk = new JDesktopPane();
		desk.setMinimumSize(new Dimension());
		contentPanel.add(desk);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		fileMenu.add(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

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
				try {
					ReadWriteDefinition dialogRWD = new ReadWriteDefinition(
							domino);
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

		pack();
	}

	private void newConnection() {
		ConnectionSetup dialogCS = new ConnectionSetup();
		dialogCS.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialogCS.setVisible(true);

		JInternalFrame iFrame = new JInternalFrame("Created from Menu");
		framesList.add(iFrame);
		iFrame.setResizable(true);
		iFrame.setClosable(true);
		iFrame.setIconifiable(true);
		iFrame.setLocation(10, 10);
		iFrame.getContentPane().setLayout(new BorderLayout());
		DataTablePanel table = new DataTablePanel(columnNames, rows, columns);
		table.setVisible(true);
		iFrame.getContentPane().add(table);
		iFrame.setPreferredSize(new Dimension(300, 300));
		iFrame.pack();
		try {
			iFrame.setSelected(true);
			iFrame.setMaximizable(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		iFrame.setVisible(true);
		desk.add(iFrame);
	}
}
