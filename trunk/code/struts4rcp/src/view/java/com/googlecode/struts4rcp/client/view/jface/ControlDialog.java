package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.googlecode.struts4rcp.client.Client;

public class ControlDialog extends Dialog {

	private final Shell shell;

	private TransmissionPane transmissionPane;

	private ExceptionPane exceptionPane;

	private ConfigurationPane configurationPane;

	protected ControlDialog(final Shell parent, final Client client) {
		super(parent);
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(500, 500);
		Rectangle displayBounds = Display.getDefault().getClientArea();
		Rectangle shellBounds = shell.getBounds();
		int x = (displayBounds.width - shellBounds.width) / 2;
		int y = (displayBounds.height - shellBounds.height) / 2;
		shell.setLocation(x, y);
		shell.setText("控制台");
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = false;
				shell.setVisible(false);
			}
		});
		shell.setVisible(false);
		shell.setLayout(new FillLayout());
		TabFolder tabFolder = new TabFolder(shell, SWT.FILL);
		tabFolder.setLayout(new FillLayout());

		TabItem transportationTabItem = new TabItem(tabFolder, SWT.FILL);
		transportationTabItem.setText("传输管理");
		transmissionPane = new TransmissionPane(tabFolder, client);
		transportationTabItem.setControl(transmissionPane);

		TabItem exceptionTabItem = new TabItem(tabFolder, SWT.FILL);
		exceptionTabItem.setText("异常管理");
		exceptionPane = new ExceptionPane(tabFolder);
		exceptionTabItem.setControl(exceptionPane);

		TabItem configurationTabItem = new TabItem(tabFolder, SWT.FILL);
		configurationTabItem.setText("配置管理");
		configurationPane = new ConfigurationPane(tabFolder, client);
		configurationTabItem.setControl(configurationPane);

	}

	public void setVisible(final boolean visible) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				shell.setVisible(visible);
			}
		});
	}

	public void dispose() {
		if (transmissionPane != null && ! transmissionPane.isDisposed())
			transmissionPane.dispose();
		if (exceptionPane != null && ! exceptionPane.isDisposed())
			exceptionPane.dispose();
		if (configurationPane != null && ! configurationPane.isDisposed())
			configurationPane.dispose();
		if (shell != null && ! shell.isDisposed())
			shell.dispose();
	}

}
