package com.googlecode.struts4rcp.client.view.jface;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class ControlDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final static String DEFAULT_TITLE = "Transport Control";

	private final TransportationPane transportControlPane = new TransportationPane();

	public TransportationPane getTransportControlPane() {
		return transportControlPane;
	}

	public ControlDialog() {
		super();
		init();
	}

	public ControlDialog(Dialog owner) throws HeadlessException {
		super(owner, true);
		init();
	}

	public ControlDialog(Frame owner) throws HeadlessException {
		super(owner, true);
		init();
	}

	private void init() {
		this.setTitle(DEFAULT_TITLE);
		this.setSize(500, 500);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = this.getSize();
		this.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, transportControlPane);
	}

}
