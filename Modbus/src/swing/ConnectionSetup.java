package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class ConnectionSetup extends JDialog {

	private final Box contentBox = Box.createHorizontalBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectionSetup dialog = new ConnectionSetup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConnectionSetup() {

		setTitle("Connection Setup");

		setBounds(300, 300, 350, 220);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentBox, BorderLayout.CENTER);

		contentBox.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentBox.add(createQuestionBox());

		getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);

		pack();
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(createOkButton());
		buttonPanel.add(createCancelButton());
		return buttonPanel;

	}

	private Box createQuestionBox() {

		Box box = Box.createVerticalBox();
		box.add(createDialogContainer("IP Address "));
		box.add(createDialogContainer("Server Port "));
		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		return box;
	}

	private JPanel createDialogContainer(String labelName) {
		JLabel label = new JLabel(labelName);
		JTextArea textArea = new JTextArea();
		textArea.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(label);
		panel.add(textArea);
		return panel;
	}

	private JButton createOkButton() {
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		getRootPane().setDefaultButton(okButton);
		return okButton;
	}

	private JButton createCancelButton() {
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		return cancelButton;
	}

}
