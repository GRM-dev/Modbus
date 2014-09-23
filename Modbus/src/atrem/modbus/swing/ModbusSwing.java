package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import atrem.modbus.Domino;

public class ModbusSwing extends JFrame {

	private JDesktopPane desk;
	private List<JInternalFrame> internalFramesList = new ArrayList<JInternalFrame>();
	private Domino domino;

	public static void main(String[] args) {
		Domino domino = new Domino();
		// ModbusSwing modbusSwing = new ModbusSwing(domino);
	}

	public ModbusSwing(Domino domino) {

		this.domino = domino;
		initialize();

	}

	private void initialize() {

		setTitle("ModbusExplorer");
		setBounds(300, 200, 600, 400);
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

	public void initializeNewFrame() {
		JInternalFrame iFrame = new InterFrame("ModbusExplorer"
				+ ((Integer) internalFramesList.size()).toString());
		internalFramesList.add(iFrame);
		desk.add(iFrame);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createConnectionMenu());
		menuBar.add(createSetupMenu());
		return menuBar;
	}

	private JMenu createFileMenu() {

		JMenu menu = new JMenu("File");
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
		menu.add(new AbstractAction("Connect") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					newConnection();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		return menu;

	}
	private JMenu createSetupMenu() {

		JMenu menu = new JMenu("Setup");
		menu.add(new AbstractAction("Read/Write Definition...") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setupDefinition();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		return menu;
	}

	private void newConnection() {
		ConnectionSetupDialog connectionSetupDialog = new ConnectionSetupDialog(
				domino);
		connectionSetupDialog
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		connectionSetupDialog.setVisible(true);
	}

	private void setupDefinition() {
		try {
			ReadWriteDefinitionDialog readWriteDefinitionDialog = new ReadWriteDefinitionDialog(
					domino);
			readWriteDefinitionDialog
					.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

}