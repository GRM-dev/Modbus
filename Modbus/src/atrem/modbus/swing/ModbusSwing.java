package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import atrem.modbus.Domino;
import atrem.modbus.Request;

public class ModbusSwing extends JFrame {
	private JDesktopPane			desk;
	private List<JInternalFrame>	internalFramesList	= new ArrayList<JInternalFrame>();
	private Domino					domino;
	private JLabel					connectionStatus;
	
	public static void main(String[] args) {
		Domino domino = new Domino();
	}
	
	public ModbusSwing(Domino domino) {
		this.domino = domino;
		initialize();
	}
	
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("ModbusExplorer");
		int screenHeight = (int) screenSize.getHeight();
		int screenWidth = (int) screenSize.getWidth();
		int frameWidth = screenWidth / 2 + screenWidth / 8;
		int frameHeight = frameWidth * 3 / 4;
		setBounds(screenWidth - screenWidth / 4 - frameWidth, screenHeight / 2
				- frameHeight / 2, frameWidth, frameHeight);
		getContentPane().setLayout(new BorderLayout());
		add(createContentPanel(), BorderLayout.CENTER);
		setJMenuBar(createMenuBar());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		desk = new JDesktopPane();
		contentPanel.add(desk);
		return contentPanel;
	}
	
	public void initializeInterFrame(JInternalFrame jInternalFrame, Request request) {
		InterFrame interFrame = (InterFrame) jInternalFrame;
		interFrame.initializeNewRequest(request);
	}
	
	public JInternalFrame createInterFrame(String name) {
		JInternalFrame iFrame = new InterFrame(name, domino);
		internalFramesList.add(iFrame);
		desk.add(iFrame);
		return iFrame;
	}
	
	private JMenuBar createMenuBar() {
		connectionStatus = new JLabel(" DISCONNECTED ");
		connectionStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createConnectionMenu());
		menuBar.add(createSetupMenu());
		menuBar.add(connectionStatus);
		return menuBar;
	}
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic('f');
		menu.add(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return menu;
	}
	
	private JMenu createConnectionMenu() {
		JMenu menu = new JMenu("Connection");
		menu.setMnemonic('c');
		menu.add(new AbstractAction("Connect") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					newConnection();
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		
		return menu;
	}
	
	private JMenu createSetupMenu() {
		JMenu menu = new JMenu("Setup");
		menu.setMnemonic('s');
		menu.add(new AbstractAction("Read/Write Definition...") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setupDefinition();
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		return menu;
	}
	
	private void newConnection() {
		ConnectionSetupDialog connectionSetupDialog = new ConnectionSetupDialog(domino);
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
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private String createName() {
		return "Modbus" + ((Integer) (internalFramesList.size() + 1)).toString();
	}
	
	public List<JInternalFrame> getFramesList() {
		return internalFramesList;
	}
	
	public void setFramesList(List<JInternalFrame> framesList) {
		this.internalFramesList = framesList;
	}
	
	public void setStatus(String status, Color color) {
		connectionStatus.setText("  " + status + "  ");
		connectionStatus.setForeground(color);
	}
}
