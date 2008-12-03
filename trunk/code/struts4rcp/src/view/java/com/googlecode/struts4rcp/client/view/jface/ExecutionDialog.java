package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Execution;
import com.googlecode.struts4rcp.client.event.ExecutionAdapter;
import com.googlecode.struts4rcp.client.event.ExecutionEvent;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.util.ThreadUtils;

public class ExecutionDialog extends Dialog {
	
	private final Client client;
	
	private final Shell shell;

	private Label executionLabel;
	
	private Button backButton;
	
	private Button abortButton;

	private Execution execution;

	private final ExecutionListener executionListener;

	protected ExecutionDialog(final Shell parent, final Client client) {
		super(parent);
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(450, 180);
		shell.setText("传输");
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) { 
				e.doit = false; 
			} 
		});
		shell.setVisible(false);
		
		Label imageLabel = new Label(shell, SWT.NULL);
		Image image = getImage();
		Rectangle imageRectangle = image.getBounds();
		image.setBackground(imageLabel.getBackground());
		imageLabel.setImage(image);
		imageLabel.setBounds(16, 16, imageRectangle.width, imageRectangle.height);
		
		Label messageLabel = new Label(shell, SWT.WRAP);
		messageLabel.setText("正在传输，请稍候...");
		messageLabel.setBounds(64, 24, 300, 16);
		
		executionLabel = new Label(shell, SWT.WRAP);
		executionLabel.setText("[NO.1][2008-10-10 12:11:23.214]loginAction");
		executionLabel.setBounds(16, 60, 412, 16);
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.HORIZONTAL|SWT.INDETERMINATE);
		progressBar.setBounds(16, 80, 412, 16);
		
		backButton = new Button(shell, SWT.PUSH);
		backButton.setText("后台运行");
		backButton.setBounds(264, 110, 80, 24);
		backButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				final Execution execution = ExecutionDialog.this.execution;
				if (execution != null && execution.isBackable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								execution.back();
							} catch (final Throwable t) {
								shell.getDisplay().asyncExec(new Runnable() {
									public void run() {
										MessageDialog.openError(shell, "后台运行", "后台运行传输项失败! 原因: " + t.getMessage());
									}
								});
							}
						}
					});
				}
			}
		});
		
		abortButton = new Button(shell, SWT.PUSH);
		abortButton.setText("中止运行");
		abortButton.setBounds(348, 110, 80, 24);
		abortButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				final Execution execution = ExecutionDialog.this.execution;
				if (execution != null && execution.isAbortable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								execution.abort();
							} catch (final Throwable t) {
								shell.getDisplay().asyncExec(new Runnable() {
									public void run() {
										MessageDialog.openError(shell, "中止", "中止传输项失败! 原因: " + t.getMessage());
									}
								});
							}
						}
					});
				}
			}
		});
		
		executionListener = new ExecutionAdapter() { // 只在非UI线程执行
			public void onExecuting(ExecutionEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					showExecution(event.getExecution());
				}
			}
			public void onBackExecuting(ExecutionEvent event) {
				onExecuted(event);
			}
			public void onExecuted(ExecutionEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					try {
						if (client.getActionFactory().isForeExecuting()) {
							Execution execution = client.getActionFactory().getForeExecutions().iterator().next();
							showExecution(execution);
							return;
						}
					} catch (Throwable t) {
						// ignore
					}
					showExecution(null);
				}
			}
		};
		client.addListener(executionListener);
	}
	
	public void dispose() {
		client.removeListener(executionListener);
		if (shell != null && ! shell.isDisposed())
			shell.dispose();
	}

	private Image getImage() {
		final Image[] image = new Image[1];
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
				image[0] = shell.getDisplay().getSystemImage(SWT.ICON_INFORMATION);
			}
		});
		return image[0];
	}

	private void showExecution(final Execution execution) {
		if (this.execution != execution) {
			this.execution = execution;
			shell.getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (execution != null) {
						backButton.setEnabled(execution.isBackable());
						abortButton.setEnabled(execution.isAbortable());
						executionLabel.setText(execution.toString());
						if (! shell.isVisible()) {
							Rectangle displayBounds = Display.getDefault().getClientArea();
							Rectangle shellBounds = shell.getBounds();
							int x = (displayBounds.width - shellBounds.width) / 2;
							int y = (displayBounds.height - shellBounds.height) / 2;
							shell.setLocation(x, y);
							shell.setVisible(true);
						}
					} else {
						shell.setVisible(false);
					}
				}
			});
		}
	}

}
