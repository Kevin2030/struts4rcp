package com.googlecode.struts4rcp.client.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Execution;
import com.googlecode.struts4rcp.client.event.ExecutionAdapter;
import com.googlecode.struts4rcp.client.event.ExecutionEvent;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.util.ThreadUtils;

public class ExecutionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final ExecutionListener executionListener;

	private final JLabel executionLabel;

	private final JButton backButton;

	private final JButton abortButton;

	private Execution execution; // execution全部在UI线程内使用，所以不需要同步

	private final Client client;

	private Icon enableIcon = Images.getIcon("enable.gif");

	private Icon disableIcon = Images.getIcon("disable.gif");

	public ExecutionDialog(final Frame owner, final Client client) {
		super(owner, true);
		this.client = client;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setTitle("正在传输");
		this.setSize(480, 200);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				final Execution execution = ExecutionDialog.this.execution;
				if (execution != null) {
					if (execution.isBackable()) {
						ThreadUtils.execute(new Runnable(){
							public void run() {
								try {
									execution.back();
									JOptionPane.showMessageDialog(ExecutionDialog.this, "传输项已转为后台运行!", "后台运行", JOptionPane.INFORMATION_MESSAGE);
								} catch (Throwable t) {
									JOptionPane.showMessageDialog(ExecutionDialog.this, "后台运行传输项失败! 原因: " + t.getMessage(), "后台运行", JOptionPane.WARNING_MESSAGE);
								}
							}
						});
					} else if (execution.isAbortable()) {
						ThreadUtils.execute(new Runnable(){
							public void run() {
								try {
									execution.abort();
									JOptionPane.showMessageDialog(ExecutionDialog.this, "传输项已中止!", "中止", JOptionPane.INFORMATION_MESSAGE);
								} catch (Throwable t) {
									JOptionPane.showMessageDialog(ExecutionDialog.this, "中止传输项失败! 原因: " + t.getMessage(), "中止", JOptionPane.WARNING_MESSAGE);
								}
							}
						});
					}
				}
			}
		});

		this.getContentPane().setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);

		final JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH, messagePanel);

		final JPanel abortPanel = new JPanel();
		abortPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.SOUTH, abortPanel);

		final JPanel backPanel = new JPanel();
		backPanel.setLayout(new BorderLayout());
		abortPanel.add(BorderLayout.CENTER, backPanel);

		final JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.CENTER, progressPanel);

		final JLabel messageLabel = new JLabel("正在发送请求，请稍候...");
		messagePanel.add(BorderLayout.NORTH, messageLabel);

		executionLabel = new JLabel();
		messagePanel.add(BorderLayout.SOUTH, executionLabel);

		final JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressPanel.add(BorderLayout.SOUTH, progressBar);

		backButton = new JButton("后台运行", Images.getIcon("back.gif"));
		backPanel.add(BorderLayout.EAST, backButton);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final Execution execution = ExecutionDialog.this.execution;
				if (execution != null && execution.isBackable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								execution.back();
								JOptionPane.showMessageDialog(ExecutionDialog.this, "传输项已转为后台运行!", "后台运行", JOptionPane.INFORMATION_MESSAGE);
							} catch (Throwable t) {
								JOptionPane.showMessageDialog(ExecutionDialog.this, "后台运行传输项失败! 原因: " + t.getMessage(), "后台运行", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
				}
			}
		});

		abortButton = new JButton("中止", Images.getIcon("abort.gif"));
		abortPanel.add(BorderLayout.EAST, abortButton);
		abortButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final Execution execution = ExecutionDialog.this.execution;
				if (execution != null && execution.isAbortable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								execution.abort();
								JOptionPane.showMessageDialog(ExecutionDialog.this, "传输项已中止!", "中止", JOptionPane.INFORMATION_MESSAGE);
							} catch (Throwable t) {
								JOptionPane.showMessageDialog(ExecutionDialog.this, "中止传输项失败! 原因: " + t.getMessage(), "中止", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
				}
			}
		});

		executionListener = new ExecutionAdapter() { // 只在非UI线程执行
			public void onExecuting(ExecutionEvent event) {
				if (! UIUtils.isUIThread(event.getThread())) {
					showExecution(event.getExecution());
				}
			}
			public void onBackExecuting(ExecutionEvent event) {
				onExecuted(event);
			}
			public void onExecuted(ExecutionEvent event) {
				if (! UIUtils.isUIThread(event.getThread())) {
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

	private void showExecution(Execution execution) {
		if (this.execution != execution) {
			this.execution = execution;
			if (execution != null) {
				backButton.setEnabled(execution.isBackable());
				abortButton.setEnabled(execution.isAbortable());
				if (execution.isAbortable())
					executionLabel.setIcon(enableIcon);
				else
					executionLabel.setIcon(disableIcon);
				executionLabel.setText(execution.toString());
				if (! ExecutionDialog.this.isVisible()) {
					Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension fra = ExecutionDialog.this.getSize();
					ExecutionDialog.this.setLocation((scr.width - fra.width) / 2, (scr.height - fra.height) / 2);// 在屏幕居中显示
					ExecutionDialog.this.setVisible(true);
				}
			} else {
				ExecutionDialog.this.setVisible(false);
			}
		}
	}

	@Override
	public void dispose() {
		client.removeListener(executionListener);
		super.dispose();
	}

}
