package com.googlecode.struts4rcp.client.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.event.ExceptionAdapter;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.util.ExceptionUtils;

public class ExceptionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final ExceptionListener exceptionListener;

	private final Client client;

	public ExceptionDialog(Frame owner, Client client) {
		super(owner, true);
		this.client = client;

		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setTitle("传输出错");
		this.setSize(480, 200);
		this.setResizable(false);

		this.getContentPane().setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);

		final JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.CENTER, messagePanel);

		final JPanel okPanel = new JPanel();
		okPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.SOUTH, okPanel);

		final JPanel copyPanel = new JPanel();
		copyPanel.setLayout(new BorderLayout());
		okPanel.add(BorderLayout.CENTER, copyPanel);

		final JLabel exceptionLabel = new JLabel("非常抱歉，传输出错，请联系管理员！");
		messagePanel.add(BorderLayout.NORTH, exceptionLabel);

		final JScrollPane detailPane = new JScrollPane();
		detailPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		final JTextArea detailText = new JTextArea();
		detailText.setEditable(false);
		detailText.setBackground(Color.WHITE);
		detailPane.getViewport().setView(detailText);
		messagePanel.add(BorderLayout.CENTER, detailPane);

		final JButton okButton = new JButton("关闭", Images.getIcon("close.gif"));
		okPanel.add(BorderLayout.EAST, okButton);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ExceptionDialog.this.setVisible(false);
			}
		});

		final JButton copyButton = new JButton("复制", Images.getIcon("copy.gif"));
		copyPanel.add(BorderLayout.EAST, copyButton);
		copyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(detailText.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
				JOptionPane.showMessageDialog(ExceptionDialog.this, "已复制异常信息到您的粘贴板上!", "复制", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		exceptionListener = new ExceptionAdapter() { // 只在非UI线程执行
			public void onBackExceptionCatched(ExceptionEvent event) {}
			public void onForeExceptionCatched(ExceptionEvent event) {
				if (UIUtils.isNonUIThread(event.getThread())) {
					exceptionLabel.setText(event.getException().getMessage());
					detailText.setText("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "]\n" + ExceptionUtils.getDetailMessage(event.getException()));
					if (! ExceptionDialog.this.isVisible()) {
						Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
						Dimension fra = ExceptionDialog.this.getSize();
						ExceptionDialog.this.setLocation((scr.width - fra.width) / 2, (scr.height - fra.height) / 2);// 在屏幕居中显示
						ExceptionDialog.this.setVisible(true);
					}
				}
			}
		};
		client.addListener(exceptionListener);
	}

	@Override
	public void dispose() {
		client.removeListener(exceptionListener);
		super.dispose();
	}

}
