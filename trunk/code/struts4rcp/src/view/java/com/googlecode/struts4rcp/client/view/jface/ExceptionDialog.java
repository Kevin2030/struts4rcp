package com.googlecode.struts4rcp.client.view.jface;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ExceptionAdapter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ExceptionDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	private final ExceptionListener exceptionListener;

	private final Client client;

	private final Shell shell;
	
	private final Label exceptionLabel;
	
	private final Text detailText;

	public ExceptionDialog(final Shell parent, final Client client) {
		super(parent);
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(450, 180);
		shell.setText("异常");
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) { 
				e.doit = false;
				shell.setVisible(false);
			} 
		});
		shell.setVisible(false);

		exceptionLabel = new Label(shell, SWT.WRAP);
		exceptionLabel.setText("非常抱歉，传输出错，请联系管理员！");
		exceptionLabel.setBounds(16, 16, 412, 16);

		detailText = new Text(shell, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);
		detailText.setEditable(false);
		detailText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		detailText.setBounds(16, 32, 412, 80);

		final Button copyButton = new Button(shell, SWT.NONE);
		copyButton.setText("复制");
		copyButton.setBounds(264, 112, 80, 24);
		copyButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				StringSelection stringSelection = new StringSelection(detailText.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				MessageDialog.openInformation(shell, "复制", "已复制异常信息到您的粘贴板上!");
			}
		});

		final Button okButton = new Button(shell, SWT.NONE);
		okButton.setText("关闭");
		okButton.setBounds(348, 112, 80, 24);
		okButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
			}
		});

		exceptionListener = new ExceptionAdapter() { // 只在非UI线程执行
			public void onBackCatched(ExceptionEvent event) {}
			public void onCatched(ExceptionEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					showException(event.getException());
				}
			}
		};
		client.addListener(exceptionListener);
	}
	
	private void showException(final Throwable exception) {
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
				exceptionLabel.setText(exception.getMessage());
				detailText.setText("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "]\n" + ExceptionUtils.getDetailMessage(exception));
				if (! shell.isVisible()) {
					Rectangle displayBounds = Display.getDefault().getClientArea();
					Rectangle shellBounds = shell.getBounds();
					int x = (displayBounds.width - shellBounds.width) / 2;
					int y = (displayBounds.height - shellBounds.height) / 2;
					shell.setLocation(x, y);
					shell.setVisible(true);
				}
			}
		});
	}

	public void dispose() {
		client.removeListener(exceptionListener);
		if (shell != null && ! shell.isDisposed())
			shell.dispose();
	}

}
