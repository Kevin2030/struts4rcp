package com.googlecode.struts4rcp.client.view.jface;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Transmission;
import com.googlecode.struts4rcp.client.event.TransmissionAdapter;
import com.googlecode.struts4rcp.client.event.TransmissionEvent;
import com.googlecode.struts4rcp.client.event.TransmissionListener;

public class TransmissionPane extends Composite {

	private final Client client;

	private final ArrayList<Transmission> executions = new ArrayList<Transmission>();

	private final List executionList;

	private final TransmissionListener transportationListener;

	public TransmissionPane(final Composite parent, final Client client) {
		super(parent, SWT.NONE);
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
		ToolBar toolBar = new ToolBar(this, SWT.NONE);
		toolBar.setSize(400, 40);
		final ToolItem abortItem = new ToolItem(toolBar, SWT.NONE);
		abortItem.setImage(Images.getImage("abort.gif"));
		abortItem.setText("中止");
		abortItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (getTransportationModelSize() == 0) {
					MessageDialog.openWarning(parent.getShell(), "中止",
							"没有任何传输项!");
					return;
				}
				final Transmission execution = getSelectedExecution();
				if (execution == null) {
					MessageDialog.openWarning(parent.getShell(), "中止",
							"请选择传输项!");
					return;
				}
				if (!execution.isAbortable()) {
					MessageDialog.openWarning(parent.getShell(), "中止",
							"此传输项不允许中止!");
					return;
				}
				try {
					execution.abort();
					MessageDialog.openInformation(parent.getShell(), "中止",
							"中止传输项成功!");
				} catch (Throwable t) {
					MessageDialog.openWarning(parent.getShell(), "中止",
							"中止传输项失败! 原因: " + t.getMessage());
				}
			}
		});
		ToolItem refreshItem = new ToolItem(toolBar, SWT.NONE);
		refreshItem.setImage(Images.getImage("refresh.gif"));
		refreshItem.setText("刷新");
		refreshItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refreshTransportationList(true);
			}
		});
		executionList = new List(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		executionList.setBounds(0, 40, 484, 384);
		Label statusLabel = new Label(this, SWT.NONE);
		statusLabel.setImage(Images.getImage("time.gif"));
		statusLabel.setBounds(0, 424, 20, 20);
		final Label timeLabel = new Label(this, SWT.NONE);
		timeLabel.setBounds(20, 428, 200, 20);
		executionList.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				final Transmission execution = getSelectedExecution();
				if (execution != null) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							abortItem.setEnabled(execution.isAbortable());
							timeLabel.setText(new DecimalFormat("###,##0").format(System.currentTimeMillis() - execution.getTransmitingTime().getTime()) + " ms");
						}
					});
				} else {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							abortItem.setEnabled(false);
							timeLabel.setText("");
						}
					});
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		transportationListener = new TransmissionAdapter() {
			@Override
			public void onTransmit(TransmissionEvent event) {
				final Transmission execution = event.getTransmission();
				if (!execution.isTransmited()) {
					synchronized (executions) {
						if (! executions.contains(execution)) {
							executions.add(execution);
							final int i = executions.size();
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									executionList.add("(挂起) "
											+ i + ". "
											+ execution.toString());
								}
							});
						}
					}
				}
			}

			@Override
			public void onTransmited(TransmissionEvent event) {
				Transmission execution = event.getTransmission();
				synchronized (executions) {
					final int i = executions.indexOf(execution);
					if (i > -1 && i < executions.size()) {
						executions.remove(i);
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								executionList.remove(i);
							}
						});
					}
				}
			}

			@Override
			public void onTransmiting(final TransmissionEvent event) {
				final Transmission execution = event.getTransmission();
				synchronized (executions) {
					if (executions.contains(execution)) {
						final int i = executions.indexOf(execution);
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								executionList.setItem(i, "(传输) " + (i + 1) + ". "
										+ execution.toString());
							}
						});
					}
				}
			}
		};
		client.addListener(transportationListener);
		refreshTransportationList(false);
	}

	private void refreshTransportationList(boolean isUI) {
		Collection<Transmission> exes = client.getTransmitter().getTransmissions();
		synchronized (executions) {
			executions.clear();
			executionList.removeAll();
			for (final Transmission execution : exes) {
				if (! execution.isTransmited()) {
					executions.add(execution);
					final int i = executions.size();
					if (isUI) {
						if (execution.isTransmiting())
							executionList.add("(传输) " + i + ". "
									+ execution.toString());
						else
							executionList.add("(挂起) " + i + ". "
									+ execution.toString());
					} else {
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (execution.isTransmiting())
									executionList.add("(传输) " + i + ". "
											+ execution.toString());
								else
									executionList.add("(挂起) " + i + ". "
											+ execution.toString());
							}
						});
					}
				}
			}
		}
	}

	private Transmission getSelectedExecution() {
		final int i = executionList.getSelectionIndex();
		synchronized (executions) {
			if (i > -1 && i < executions.size()) {
				return executions.get(i);
			}
		}
		return null;
	}

	private int getTransportationModelSize() {
		synchronized (executions) {
			return executions.size();
		}
	}

	@Override
	public void dispose() {
		client.removeListener(transportationListener);
		super.dispose();
	}

}