package com.googlecode.struts4rcp.client.view.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ConnectionAdapter;
import com.googlecode.struts4rcp.client.event.ConnectionEvent;

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
 * @see com.googlecode.struts4rcp.client.view.swing.TransportationPane
 * @see com.googlecode.struts4rcp.client.view.swing.TransportationPane
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConnectionButton extends JButton {

	private static final long serialVersionUID = 1L;

	private final Icon connectedIcon;

	private final Icon disconnectedIcon;

	private final Icon transportingIcon;

	private final ControlDialog controlDialog;

	public ControlDialog getControlDialog() {
		return controlDialog;
	}

	private final ExecutionDialog executionDialog;

	public ExecutionDialog getExecutionDialog() {
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
				ConnectionButton.this.setIcon(ConnectionButton.this.transportingIcon);
				ConnectionButton.this.setToolTipText("Transporting");
			} else {
				ConnectionButton.this.setIcon(ConnectionButton.this.connectedIcon);
				ConnectionButton.this.setToolTipText("Online");
			}
		} else {
			ConnectionButton.this.setIcon(ConnectionButton.this.disconnectedIcon);
			ConnectionButton.this.setToolTipText("Offline");

		}
	}

	public ConnectionButton(Frame frame, Client client) {
		// 初始化图片
		this.connectedIcon = Images.getIcon("connected.gif");
		this.disconnectedIcon = Images.getIcon("disconnected.gif");
		this.transportingIcon = Images.getIcon("transporting.gif");
		// 初始化窗口
		controlDialog = new ControlDialog(frame, client);
		executionDialog = new ExecutionDialog(frame, client);
		exceptionDialog = new ExceptionDialog(frame, client);
		// 初始化状态
		refreshStatus();
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
		client.addListener(new ConnectionDelegate(new ConnectionAdapter() {
			public void onConnected(ConnectionEvent event) {
				setConnected(true);
			}
			public void onDisconnected(ConnectionEvent event) {
				setConnected(false);
			}
		}));
		final ListModel listModel = controlDialog.getTransportationPane().getTransportationList().getModel();
		listModel.addListDataListener(new ListDataListener(){
			public void contentsChanged(ListDataEvent e) {}
			public void intervalAdded(ListDataEvent e) {
				setTransporting(listModel.getSize() > 0);
			}
			public void intervalRemoved(ListDataEvent e) {
				setTransporting(listModel.getSize() > 0);
			}
		});
	}

}
