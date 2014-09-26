package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import atrem.modbus.Domino;
import atrem.modbus.Request;

/**
 * Main Frame - JFrame - of program
 */
public class ModbusSwing extends JFrame {

	public static final int FONT_SIZE = 12;
	public static final String FONT = "ARIAL";
	private JDesktopPane desk;
	private List<JInternalFrame> internalFramesList = new ArrayList<JInternalFrame>();
	private Domino domino;
	private JLabel connectionStatus;
	private Container contentPane;
	private JProgressBar progressBar;

	public ModbusSwing(Domino domino) {
		this.domino = domino;
		initialize();
	}

	private void initialize() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane = getContentPane();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("ModbusExplorer");
		int screenHeight = (int) screenSize.getHeight();
		int screenWidth = (int) screenSize.getWidth();
		int frameWidth = screenWidth / 2 + screenWidth / 8;
		int frameHeight = frameWidth * 3 / 4;
		setBounds(screenWidth - screenWidth / 4 - frameWidth, screenHeight / 2
				- frameHeight / 2, frameWidth, frameHeight);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(createContentPanel(), BorderLayout.CENTER);
		contentPane.add(createStatusPanel(), BorderLayout.SOUTH);
		setJMenuBar(createMenuBar());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		addWindowListener(new ModbusFrameListener());
		SwingUtilities.updateComponentTreeUI(this);
	}

	private Component createStatusPanel() {
		StatusBar statusPanel = new StatusBar();
		progressBar = statusPanel.getProgressBar();
		return statusPanel;
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		desk = new JDesktopPane();
		contentPanel.add(desk);
		return contentPanel;
	}

	public void initializeInterFrame(InterFrame interFrame, Request request) {
		interFrame.initializeNewRequest(request);
	}

	public InterFrame createInterFrame(String name) {
		InterFrame iFrame = new InterFrame(name, domino);
		internalFramesList.add(iFrame);
		desk.add(iFrame);
		return iFrame;
	}

	private JMenuBar createMenuBar() {
		connectionStatus = new JLabel(" DISCONNECTED ");
		connectionStatus.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		// connectionStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createConnectionMenu());
		menuBar.add(createSetupMenu());

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutMenuItem = new JMenuItem("About ...");
		helpMenu.add(aboutMenuItem);
		menuBar.add(connectionStatus);
		return menuBar;
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic('f');
		menu.setFont(new Font("Segoe UI", Font.PLAIN, FONT_SIZE));
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic('e');
		menuItem.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		menuItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menu.add(menuItem);
		return menu;
	}

	private JMenu createConnectionMenu() {
		JMenu menu = new JMenu("Connection");
		menu.setMnemonic('c');
		menu.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		JMenuItem menuItem = new JMenuItem("Connect");
		menuItem.setMnemonic('c');
		menuItem.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		menuItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					newConnection();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		menu.add(menuItem);

		JMenuItem closeAllConnMenuItem = new JMenuItem("Close All Connections");
		closeAllConnMenuItem.setMnemonic('a');
		closeAllConnMenuItem.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		menu.add(closeAllConnMenuItem);

		return menu;
	}

	private JMenu createSetupMenu() {
		JMenu menu = new JMenu("Setup");
		menu.setMnemonic('s');
		menu.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		JMenuItem rwDefMenuItem = new JMenuItem("Read/Write Definition...");
		rwDefMenuItem.setMnemonic('r');
		rwDefMenuItem.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		rwDefMenuItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setupDefinition();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		menu.add(rwDefMenuItem);

		JMenuItem optionsMenuItem = new JMenuItem("Options");
		optionsMenuItem.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		optionsMenuItem.setMnemonic('o');
		menu.add(optionsMenuItem);
		optionsMenuItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		return menu;
	}

	private void newConnection() {
		ConnectionSetupDialog connectionSetupDialog = new ConnectionSetupDialog(
				domino);
		connectionSetupDialog.setDefaultCloseOperation(test());
		connectionSetupDialog.setVisible(true);
	}

	private static int test() {
		return WindowConstants.DISPOSE_ON_CLOSE;
	}

	private void setupDefinition() {
		try {
			ReadWriteDefinitionDialog readWriteDefinitionDialog = new ReadWriteDefinitionDialog(
					this);
			readWriteDefinitionDialog.setDefaultCloseOperation(test());
			readWriteDefinitionDialog.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private String createName() {
		return "Modbus"
				+ ((Integer) (internalFramesList.size() + 1)).toString();
	}

	public List<JInternalFrame> getFramesList() {
		return internalFramesList;
	}

	public void setFramesList(List<JInternalFrame> framesList) {
		this.internalFramesList = framesList;
	}

	public JProgressBar getProgressBar() {
		return this.progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public void setStatus(String status, Color color) {
		connectionStatus.setText("  " + status + "  ");
		connectionStatus.setForeground(color);
	}

	private void closingOperations() {
		closeConnection();
	}

	private void closeConnection() {
		try {
			domino.getController().getConnection().closeConnection();
			JOptionPane.showMessageDialog(null, "Po³¹czenie zakoñczone.");
		} catch (Exception exc) {
			dispose();
		}
	}

	private class ModbusFrameListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			closingOperations();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			closingOperations();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
