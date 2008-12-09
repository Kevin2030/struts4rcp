package com.googlecode.struts4rcp.client.view.jface;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ExceptionAdapter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ExceptionPane extends Composite {
	
	private final Client client;

	private ExceptionListener exceptionListener;
	
	private Text exceptionTextArea;
	
	public ExceptionPane(final Composite parent, final Client client) {
		super(parent, SWT.NONE);
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
		ToolBar toolBar = new ToolBar(this, SWT.NONE);
		toolBar.setSize(400, 40);
		ToolItem saveItem = new ToolItem(toolBar, SWT.NONE);
		saveItem.setImage(Images.getImage("save.gif"));
		saveItem.setText("保存");
		saveItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.SAVE);
				fileDialog.setFileName("exception.txt");
				String fileName = fileDialog.open();
				File file = new File(fileName);
				if (file.exists()) {
					boolean ch = MessageDialog.openQuestion(parent.getShell(), "保存", "文件已存在，是否覆盖?");
					if (! ch)
						return;
				}
				try {
					FileWriter writer = null;
					try {
						writer = new FileWriter(file);
						writer.write(exceptionTextArea.getText());
						writer.flush();
					} finally {
						if (writer != null)
							writer.close();
					}
					MessageDialog.openInformation(parent.getShell(), "保存", "保存异常信息成功!");
				} catch (IOException ex) {
					MessageDialog.openWarning(parent.getShell(), "保存", "保存异常信息失败! 原因: " + ex.getMessage());
				}
			}
		});
		ToolItem copyItem = new ToolItem(toolBar, SWT.NONE);
		copyItem.setImage(Images.getImage("copy.gif"));
		copyItem.setText("复制");
		copyItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				copyText();
			}
		});
		ToolItem clearItem = new ToolItem(toolBar, SWT.NONE);
		clearItem.setImage(Images.getImage("clear.gif"));
		clearItem.setText("清空");
		clearItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ExceptionPane.this.clearText();
			}
		});
		exceptionTextArea = new Text(this, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);
		exceptionTextArea.setBounds(0, 40, 484, 400);
		exceptionTextArea.setEditable(false);
		exceptionTextArea.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		exceptionListener = new ExceptionAdapter() {
			public void onBackExceptionCatched(final ExceptionEvent event) {
				final String msg = ExceptionUtils.getDetailMessage(event.getException());
				Display.getDefault().asyncExec(new Runnable(){
					public void run() {
						ExceptionPane.this.appendText(msg);
					}
				});
			}
			public void onForeExceptionCatched(final ExceptionEvent event) {
				final String msg = ExceptionUtils.getDetailMessage(event.getException());
				Display.getDefault().asyncExec(new Runnable(){
					public void run() {
						ExceptionPane.this.appendText(msg);
					}
				});
			}
		};
		client.addListener(exceptionListener);
	}

	protected void appendText(final String text) {
		if (exceptionTextArea.getText().length() > 80000)
			exceptionTextArea.setText("");
		exceptionTextArea.append("[");
		exceptionTextArea.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		exceptionTextArea.append("]\n");
		exceptionTextArea.append(text);
		exceptionTextArea.append("\n");
	}

	protected void clearText() {
		exceptionTextArea.setText("");
	}

	protected void copyText() {
		StringSelection stringSelection = new StringSelection(exceptionTextArea.getText());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
		MessageDialog.openInformation(super.getShell(), "复制", "已复制异常信息到您的粘贴板上!");
	}

	@Override
	public void dispose() {
		client.removeListener(exceptionListener);
		super.dispose();
	}

}
