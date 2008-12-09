package com.googlecode.struts4rcp.client.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import com.googlecode.struts4rcp.client.Client;

public class ControlDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final TransmissionPane transmissionPane;

	private final ExceptionPane exceptionPane;

	private final ConfigurationPane configurationPane;

	public ControlDialog(Frame owner, Client client) {
		super(owner, true);
		if (client == null)
			throw new NullPointerException("Client == null!");
		transmissionPane = new TransmissionPane(client);
		exceptionPane = new ExceptionPane();
		configurationPane = new ConfigurationPane(client);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("控制台");
		this.setSize(600, 500);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = this.getSize();
		this.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		this.setLayout(new BorderLayout());
		JTabbedPane tabPane = new JTabbedPane();
		this.add(BorderLayout.CENTER, tabPane);
		tabPane.addTab("传输管理", transmissionPane);
		tabPane.addTab("异常管理", exceptionPane);
		tabPane.addTab("配置管理", configurationPane);
		this.setVisible(false);
	}

	public TransmissionPane getTransmissionPane() {
		return transmissionPane;
	}

	public ExceptionPane getExceptionPane() {
		return exceptionPane;
	}

	public ConfigurationPane getConfigurationPane() {
		return configurationPane;
	}

	@Override
	public void dispose() {
		transmissionPane.dispose();
		exceptionPane.dispose();
		configurationPane.dispose();
		super.dispose();
	}

}
