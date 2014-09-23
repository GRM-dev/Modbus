package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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

public class ModbusSwing extends JFrame {
	private final JPanel contentPanel;
	String[] columnNames = {"No.", "Registry Number", "Registry Value"};
	int rows = 100;
	int columns = 5;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JDesktopPane desk;
	// static Map<String, InterFrame> framesList = new HashMap<String,
	// InterFrame>();
	private List<JInternalFrame> internalFramesList = new ArrayList<JInternalFrame>();
	private Dimension screenSize;

	public static void main(String[] args) {
		ModbusSwing modbusSwing = new ModbusSwing();
	}
	public ModbusSwing() {

		this.domino = domino;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		contentPanel = new JPanel();
		setTitle("Domino");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

		fileMenu.add(new AbstractAction("New") {
			@Override
			public void actionPerformed(ActionEvent e) {
				newFrame();
			}
		});

		fileMenu.add(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menuBar.add(fileMenu);
		pack();
	}

	public void newFrame() {

		JInternalFrame iFrame = new InterFrame(createName(), columnNames);
		internalFramesList.add(iFrame);
		desk.add(iFrame);

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
