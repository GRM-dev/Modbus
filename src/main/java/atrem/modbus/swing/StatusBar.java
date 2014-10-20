package atrem.modbus.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel {
	private JLabel statusLabel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JProgressBar progressBar;
	private JLabel connectionStatus;

	/**
	 * @param contentPane
	 */
	public StatusBar() {
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(10, 25));
		setBackground(SystemColor.control);

		leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		FlowLayout fl_leftPanel = new FlowLayout(FlowLayout.LEFT, 0, 0);
		leftPanel.setLayout(fl_leftPanel);

		statusLabel = new JLabel("Request Status:");
		statusLabel.setFont(new Font(ModbusSwing.FONT, Font.PLAIN,
				ModbusSwing.FONT_SIZE));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftPanel.add(statusLabel);

		setConnectionStatus(new JLabel(" DISCONNECTED "));
		getConnectionStatus().setFont(
				new Font(ModbusSwing.FONT, Font.PLAIN, ModbusSwing.FONT_SIZE));
		leftPanel.add(getConnectionStatus());

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("Request Progress");
		progressBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		progressBar.setIndeterminate(true);
		leftPanel.add(progressBar);

		rightPanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(new AngledLinesWindowsCornerIcon());
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rightPanel.add(label, BorderLayout.SOUTH);
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);

	}

	public JProgressBar getProgressBar() {
		return this.progressBar;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int y = 0;
		g.setColor(new Color(156, 154, 140));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(196, 194, 183));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(218, 215, 201));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 217));
		g.drawLine(0, y, getWidth(), y);

		y = getHeight() - 3;
		g.setColor(new Color(233, 232, 218));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 216));
		g.drawLine(0, y, getWidth(), y);
		y = getHeight() - 1;
		g.setColor(new Color(221, 221, 220));
		g.drawLine(0, y, getWidth(), y);
	}

	public JLabel getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(JLabel connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
}
