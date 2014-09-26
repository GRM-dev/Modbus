package atrem.modbus.swing;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LogOptionWindow extends JDialog {
	private static final File DEFAULT_PATH = new File(
			System.getProperty("user.home") + "/Desktop/log.txt");
	static private File logFile = DEFAULT_PATH;
	private JTextPane logPathTextPane;

	public LogOptionWindow(Window parentWindow) {
		super(parentWindow);
		setModal(true);
		// setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(300, 300, 405, 157);
		;

		JPanel filters = new JPanel();
		FlowLayout flowLayout = (FlowLayout) filters.getLayout();
		filters.setBounds(99, 11, 200, 38);
		getContentPane().add(filters);

		JCheckBox chckbxTime = new JCheckBox("Time");
		chckbxTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO zachownaie po Time
			}
		});
		filters.add(chckbxTime);

		JCheckBox chckbxValue = new JCheckBox("Value");
		chckbxValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO zachownaie po Value
			}
		});
		filters.add(chckbxValue);

		JCheckBox chckbxRegister = new JCheckBox("Register");
		chckbxRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO zachownaie po Register
			}
		});
		filters.add(chckbxRegister);
		JPanel pathOptions = new JPanel();
		pathOptions.setBounds(0, 21, 390, 104);
		getContentPane().add(pathOptions);
		pathOptions.setLayout(null);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.showOpenDialog(null);

				setLogFile(fileChooser.getSelectedFile());
				logPathTextPane.setText("" + logFile);
				dispose();

			}
		});
		btnBrowse.setBounds(314, 73, 67, 23);
		pathOptions.add(btnBrowse);

		logPathTextPane = new JTextPane();
		logPathTextPane.setEditable(false);
		logPathTextPane.setBounds(10, 39, 371, 23);
		logPathTextPane.setText("" + logFile);
		pathOptions.add(logPathTextPane);

		JLabel lblPathlabel = new JLabel("Log path:");
		lblPathlabel.setBounds(10, 11, 200, 23);
		pathOptions.add(lblPathlabel);
	}

	public File getLogFile() {
		return logFile;
	}

	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}

}
