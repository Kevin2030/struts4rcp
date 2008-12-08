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
import com.googlecode.struts4rcp.client.Transmission;
import com.googlecode.struts4rcp.client.event.ExecutionAdapter;
import com.googlecode.struts4rcp.client.event.ExecutionEvent;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.client.event.TransmissionAdapter;
import com.googlecode.struts4rcp.client.event.TransmissionEvent;
import com.googlecode.struts4rcp.client.event.TransmissionListener;
import com.googlecode.struts4rcp.util.ThreadUtils;

/**
 * Action队列信息管理窗口，包装Action队列信息管理面板
 * @see com.googlecode.struts4rcp.client.view.swing.TransmissionPane
 * @see com.googlecode.struts4rcp.client.view.swing.ConnectionStatus
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class TransmissionPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private Icon enableIcon = Images.getIcon("enable.gif");

	private Icon disableIcon = Images.getIcon("disable.gif");

	private final DefaultListModel transportationModel;

	private final JList transportationList;

	public JList getTransportationList() {
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

	public TransmissionPane(final Client client) {
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
		transportationList.setCellRenderer(new TransportListCellRenderer());
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
				if (getTransportationModelSize() == 0) {
					JOptionPane.showMessageDialog(TransmissionPane.this, "没有任何传输项!", "中止", JOptionPane.WARNING_MESSAGE);
					return;
				}
				final Transmission execution = (Transmission)transportationList.getSelectedValue();
				if (execution == null) {
					JOptionPane.showMessageDialog(TransmissionPane.this, "请选择传输项!", "中止", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (! execution.isAbortable()) {
					JOptionPane.showMessageDialog(TransmissionPane.this, "此传输项不允许中止!", "中止", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ThreadUtils.execute(new Runnable(){
					public void run() {
						try {
							execution.abort();
							JOptionPane.showMessageDialog(TransmissionPane.this, "中止传输项完成!", "中止", JOptionPane.INFORMATION_MESSAGE);
						} catch (Throwable t) {
							JOptionPane.showMessageDialog(TransmissionPane.this, "中止传输项失败! 原因: " + t.getMessage(), "中止", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
		});

		transportationList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				final Transmission execution = (Transmission)transportationList.getSelectedValue();
				if (execution != null) {
					timeLabel.setText(new DecimalFormat("###,##0").format(System.currentTimeMillis() - execution.getTransportingTime().getTime()) + " ms");
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
					refreshTransportationList();
					JOptionPane.showMessageDialog(TransmissionPane.this, "刷新传输列表成功!", "刷新", JOptionPane.INFORMATION_MESSAGE);
				} catch (Throwable t) {
					JOptionPane.showMessageDialog(TransmissionPane.this, "刷新传输列表失败! 原因: " + t.getMessage(), "刷新", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		executionListener = new ExecutionDelegate(new ExecutionAdapter() {
			public void onExecuting(ExecutionEvent event) {
				Transmission execution = event.getExecution();
				if (! execution.isTransported()) {
					synchronized (transportationModel) {
						if (! transportationModel.contains(execution))
							transportationModel.addElement(execution);
					}
				}
			}
			public void onBackExecuting(ExecutionEvent event) {
				onExecuting(event);
			}
			public void onExecuted(ExecutionEvent event) {
				Transmission execution = event.getExecution();
				synchronized (transportationModel) {
					transportationModel.removeElement(execution);
				}
			}
		});
		transportationListener = new TransmissionDelegate(new TransmissionAdapter() {
			public void onTransmiting(final TransmissionEvent event) {
				synchronized (transportationModel) {
					transportationList.repaint();
				}
			}
		});
		client.addListener(executionListener);
		client.addListener(transportationListener);
	}

	private void refreshTransportationList() {
		Collection<Transmission> executions = client.getTransmitter().getTransmissions();
		synchronized (transportationModel) {
			transportationModel.clear();
			for (Transmission execution : executions) {
				if (! execution.isTransported()) {
					transportationModel.addElement(execution);
				}
			}
		}
	}

	private final ExecutionListener executionListener;

	private final TransmissionListener transportationListener;

	public void dispose() {
		client.removeListener(executionListener);
		client.removeListener(transportationListener);
	}

	private int getTransportationModelSize() {
		synchronized (transportationModel) {
			return transportationModel.getSize();
		}
	}

	private class TransportListCellRenderer extends DefaultListCellRenderer  {

		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Transmission execution = (Transmission)value;
			if (execution.isTransporting())
				this.setIcon(enableIcon);
			else
				this.setIcon(disableIcon);
			this.setText("" + (index + 1) + ". " + execution.toString());
			return this;
		}

	}

}
