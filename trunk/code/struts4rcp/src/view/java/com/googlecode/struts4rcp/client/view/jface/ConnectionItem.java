package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.widgets.Composite;

import com.googlecode.struts4rcp.client.Client;

/**
 * 传输状态项，可将此状态项添加到工具栏，状态栏上。
 * <br>使用如：
 * <pre>
 * public class XXXActionBarAdvisor extends ActionBarAdvisor {
 *     protected void fillStatusLine(IStatusLineManager statusLine) {
 *         super.fillStatusLine(statusLine);
 *         statusLine.add(new TransportStatusItem(Actions.getClient()));
 *     }
 * }
 * </pre>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ConnectionItem extends ContributionItem {

	private ConnectionButton transportStatusButton;

	public ConnectionButton getTransportStatusButton() {
		return transportStatusButton;
	}

	private final Client client;

	public ConnectionItem(Client client) {
		super(ConnectionItem.class.getName());
		this.client = client;
	}

	@Override
	public void fill(Composite parent) {
		transportStatusButton = new ConnectionButton(parent, client);
		this.setVisible(true);
	}

	@Override
	public void dispose() {
		super.dispose();
		transportStatusButton.dispose();
	}

}
