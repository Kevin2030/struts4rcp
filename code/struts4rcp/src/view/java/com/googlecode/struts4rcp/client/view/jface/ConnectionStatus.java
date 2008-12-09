package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ConnectionAdapter;
import com.googlecode.struts4rcp.client.event.ConnectionEvent;
import com.googlecode.struts4rcp.client.event.ConnectionListener;
import com.googlecode.struts4rcp.client.event.TransmissionAdapter;
import com.googlecode.struts4rcp.client.event.TransmissionEvent;
import com.googlecode.struts4rcp.client.event.TransmissionListener;

/**
 * 传输状态项，可将此状态项添加到工具栏，状态栏上。
 * <br>使用如：
 * <pre>
 * public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
 *     protected void fillStatusLine(IStatusLineManager statusLine) {
 *         super.fillStatusLine(statusLine);
 *         statusLine.add(new ConnectionItem(Actions.getClient()));
 *     }
 * }
 * </pre>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConnectionStatus extends ContributionItem {

	private final Client client;

	public ConnectionStatus(Client client) {
		super(ConnectionStatus.class.getName());
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
		// 初始化图片
		this.connectionImage = Images.getImage("connected.gif");
		this.disconnectionImage = Images.getImage("disconnected.gif");
		this.transportationImage = Images.getImage("transporting.gif");
	}

	private Cursor handCursor = new Cursor(null, SWT.CURSOR_HAND);

	private Label connectionButton;
	
	private WorkDialog executionDialog;
	
	private ExceptionDialog exceptionDialog;
	
	private ControlDialog controlDialog;

	private final Image connectionImage;

	private final Image disconnectionImage;

	private final Image transportationImage;
	
	private ConnectionListener connectionListener;
	
	private TransmissionListener transportationListener;

	@Override
	public void fill(Composite parent) {
		try {
			executionDialog = new WorkDialog(parent.getShell(), client);
			exceptionDialog = new ExceptionDialog(parent.getShell(), client);
			controlDialog = new ControlDialog(parent.getShell(), client);
			connectionButton = new Label(parent, SWT.NONE);
			connectionButton.setCursor(handCursor);
			// 按钮点击事件
			connectionButton.addMouseListener(new MouseListener(){
				public void mouseDoubleClick(MouseEvent event) {}
				public void mouseDown(MouseEvent event) {}
				public void mouseUp(MouseEvent event) {
					controlDialog.setVisible(true); 
				}
			});
			// 初始化状态
			refreshStatus();
			// 连接状态事件
			connectionListener = new ConnectionAdapter() {
				public void onConnected(ConnectionEvent event) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							setConnected(true);
						}
					});
				}
				public void onDisconnected(ConnectionEvent event) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							setConnected(false);
						}
					});
				}
			};
			client.addListener(connectionListener);
			transportationListener = new TransmissionAdapter() {
				public void onTransmiting(final TransmissionEvent event) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							setTransporting(client.getTransmitter().isTransmiting());
						}
					});
				}
				public void onTransmited(TransmissionEvent event) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							setTransporting(client.getTransmitter().isTransmiting());
						}
					});
				}
			};
			client.addListener(transportationListener);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		this.setVisible(true);
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
					connectionButton.setImage(transportationImage);
					connectionButton.setToolTipText("Transporting");
				} else {
					connectionButton.setImage(connectionImage);
					connectionButton.setToolTipText("Online");
				}
			} else {
				connectionButton.setImage(disconnectionImage);
				connectionButton.setToolTipText("Offline");

			}
	}

	@Override
	public void dispose() {
		client.removeListener(connectionListener);
		client.removeListener(transportationListener);
		if (connectionButton != null 
				&& ! connectionButton.isDisposed())
			connectionButton.dispose();
		if (handCursor != null 
				&& ! handCursor.isDisposed())
			handCursor.dispose();
		if (connectionImage != null 
				&& ! connectionImage.isDisposed())
			connectionImage.dispose();
		if (disconnectionImage != null 
				&& ! disconnectionImage.isDisposed())
			disconnectionImage.dispose();
		if (transportationImage != null 
				&& ! transportationImage.isDisposed())
			transportationImage.dispose();
		if (executionDialog != null)
			executionDialog.dispose();
		if (exceptionDialog != null)
			exceptionDialog.dispose();
		if (controlDialog != null)
			controlDialog.dispose();
		super.dispose();
	}

}
