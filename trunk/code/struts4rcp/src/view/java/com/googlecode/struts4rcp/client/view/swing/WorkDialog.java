package com.googlecode.struts4rcp.client.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.googlecode.struts4rcp.client.event.WorkAdapter;
import com.googlecode.struts4rcp.client.event.WorkEvent;
import com.googlecode.struts4rcp.client.event.WorkListener;
import com.googlecode.struts4rcp.client.work.Work;
import com.googlecode.struts4rcp.client.work.Worker;
import com.googlecode.struts4rcp.util.ThreadUtils;

public class WorkDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final WorkListener workListener;

	private final JLabel workLabel;

	private final JButton backButton;

	private final JButton abortButton;

	private Work work;

	private Icon enableIcon = Images.getIcon("enable.gif");

	private Icon disableIcon = Images.getIcon("disable.gif");

	public WorkDialog(final Frame owner) {
		super(owner, true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setTitle("正在传输");
		this.setSize(480, 200);
		this.setResizable(false);

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

		workLabel = new JLabel();
		messagePanel.add(BorderLayout.SOUTH, workLabel);

		final JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressPanel.add(BorderLayout.SOUTH, progressBar);

		backButton = new JButton("后台运行", Images.getIcon("back.gif"));
		backPanel.add(BorderLayout.EAST, backButton);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final Work work = WorkDialog.this.work;
				if (work != null && work.isBackable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								work.back();
							} catch (Throwable t) {
								JOptionPane.showMessageDialog(WorkDialog.this, "后台运行传输项失败! 原因: " + t.getMessage(), "后台运行", JOptionPane.WARNING_MESSAGE);
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
				final Work work = WorkDialog.this.work;
				if (work != null && work.isAbortable()) {
					ThreadUtils.execute(new Runnable(){
						public void run() {
							try {
								work.abort();
							} catch (Throwable t) {
								JOptionPane.showMessageDialog(WorkDialog.this, "中止传输项失败! 原因: " + t.getMessage(), "中止", JOptionPane.WARNING_MESSAGE);
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

	private void showWork(Work work) {
		if (this.work != work) {
			this.work = work;
			if (work != null) {
				backButton.setEnabled(work.isBackable());
				abortButton.setEnabled(work.isAbortable());
				if (work.isAbortable())
					workLabel.setIcon(enableIcon);
				else
					workLabel.setIcon(disableIcon);
				workLabel.setText(work.toString());
				if (! WorkDialog.this.isVisible()) {
					Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension fra = WorkDialog.this.getSize();
					WorkDialog.this.setLocation((scr.width - fra.width) / 2, (scr.height - fra.height) / 2);// 在屏幕居中显示
					WorkDialog.this.setVisible(true);
				}
			} else {
				WorkDialog.this.setVisible(false);
			}
		}
	}

	@Override
	public void dispose() {
		Worker.getWorker().removeListener(workListener);
		super.dispose();
	}

}
