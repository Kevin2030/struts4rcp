package com.xxx.demo.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.googlecode.struts4rcp.client.Actions;
import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.transporter.AbstractHttpTransporter;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ConfigFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public ConfigFrame(final Properties properties) {
		this.setTitle("RCP Struts Test");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = this.getSize();
		this.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		this.getContentPane().setLayout(new BorderLayout());
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(6, 2, 10, 10));
		mainPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.getContentPane().add(BorderLayout.NORTH, mainPane);
		mainPane.add(new JLabel("请选择配置："));
		mainPane.add(new JLabel());
		final JComboBox transporterComboBox = new JComboBox(new String[]{
				"HttpURLConnectionTransporter",
				"CommonsHttpClientTransporter",
				"HttpClientTransporter"});
		mainPane.add(new JLabel("Transporter"));
		mainPane.add(transporterComboBox);
		final JTextField connectionTimeoutField = new JTextField("30000");
		mainPane.add(new JLabel("ConnectionTimeout(ms)"));
		mainPane.add(connectionTimeoutField);
		final JTextField socketTimeoutField = new JTextField("0");
		mainPane.add(new JLabel("SocketTimeout(ms)"));
		mainPane.add(socketTimeoutField);
		final JTextField connectionCheckIntervalField = new JTextField("5000");
		mainPane.add(new JLabel("ConnectionCheckInterval(ms)"));
		mainPane.add(connectionCheckIntervalField);

		JPanel buttonPanel = new JPanel();
		mainPane.add(new JLabel());
		mainPane.add(buttonPanel);
		JButton resetButton = new JButton("重置");
		buttonPanel.add(resetButton);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				transporterComboBox.setSelectedIndex(0);
				connectionTimeoutField.setText("30000");
				socketTimeoutField.setText("0");
				connectionCheckIntervalField.setText("5000");
			}
		});
		JButton okButton = new JButton("确定");
		buttonPanel.add(okButton);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				properties.setProperty(Client.TRANSPORTER_PARAM_NAME, "com.googlecode.struts4rcp.client.transporter." + (String)transporterComboBox.getSelectedItem());
				properties.setProperty(AbstractHttpTransporter.CONNECTION_TIMEOUT_PARAM_NAME, connectionTimeoutField.getText().trim());
				properties.setProperty(AbstractHttpTransporter.SOCKET_TIMEOUT_PARAM_NAME, socketTimeoutField.getText().trim());
				properties.setProperty(AbstractHttpTransporter.CONNECTION_CHECK_INTERVAL_PARAM_NAME, connectionCheckIntervalField.getText().trim());
				try {
					Actions.init(properties);
					new LoginFrame();
					ConfigFrame.this.dispose();
				} catch (Throwable t) {
					JOptionPane.showMessageDialog(ConfigFrame.this, "初始化失败! 原因: \n" + ExceptionUtils.getDetailMessage(t), "中止", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.setVisible(true);
	}

}
