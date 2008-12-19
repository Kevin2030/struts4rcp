package com.googlecode.struts4rcp.client.view.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.NetworkAdapter;
import com.googlecode.struts4rcp.client.event.NetworkEvent;
import com.googlecode.struts4rcp.client.event.TransferAdapter;
import com.googlecode.struts4rcp.client.event.TransferEvent;

/**
 * 队列信息窗口显示按钮，点击该按钮将弹出Action队列信息管理窗口，可将此按钮添加到工具栏，状态栏上。
 * <br>使用如：
 * <pre>
 * JFrame frame = new JFrame();
 * JToolBar statusBar = new JToolBar();
 * ConnectionButton connectionButton = new ConnectionButton(frame)
 * frame.getContentPane().setLayout(new BorderLayout());
 * frame.getContentPane().add(BorderLayout.SOUTH, statusBar);
 * statusBar.add(connectionButton);
 * </pre>
 * @see com.googlecode.struts4rcp.client.view.swing.TransferPane
 * @see com.googlecode.struts4rcp.client.view.swing.TransferPane
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class NetworkStatus extends JButton {

	private static final long serialVersionUID = 1L;

	private final Icon connectedIcon;

	private final Icon disconnectedIcon;

	private final Icon transportingIcon;

	private final ControlDialog controlDialog;

	public ControlDialog getControlDialog() {
		return controlDialog;
	}

	private final WorkDialog executionDialog;

	public WorkDialog getExecutionDialog() {
		return executionDialog;
	}

	private final ExceptionDialog exceptionDialog;

	public ExceptionDialog getExceptionDialog() {
		return exceptionDialog;
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
				NetworkStatus.this.setIcon(NetworkStatus.this.transportingIcon);
				NetworkStatus.this.setToolTipText("Transporting");
			} else {
				NetworkStatus.this.setIcon(NetworkStatus.this.connectedIcon);
				NetworkStatus.this.setToolTipText("Online");
			}
		} else {
			NetworkStatus.this.setIcon(NetworkStatus.this.disconnectedIcon);
			NetworkStatus.this.setToolTipText("Offline");

		}
	}

	public NetworkStatus(Frame frame, final Client client) {
		if (client == null)
			throw new NullPointerException("Client == null!");
		// 初始化图片
		this.connectedIcon = Images.getIcon("connected.gif");
		this.disconnectedIcon = Images.getIcon("disconnected.gif");
		this.transportingIcon = Images.getIcon("transporting.gif");
		// 初始化窗口
		controlDialog = new ControlDialog(frame, client);
		executionDialog = new WorkDialog(frame);
		exceptionDialog = new ExceptionDialog(frame);
		// 按钮点击事件
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlDialog.setVisible(true);
				} catch (Throwable t) {
					// ignore
				}
			}
		});
		// 连接状态事件
		client.addListener(new NetworkDelegate(new NetworkAdapter() {
			public void onConnected(NetworkEvent event) {
				setConnected(true);
			}
			public void onDisconnected(NetworkEvent event) {
				setConnected(false);
			}
		}));
		client.addListener(new TransferDelegate(new TransferAdapter() {
			public void onTransferring(final TransferEvent event) {
				setTransporting(client.getTransferrer().isTransferring());
			}
			public void onTransferred(TransferEvent event) {
				setTransporting(client.getTransferrer().isTransferring());
			}
		}));
		// 初始化状态
		refreshStatus();
	}

}
