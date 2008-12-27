package com.googlecode.struts4rcp.client.view.jface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.PropertyInfo;
import com.googlecode.struts4rcp.client.event.PropertyEvent;
import com.googlecode.struts4rcp.client.event.PropertyListener;

public class ConfigurationPane extends Composite {

	private final Client client;

	private final PropertyListener configurationListener;

	private final ArrayList<PropertyInfo> configurations = new ArrayList<PropertyInfo>();

	private final List configurationList;

	public ConfigurationPane(final Composite parent, final Client client) {
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
				fileDialog.setFileName("transport.properties");
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
						for (Map.Entry<Object, Object> entry : client.getProperties().entrySet()) {
							writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
						}
						writer.flush();
					} finally {
						if (writer != null)
							writer.close();
					}
					MessageDialog.openInformation(parent.getShell(), "保存", "保存配置列表成功!");
				} catch (IOException ex) {
					MessageDialog.openWarning(parent.getShell(), "保存", "保存配置列表失败! 原因: " + ex.getMessage());
				}
			}
		});
		ToolItem editItem = new ToolItem(toolBar, SWT.NONE);
		editItem.setImage(Images.getImage("edit.gif"));
		editItem.setText("修改");
		/*editItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (getConfigurationModelSize() == 0) {
					MessageDialog.openWarning(parent.getShell(), "修改", "没有任何配置项!");
					return;
				}
				PropertyInfo configuration = null;
				synchronized (configurations) {
					int selected = configurationList.getSelectionIndex();
					if (selected > -1 && selected < configurations.size())
						configuration = (PropertyInfo)configurations.get(selected);
				}
				if (configuration == null) {
					MessageDialog.openWarning(parent.getShell(), "修改", "请选择配置项!");
					return;
				}
				String desc = configuration.getDescription();
				if (desc != null && desc.length() > 0) {
					boolean ch = MessageDialog.openQuestion(parent.getShell(), "修改", desc + "\n是否继续修改?");
					if (! ch)
						return;
				}
				InputDialog configurationInputDialog = new InputDialog(parent.getShell(), "输入", "请输入配置项\"" + configuration.getName() + "\"的新值：", configuration.getDefaultValue(), null);
				int ch = configurationInputDialog.open();
				if (ch == InputDialog.OK) {
					String newValue = configurationInputDialog.getValue();
					if (! newValue.equals(configuration.getValue())) {
						client.setProperty(configuration.getKey(), newValue);
						MessageDialog.openInformation(parent.getShell(), "修改", "修改配置项成功!");
					}
				}
			}
		});*/
		ToolItem refreshItem = new ToolItem(toolBar, SWT.NONE);
		refreshItem.setImage(Images.getImage("refresh.gif"));
		refreshItem.setText("刷新");
		refreshItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refreshConfigurationList();
				MessageDialog.openInformation(parent.getShell(), "刷新", "刷新配置项成功!");
			}
		});
		configurationList = new List(this, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		configurationList.setBounds(0, 40, 484, 400);

		configurationListener = new PropertyListener() {
			public void onPropertyChanged(final PropertyEvent event) {
				final PropertyInfo configuration = event.getPropertyInfo();
				synchronized (configurations) {
					if (configurations.contains(configuration)) {
						final int i = configurations.indexOf(configuration);
						configurations.set(i, configuration);
						configurationList.setItem(i, configuration.toString());
					}
				}
			}
			public boolean isAsync() {
				return false;
			}
		};
		client.addListener(configurationListener);
		refreshConfigurationList();
	}

	private void refreshConfigurationList() {
		Map<String, PropertyInfo> configs = client.getPropertyInfos();
		synchronized (configurations) {
			configurations.clear();
			configurationList.removeAll();
			for (PropertyInfo configuration : configs.values()) {
				configurations.add(configuration);
				configurationList.add(configuration.toString());
			}
		}
	}

	private int getConfigurationModelSize() {
		synchronized (configurations) {
			return configurations.size();
		}
	}

	@Override
	public void dispose() {
		client.removeListener(configurationListener);
		super.dispose();
	}

}
