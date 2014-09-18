package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ModbusSwing extends JFrame {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModbusSwing dialog = new ModbusSwing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModbusSwing() {

		setTitle("Modbus");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new AbstractAction("Exit") {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu connectionMenu = new JMenu("Connection");
		connectionMenu.add(new AbstractAction("Connect...") {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ConnectionSetup dialogCS = new ConnectionSetup();
					dialogCS.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialogCS.setVisible(true);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		JMenu setupMenu = new JMenu("Setup");
		setupMenu.add(new AbstractAction("Read/Write Definition...") {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ReadWriteDefinition dialogRWD = new ReadWriteDefinition();
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

	}

}

class DataTablePanel extends JPanel {

	public DataTablePanel() {

	}

}
