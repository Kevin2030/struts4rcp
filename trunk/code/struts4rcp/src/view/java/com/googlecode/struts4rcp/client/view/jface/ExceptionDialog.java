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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.googlecode.struts4rcp.client.Worker;
import com.googlecode.struts4rcp.client.event.ExceptionAdapter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ExceptionDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	private final ExceptionListener exceptionListener;

	private final Shell shell;

	private final Text detailText;

	public ExceptionDialog(final Shell parent) {
		super(parent);
		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(450, 200);
		shell.setText("异常");
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = false;
				shell.setVisible(false);
			}
		});
		shell.setVisible(false);

		Label imageLabel = new Label(shell, SWT.NULL);
		Image image = getImage();
		Rectangle imageRectangle = image.getBounds();
		image.setBackground(imageLabel.getBackground());
		imageLabel.setImage(image);
		imageLabel.setBounds(16, 16, imageRectangle.width, imageRectangle.height);

		Label exceptionLabel = new Label(shell, SWT.WRAP);
		exceptionLabel.setText("非常抱歉，传输过程出错或服务器端出错！");
		exceptionLabel.setBounds(64, 24, 412, 16);

		detailText = new Text(shell, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);
		detailText.setEditable(false);
		detailText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		detailText.setBounds(16, 56, 412, 64);

		final Button copyButton = new Button(shell, SWT.NONE);
		copyButton.setText("复制");
		copyButton.setBounds(264, 130, 80, 24);
		copyButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				StringSelection stringSelection = new StringSelection(detailText.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				MessageDialog.openInformation(shell, "复制", "已复制异常信息到您的粘贴板上!");
			}
		});

		final Button okButton = new Button(shell, SWT.NONE);
		okButton.setText("关闭");
		okButton.setBounds(348, 130, 80, 24);
		okButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
			}
		});

		exceptionListener = new ExceptionAdapter() { // 只在非UI线程执行
			public void onBackExceptionCatched(ExceptionEvent event) {}
			public void onForeExceptionCatched(ExceptionEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					showException(event.getException());
				}
			}
		};
		Worker.getWorker().addListener(exceptionListener);
	}

	private void showException(final Throwable exception) {
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
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

	private Image getImage() {
		final Image[] image = new Image[1];
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
				image[0] = shell.getDisplay().getSystemImage(SWT.ICON_ERROR);
			}
		});
		return image[0];
	}

	public void dispose() {
		Worker.getWorker().removeListener(exceptionListener);
		if (shell != null && ! shell.isDisposed())
			shell.dispose();
	}

}
