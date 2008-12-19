package com.googlecode.struts4rcp.client.view.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collection;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Transfer;
import com.googlecode.struts4rcp.client.event.TransferAdapter;
import com.googlecode.struts4rcp.client.event.TransferEvent;
import com.googlecode.struts4rcp.client.event.TransferListener;
import com.googlecode.struts4rcp.util.ThreadUtils;

/**
 * Action队列信息管理窗口，包装Action队列信息管理面板
 * @see com.googlecode.struts4rcp.client.view.swing.TransferPane
 * @see com.googlecode.struts4rcp.client.view.swing.ConnectionStatus
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransferPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private Icon enableIcon = Images.getIcon("enable.gif");

	private Icon disableIcon = Images.getIcon("disable.gif");

	private final DefaultListModel transportationModel;

	private final JList transportationList;

	public JList getTransferList() {
		return transportationList;
	}

	private final JToolBar toolBar = new JToolBar();

	public JToolBar getToolBar() {
		return toolBar;
	}

	private final JToolBar statusBar = new JToolBar();

	public JToolBar getStatusBar() {
		return statusBar;
	}

	private final Client client;

	public TransferPane(final Client client) {
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
		this.setLayout(new BorderLayout());

		toolBar.setFloatable(false);
		this.add(BorderLayout.NORTH, toolBar);

		statusBar.setFloatable(false);
		statusBar.setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH, statusBar);
		JPanel descPane = new JPanel();
		descPane.add(new JLabel("传输状态", enableIcon, JLabel.LEFT));
		descPane.add(new JLabel("挂起状态", disableIcon, JLabel.LEFT));
		statusBar.add(BorderLayout.EAST, descPane);

		final JLabel timeLabel = new JLabel();
		timeLabel.setIcon(Images.getIcon("time.gif"));
		statusBar.add(BorderLayout.WEST, timeLabel);

		transportationModel = new DefaultListModel();
		transportationList = new JList(transportationModel);
		transportationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transportationList.setCellRenderer(new TransferListCellRenderer());
		final JScrollPane actionListPane = new JScrollPane();
		actionListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		actionListPane.getViewport().setView(transportationList);
		this.add(BorderLayout.CENTER, actionListPane);

		final JButton abortButton = new JButton("中止", Images.getIcon("abort.gif"));
		abortButton.setToolTipText("中止传输项");
		abortButton.setEnabled(false);
		toolBar.add(abortButton);
		abortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTransferModelSize() == 0) {
					JOptionPane.showMessageDialog(TransferPane.this, "没有任何传输项!", "中止", JOptionPane.WARNING_MESSAGE);
					return;
				}
				final Transfer execution = (Transfer)transportationList.getSelectedValue();
				if (execution == null) {
					JOptionPane.showMessageDialog(TransferPane.this, "请选择传输项!", "中止", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (! execution.isAbortable()) {
					JOptionPane.showMessageDialog(TransferPane.this, "此传输项不允许中止!", "中止", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ThreadUtils.execute(new Runnable(){
					public void run() {
						try {
							execution.abort();
							JOptionPane.showMessageDialog(TransferPane.this, "中止传输项完成!", "中止", JOptionPane.INFORMATION_MESSAGE);
						} catch (Throwable t) {
							JOptionPane.showMessageDialog(TransferPane.this, "中止传输项失败! 原因: " + t.getMessage(), "中止", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
		});

		transportationList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				final Transfer execution = (Transfer)transportationList.getSelectedValue();
				if (execution != null) {
					timeLabel.setText(new DecimalFormat("###,##0").format(System.currentTimeMillis() - execution.getTransferringTime().getTime()) + " ms");
				} else {
					timeLabel.setText("");
				}
				abortButton.setEnabled(execution != null && execution.isAbortable());
			}

		});

		final JButton resetButton = new JButton("刷新", Images.getIcon("refresh.gif"));
		resetButton.setToolTipText("刷新传输列表");
		toolBar.add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refreshTransferList();
					JOptionPane.showMessageDialog(TransferPane.this, "刷新传输列表成功!", "刷新", JOptionPane.INFORMATION_MESSAGE);
				} catch (Throwable t) {
					JOptionPane.showMessageDialog(TransferPane.this, "刷新传输列表失败! 原因: " + t.getMessage(), "刷新", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		transportationListener = new TransferDelegate(new TransferAdapter() {
			@Override
			public void onTransfer(TransferEvent event) {
				Transfer execution = event.getTransfer();
				if (! execution.isTransferred()) {
					synchronized (transportationModel) {
						if (! transportationModel.contains(execution))
							transportationModel.addElement(execution);
					}
				}
			}
			@Override
			public void onTransferred(TransferEvent event) {
				Transfer execution = event.getTransfer();
				synchronized (transportationModel) {
					transportationModel.removeElement(execution);
				}
			}
			@Override
			public void onTransferring(final TransferEvent event) {
				synchronized (transportationModel) {
					transportationList.repaint();
				}
			}
		});
		client.addListener(transportationListener);
	}

	private void refreshTransferList() {
		Collection<Transfer> executions = client.getTransferrer().getTransfers();
		synchronized (transportationModel) {
			transportationModel.clear();
			for (Transfer execution : executions) {
				if (! execution.isTransferred()) {
					transportationModel.addElement(execution);
				}
			}
		}
	}

	private final TransferListener transportationListener;

	public void dispose() {
		client.removeListener(transportationListener);
	}

	private int getTransferModelSize() {
		synchronized (transportationModel) {
			return transportationModel.getSize();
		}
	}

	private class TransferListCellRenderer extends DefaultListCellRenderer  {

		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Transfer execution = (Transfer)value;
			if (execution.isTransferring())
				this.setIcon(enableIcon);
			else
				this.setIcon(disableIcon);
			this.setText("" + (index + 1) + ". " + execution.toString());
			return this;
		}

	}

}
