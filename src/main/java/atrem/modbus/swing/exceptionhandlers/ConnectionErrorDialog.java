package atrem.modbus.swing.exceptionhandlers;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConnectionErrorDialog {
	static JDialog			dialog			= new JDialog();
	static JPanel			contentPanel	= new JPanel();
	static JButton			cancelButton;
	private static JLabel	lblNewLabel;
	private static JPanel	buttonPane;
	private static JButton	okButton;
	
	/**
	 * Shows Connection Error Dialog
	 * 
	 * @param e1
	 * @param connectionSetupDialog
	 * @wbp.parser.entryPoint
	 */
	public static void show(IOException e1) {
		
		dialog.setBounds(100, 100, 450, 161);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel(e1.getMessage());
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblNewLabel);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Odnów Po³¹czenie");
				okButton.setMnemonic('o');
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				buttonPane.add(okButton);
				dialog.getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Przerwij");
				cancelButton.setMnemonic('p');
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
}
