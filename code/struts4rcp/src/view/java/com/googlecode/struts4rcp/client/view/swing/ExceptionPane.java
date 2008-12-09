package com.googlecode.struts4rcp.client.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import com.googlecode.struts4rcp.client.Worker;
import com.googlecode.struts4rcp.client.event.ExceptionAdapter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ExceptionPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JTextArea exceptionTextArea = new JTextArea();

	private final JCheckBox autoScrollCheckBox = new JCheckBox("自动滚动", true);

	private final JToolBar toolBar = new JToolBar();

	public JToolBar getToolBar() {
		return toolBar;
	}

	private final ExceptionListener exceptionListener;

	public ExceptionPane() {
		this.setLayout(new BorderLayout());

		toolBar.setFloatable(false);
		this.add(BorderLayout.NORTH, toolBar);

		exceptionTextArea.setEditable(false);
		exceptionTextArea.setBackground(Color.WHITE);
		final JScrollPane exceptionPane = new JScrollPane();
		exceptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		exceptionPane.getViewport().setView(exceptionTextArea);
		this.add(BorderLayout.CENTER, exceptionPane);

		final JButton saveButton = new JButton("保存", Images.getIcon("save.gif"));
		saveButton.setToolTipText("保存异常信息");
		toolBar.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setSelectedFile(new File("exception.txt"));
				int i = fileChooser.showSaveDialog(ExceptionPane.this);
				if (i == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file.exists()) {
						int ch = JOptionPane.showConfirmDialog(ExceptionPane.this, "文件已存在，是否覆盖?", "保存", JOptionPane.YES_NO_OPTION);
						if (ch != JOptionPane.YES_OPTION)
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
						JOptionPane.showMessageDialog(ExceptionPane.this, "保存异常信息成功!", "保存", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(ExceptionPane.this, "保存异常信息失败! 原因: " + ex.getMessage(), "保存", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		JButton copyButton = new JButton("复制", Images.getIcon("copy.gif"));
		toolBar.add(copyButton);
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExceptionPane.this.copyText();
			}
		});

		JButton clearButton = new JButton("清空", Images.getIcon("clear.gif"));
		toolBar.add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExceptionPane.this.clearText();
			}
		});

		toolBar.add(autoScrollCheckBox);

		exceptionListener = new ExceptionDelegate(new ExceptionAdapter() {
			public void onBackExceptionCatched(ExceptionEvent event) {
				ExceptionPane.this.appendText(ExceptionUtils.getDetailMessage(event.getException()));
			}
			public void onForeExceptionCatched(ExceptionEvent event) {
				ExceptionPane.this.appendText(ExceptionUtils.getDetailMessage(event.getException()));
			}
		});
		Worker.getWorker().addListener(exceptionListener);
	}

	protected void appendText(String text) {
		if (exceptionTextArea.getText().length() > 80000)
			exceptionTextArea.setText("");
		exceptionTextArea.append("[");
		exceptionTextArea.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		exceptionTextArea.append("]\n");
		exceptionTextArea.append(text);
		exceptionTextArea.append("\n");
		if (autoScrollCheckBox.isSelected()) {
			String str = exceptionTextArea.getText();
			int len = str.length();
			if (len > 0)
				exceptionTextArea.select(len - 1, len);
		}
	}

	protected void clearText() {
		exceptionTextArea.setText("");
	}

	protected void copyText() {
		StringSelection stringSelection = new StringSelection(exceptionTextArea.getText());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
		JOptionPane.showMessageDialog(ExceptionPane.this, "已复制异常信息到您的粘贴板上!", "复制", JOptionPane.INFORMATION_MESSAGE);
	}

	public void dispose() {
		Worker.getWorker().removeListener(exceptionListener);
	}

}
