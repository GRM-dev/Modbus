package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import atrem.modbus.Controller;
import atrem.modbus.Domino;
import atrem.modbus.Request;
import atrem.modbus.RequestHandler;
import atrem.modbus.RequestService;
import atrem.modbus.frames.ResponseFrame;

public class InterFrame extends JInternalFrame {

	private TableDemo tableDemo;
	public Domino domino;
	private boolean pauseButton = true;
	private JButton btnPause = new JButton("Pause");
	private JButton btnStart = new JButton("Start");
	RequestService requestService;

	public InterFrame(String title, Domino domino) {
		this.domino = domino;
		setResizable(true);
		setMinimumSize(new Dimension(100, 60));
		setClosable(true);
		setIconifiable(true);
		setTitle(title);
		setLocation(10, 10);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		// TODO te action listenery wygladaja poprostu zle

		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requestService.pauseRequest();
				pauseButton = !pauseButton;
				btnPause.setEnabled(pauseButton);
				btnStart.setEnabled(!pauseButton);
			}
		});

		panel.add(btnPause);
		btnStart.setEnabled(!pauseButton);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requestService.startRequest();
				pauseButton = !pauseButton;
				btnPause.setEnabled(pauseButton);
				btnStart.setEnabled(!pauseButton);
			}
		});

		panel.add(btnStart);
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
		requestService = new RequestHandler(request, controller, this);
		requestService.startRequest();

	}

	public TableDemo getTableDemo() {
		return tableDemo;
	}

	public void setTableDemo(TableDemo tableDemo) {
		this.tableDemo = tableDemo;
	}

	public void addDataToTable(final ResponseFrame responseFrame) {
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

}
