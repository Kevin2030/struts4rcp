package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ConnectionAdapter;
import com.googlecode.struts4rcp.client.event.ConnectionEvent;

public class ConnectionButton extends Button {

	private final Image connectionImage;

	public Image getConnectionImage() {
		return connectionImage;
	}

	private final Image disconnectionImage;

	public Image getDisconnectionImage() {
		return disconnectionImage;
	}

	private final Image transportationImage;

	public Image getTransportationImage() {
		return transportationImage;
	}

	private ControlDialog transportControlDialog;

	public ControlDialog getTransportControlDialog() {
		return transportControlDialog;
	}

	public ConnectionButton(Composite parent, Client client) {
		super(parent, SWT.NONE);
		// 初始化图片
		this.connectionImage = Images.getImage("connection.gif");
		this.disconnectionImage = Images.getImage("disconnection.gif");
		this.transportationImage = Images.getImage("transportation.gif");
		// 初始化状态
		refreshStatus();
		// 按钮点击事件
		this.addMouseListener(new MouseListener(){
			public void mouseDoubleClick(MouseEvent event) {}
			public void mouseDown(MouseEvent event) {}
			public void mouseUp(MouseEvent event) {
				transportControlDialog.setVisible(true);
			}
		});
		// 连接状态事件
		client.addListener(new ConnectionAdapter() {
			public void onConnected(ConnectionEvent event) {
				setConnected(true);
			}
			public void onDisconnected(ConnectionEvent event) {
				setConnected(false);
			}
		});
	}

	private boolean connected;

	private boolean transporting;

	protected void setConnected(boolean connected) {
		setStatus(connected, this.transporting);
	}

	protected void setTransporting(boolean transporting) {
		setStatus(this.connected, transporting);
	}

	private void setStatus(boolean connected, boolean transporting) {
		if (this.connected != connected || this.transporting != transporting) {
			this.connected = connected;
			this.transporting = transporting;
			refreshStatus();
		}
	}

	protected void refreshStatus() {
		if (connected) {
			if (transporting) {
				ConnectionButton.this.setImage(ConnectionButton.this.transportationImage);
				ConnectionButton.this.setToolTipText("Transporting");
			} else {
				ConnectionButton.this.setImage(ConnectionButton.this.connectionImage);
				ConnectionButton.this.setToolTipText("Online");
			}
		} else {
			ConnectionButton.this.setImage(ConnectionButton.this.disconnectionImage);
			ConnectionButton.this.setToolTipText("Offline");

		}
	}

	@Override
	public void dispose() {
		super.dispose();
		connectionImage.dispose();
		disconnectionImage.dispose();
		transportationImage.dispose();
	}

}
