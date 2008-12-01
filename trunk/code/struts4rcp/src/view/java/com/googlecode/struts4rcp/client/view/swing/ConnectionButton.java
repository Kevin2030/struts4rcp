package com.googlecode.struts4rcp.client.view.swing;

import java.awt.Frame;

import com.googlecode.struts4rcp.client.Client;

/**
 * @deprecated 替换为<code>com.googlecode.struts4rcp.client.view.swing.ConnectionStatus</code>
 * @see com.googlecode.struts4rcp.client.view.swing.ConnectionStatus
 */
@Deprecated
public class ConnectionButton extends ConnectionStatus {

	private static final long serialVersionUID = 1L;

	public ConnectionButton(Frame frame, Client client) {
		super(frame, client);
	}

}
