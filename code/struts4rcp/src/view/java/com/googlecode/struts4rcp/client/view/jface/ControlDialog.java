package com.googlecode.struts4rcp.client.view.jface;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ExceptionAdapter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ControlDialog extends Dialog {

	private final Client client;

	// private final Collection<Execution> executions = new ArrayList<Execution>();

	protected ControlDialog(Shell parentShell, Client client) {
		super(parentShell);
		if (client == null)
			throw new NullPointerException("Client == null!");
		this.client = client;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500, 500);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		final Display display = Display.getCurrent();
		Rectangle displayBounds = display.getClientArea();
		Rectangle shellBounds = newShell.getBounds();
		int x = (displayBounds.width - shellBounds.width) / 2;
		int y = (displayBounds.height - shellBounds.height) / 2;
		newShell.setLocation(x, y);
		newShell.setText("控制台");
	}

	@Override
	protected Control createContents(Composite parent) {
		parent.setLayout(new FillLayout());
		TabFolder tabFolder = new TabFolder(parent, SWT.FILL);
		tabFolder.setLayout(new FillLayout());

		TabItem transportationTabItem = new TabItem(tabFolder, SWT.FILL);
		transportationTabItem.setText("传输管理");
		transportationTabItem.setControl(createTransportationPane(tabFolder));

		TabItem exceptionTabItem = new TabItem(tabFolder, SWT.FILL);
		exceptionTabItem.setText("异常管理");
		exceptionTabItem.setControl(createExceptionPane(tabFolder));

		TabItem configurationTabItem = new TabItem(tabFolder, SWT.FILL);
		configurationTabItem.setText("配置管理");
		configurationTabItem.setControl(createConfigurationPane(tabFolder));

		return tabFolder;
	}

	private Control createTransportationPane(Composite parent) {
		Composite transportationPane = new Composite(parent, SWT.NONE);
		ToolBar toolBar = new ToolBar(transportationPane, SWT.NONE);
		toolBar.setSize(400, 40);
		ToolItem abortItem = new ToolItem(toolBar, SWT.NONE);
		abortItem.setImage(Images.getImage("abort.gif"));
		abortItem.setText("中止");
		ToolItem refreshItem = new ToolItem(toolBar, SWT.NONE);
		refreshItem.setImage(Images.getImage("refresh.gif"));
		refreshItem.setText("刷新");
		List list = new List(transportationPane, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		list.setBounds(0, 40, 485, 400);
		return transportationPane;
	}

	private ExceptionListener exceptionListener;

	private Text exceptionTextArea;

	private Control createExceptionPane(Composite parent) {
		Composite exceptionPane = new Composite(parent, SWT.NONE);
		ToolBar toolBar = new ToolBar(exceptionPane, SWT.NONE);
		toolBar.setSize(400, 40);
		ToolItem saveItem = new ToolItem(toolBar, SWT.NONE);
		saveItem.setImage(Images.getImage("save.gif"));
		saveItem.setText("保存");
		saveItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// TODO
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
				clearText();
			}
		});
		exceptionTextArea = new Text(exceptionPane, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);
		exceptionTextArea.setBounds(0, 40, 485, 400);
		exceptionTextArea.setEditable(false);
		exceptionTextArea.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		exceptionListener = new ExceptionDelegate(new ExceptionAdapter() {
			public void onBackCatched(ExceptionEvent event) {
				appendText(ExceptionUtils.getDetailMessage(event.getException()));
			}
			public void onCatched(ExceptionEvent event) {
				appendText(ExceptionUtils.getDetailMessage(event.getException()));
			}
		});
		client.addListener(exceptionListener);
		return exceptionPane;
	}

	protected void appendText(String text) {
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

	private Control createConfigurationPane(Composite parent) {
		Composite configurationPane = new Composite(parent, SWT.NONE);
		ToolBar toolBar = new ToolBar(configurationPane, SWT.NONE);
		toolBar.setSize(400, 40);
		ToolItem saveItem = new ToolItem(toolBar, SWT.NONE);
		saveItem.setImage(Images.getImage("save.gif"));
		saveItem.setText("保存");
		saveItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// TODO
			}
		});
		ToolItem editItem = new ToolItem(toolBar, SWT.NONE);
		editItem.setImage(Images.getImage("edit.gif"));
		editItem.setText("修改");
		ToolItem refreshItem = new ToolItem(toolBar, SWT.NONE);
		refreshItem.setImage(Images.getImage("refresh.gif"));
		refreshItem.setText("刷新");
		List list = new List(configurationPane, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		list.setBounds(0, 40, 485, 400);
		return configurationPane;
	}

	@Override
	public boolean close() {
		client.removeListener(exceptionListener);
		return super.close();
	}

}
