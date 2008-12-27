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

import com.googlecode.struts4rcp.client.event.WorkAdapter;
import com.googlecode.struts4rcp.client.event.WorkEvent;
import com.googlecode.struts4rcp.client.event.WorkListener;
import com.googlecode.struts4rcp.client.work.Work;
import com.googlecode.struts4rcp.client.work.Worker;
import com.googlecode.struts4rcp.util.ThreadUtils;

public class WorkDialog extends Dialog {

	private final Shell shell;

	private Label workLabel;

	private Button backButton;

	private Button abortButton;

	private Work work;

	private final WorkListener workListener;

	protected WorkDialog(final Shell parent) {
		super(parent);
		this.shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.PRIMARY_MODAL);
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

		workLabel = new Label(shell, SWT.WRAP);
		workLabel.setText("[NO.1][2008-10-10 12:11:23.214]loginAction");
		workLabel.setBounds(16, 60, 412, 16);

		ProgressBar progressBar = new ProgressBar(shell, SWT.HORIZONTAL|SWT.INDETERMINATE);
		progressBar.setBounds(16, 80, 412, 16);

		backButton = new Button(shell, SWT.PUSH);
		backButton.setText("后台运行");
		backButton.setBounds(264, 110, 80, 24);
		backButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				final Work work = WorkDialog.this.work;
				if (work != null && work.isBackable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								work.back();
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
		abortButton.setText("中止");
		abortButton.setBounds(348, 110, 80, 24);
		abortButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				final Work work = WorkDialog.this.work;
				if (work != null && work.isAbortable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								work.abort();
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

		workListener = new WorkAdapter() { // 只在非UI线程执行
			public void onForeWorking(WorkEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					showWork(event.getWork());
				}
			}
			public void onBackWorking(WorkEvent event) {
				onWorked(event);
			}
			public void onWorked(WorkEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					try {
						if (Worker.getWorker().isForeWorking()) {
							Work work = Worker.getWorker().getForeWorks().iterator().next();
							showWork(work);
							return;
						}
					} catch (Throwable t) {
						// ignore
					}
					showWork(null);
				}
			}
		};
		Worker.getWorker().addListener(workListener);
	}

	public void dispose() {
		Worker.getWorker().removeListener(workListener);
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

	private void showWork(final Work work) {
		if (this.work != work) {
			this.work = work;
			shell.getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (work != null) {
						backButton.setEnabled(work.isBackable());
						abortButton.setEnabled(work.isAbortable());
						workLabel.setText(work.toString());
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
