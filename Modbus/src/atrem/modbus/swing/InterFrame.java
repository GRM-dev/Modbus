package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import atrem.modbus.Controller;
import atrem.modbus.Domino;
import atrem.modbus.InterFrameService;
import atrem.modbus.Request;
import atrem.modbus.RequestHandler;
import atrem.modbus.RequestService;
import atrem.modbus.frames.ResponseFrame;

public class InterFrame extends JInternalFrame implements InterFrameService {

	private TableDemo tableDemo;
	public Domino domino;
	private boolean pauseButton = true;
	private JButton btnStart;
	private JButton btnPause;
	private JComboBox<String> byteOrderComboBox;
	private static final String[] BYTEORDER = { "long ABCD", "long CDAB",
			"long BADC", "long DCBA", "float ABCD", "float CDAB", "float BADC",
			"float DCBA" };
	RequestService requestService;

	public InterFrame(String title, Domino domino) {
		this.domino = domino;
		initialize();
		tableDemo = new TableDemo();
		getContentPane().add(tableDemo);
		getContentPane().add(createPanel(), BorderLayout.SOUTH);
		pack();

	}

	private JPanel createPanel() {

		JPanel panel = new JPanel();
		btnStart = createStartButton();
		panel.add(btnStart);
		btnPause = createPauseButton();
		panel.add(btnPause);
		byteOrderComboBox = new JComboBox(BYTEORDER);
		panel.add(byteOrderComboBox);

		return panel;
	}

	private void initialize() {
		setResizable(true);
		setMinimumSize(new Dimension(100, 60));
		setClosable(true);
		setIconifiable(true);
		setTitle(title);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());
		setMaximizable(true);
		setVisible(true);
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JButton createStartButton() {
		final JButton button = new JButton("Start");
		button.setEnabled(!pauseButton);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requestService.startRequest();
				pauseButton = !pauseButton;
				btnPause.setEnabled(pauseButton);
				button.setEnabled(!pauseButton);
			}
		});
		return button;
	}

	private JButton createPauseButton() {
		final JButton button = new JButton("Pause");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requestService.pauseRequest();
				pauseButton = !pauseButton;
				button.setEnabled(pauseButton);
				btnStart.setEnabled(!pauseButton);
			}
		});
		return button;
	}

	public void initializeNewRequest(Request request) {
		Controller controller = domino.getController();
		requestService = new RequestHandler(request, controller, this);
		requestService.startRequest();

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

	public boolean getPauseButtonState() {
		return pauseButton;
	}

	@Override
	public void addResponseFrame(ResponseFrame responseFrame) {
		addDataToTable(responseFrame);

	}

}
