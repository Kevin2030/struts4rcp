package com.googlecode.struts4rcp.client.view.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.googlecode.struts4rcp.client.Actions;

public class DialogTest {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		properties.setProperty("server.host", "localhost");
		properties.setProperty("server.port", "8080");
		properties.setProperty("context.path", "rcpstrutsdemo");
		Actions.init(properties);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Actions.destroy();
			}
		});
		JDialog dialog = createTestDialog(frame);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		dialog.setVisible(true);
	}

	public static JDialog createTestDialog(JFrame frame) {
		return new ExceptionDialog(frame, Actions.getClient());
	}

}
