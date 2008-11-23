package com.xxx.demo.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.client.ActionCallback;
import com.googlecode.struts4rcp.client.Actions;
import com.googlecode.struts4rcp.client.view.swing.ConnectionButton;
import com.googlecode.struts4rcp.util.ExceptionUtils;
import com.xxx.demo.domain.Account;
import com.xxx.demo.domain.User;

/**
 * 客户端测试类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Main {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private JFrame frame = new JFrame();

	private JTextArea resultArea = new JTextArea();

	public Main() {
		frame.setTitle("RCP Struts Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getContentPane().setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Actions.destroy();
			}
		});

		JPanel formPane = new JPanel(new FlowLayout());

		JScrollPane outputPane = new JScrollPane();
		outputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		outputPane.getViewport().setView(resultArea);

		JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPane, outputPane);
		mainPane.setDividerLocation(100);
		mainPane.setOneTouchExpandable(true);
		frame.getContentPane().add(BorderLayout.CENTER, mainPane);

		JToolBar statusBar = new JToolBar();
		statusBar.setFloatable(false);
		statusBar.setLayout(new BorderLayout());
		frame.getContentPane().add(BorderLayout.SOUTH, statusBar);

		JLabel usernameLabel = new JLabel("用户名：");
		formPane.add(usernameLabel);
		final JTextField usernameField = new JTextField("liangfei", 10);
		formPane.add(usernameField);

		JLabel passwordLabel = new JLabel("密码：");
		formPane.add(passwordLabel);
		final JPasswordField passwordField = new JPasswordField("123456", 10);
		formPane.add(passwordField);

		JLabel delayLabel = new JLabel("模拟服务器端耗时操作(ms)：");
		formPane.add(delayLabel);
		final JTextField delayField = new JTextField("1000", 10);
		formPane.add(delayField);

		final JCheckBox exceptionField = new JCheckBox("模拟服务器端抛出异常", false);
		formPane.add(exceptionField);

		final JCheckBox backableField = new JCheckBox("是否允许转为后台运行", true);
		formPane.add(backableField);

		final JCheckBox abortableField = new JCheckBox("是否允许中止运行", true);
		formPane.add(abortableField);

		JButton restButton = new JButton("重置表单");
		formPane.add(restButton);
		restButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				usernameField.setText("liangfei");
				passwordField.setText("123456");
				delayField.setText("1000");
				exceptionField.setSelected(false);
				backableField.setSelected(true);
				abortableField.setSelected(true);
			}
		});

		JButton syncButton = new JButton("同步运行");
		formPane.add(syncButton);
		syncButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				testSyncExecute(0, account, backableField.isSelected(), abortableField.isSelected());
			}
		});

		JButton threadButton = new JButton("新线程运行");
		formPane.add(threadButton);
		threadButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				testThreadExecute(0, account, backableField.isSelected(), abortableField.isSelected());
			}
		});

		JButton asyncButton = new JButton("异步运行");
		formPane.add(asyncButton);
		asyncButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				testAsyncExecute(0, account, backableField.isSelected(), abortableField.isSelected());
			}
		});

		JButton backButton = new JButton("后台运行");
		formPane.add(backButton);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				testBackExecute(0, account, abortableField.isSelected());
			}
		});

		JButton sync5Button = new JButton("同步运行5次");
		formPane.add(sync5Button);
		sync5Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				for (int i = 1; i <= 5; i ++) {
					testSyncExecute(i, account, backableField.isSelected(), abortableField.isSelected());
				}
			}
		});

		JButton thread5Button = new JButton("新线程运行5次");
		formPane.add(thread5Button);
		thread5Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				for (int i = 1; i <= 5; i ++) {
					testThreadExecute(i, account, backableField.isSelected(), abortableField.isSelected());
				}
			}
		});

		JButton async5Button = new JButton("异步运行5次");
		formPane.add(async5Button);
		async5Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				for (int i = 1; i <= 5; i ++) {
					testAsyncExecute(i, account, backableField.isSelected(), abortableField.isSelected());
				}
			}
		});

		JButton back5Button = new JButton("后台运行5次");
		formPane.add(back5Button);
		back5Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (delayField.getText().length() == 0)
					delayField.setText("0");
				Account account = new Account(usernameField.getText(), String.valueOf(passwordField.getPassword()), Long.parseLong(delayField.getText()), exceptionField.isSelected());
				for (int i = 1; i <= 5; i ++) {
					testBackExecute(i, account, abortableField.isSelected());
				}
			}
		});

		JButton clearButton = new JButton("清空输出");
		formPane.add(clearButton);
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				clearText();
			}
		});

		final ConnectionButton statusButton = new ConnectionButton(frame, Actions.getClient());
		statusBar.add(BorderLayout.EAST, statusButton);

		frame.setVisible(true);
	}

	public void testSyncExecute(final int i, final Account account, boolean backable, boolean abortable) {
		try {
			Action<Account, User> loginAction = Actions.getAction("loginAction", backable, abortable);
			User user = loginAction.execute(account);
			appendText("Sync: (" + i + ") " + user);
		} catch (Throwable e) {
			appendText(ExceptionUtils.getDetailMessage(e));
		}
	}

	public void testThreadExecute(final int i, final Account account, final boolean backable, final boolean abortable) {
		new Thread() {
			public void run() {
				try {
					Action<Account, User> loginAction = Actions.getAction("loginAction", backable, abortable);
					User user = loginAction.execute(account);
					appendText("Sync: (" + i + ") " + user);
				} catch (Throwable e) {
					appendText(ExceptionUtils.getDetailMessage(e));
				}
			}
		}.start();
	}

	public void testAsyncExecute(final int i, Account account, boolean backable, boolean abortable) {
		try {
			Action<Account, User> loginAction = Actions.getAsyncAction("loginAction", new ActionCallback<User>() {
				public void callback(User user) throws Exception {
					appendText("Async: (" + i + ") " + user);
				}
			}, backable, abortable);
			loginAction.execute(account);
		} catch (Throwable e) {
			appendText(ExceptionUtils.getDetailMessage(e));
		}
	}

	public void testBackExecute(final int i, Account account, boolean abortable) {
		try {
			Action<Account, User> loginAction = Actions.getBackAsyncAction("loginAction", new ActionCallback<User>() {
				public void callback(User user) throws Exception {
					appendText("Back: (" + i + ") " + user);
				}
			}, abortable);
			loginAction.execute(account);
		} catch (Throwable e) {
			appendText(ExceptionUtils.getDetailMessage(e));
		}
	}

	public void appendText(String text) {
		resultArea.append(text + "\n");
		int len = resultArea.getText().length();
		if (len > 0)
			resultArea.select(len - 1, len);
	}

	public void clearText() {
		resultArea.setText("");
	}

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("server.host", (args != null && args.length > 0) ? args[0] : "localhost");
		properties.setProperty("server.host", (args != null && args.length > 1) ? args[1] : "8080");
		properties.setProperty("context.path", (args != null && args.length > 2) ? args[2] : "struts4rcpdemo");
		Actions.init(properties);
		new Main();
	}

}
