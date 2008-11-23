package com.googlecode.struts4rcp.client.transporter;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 基于HttpURLConnection的传输器实现
 * @see java.net.HttpURLConnection
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class HttpURLConnectionTransporter extends AbstractHttpTransporter<HttpURLConnection> {

	@Override
	protected HttpURLConnection getRequest(String actionName) throws IOException {
		return (HttpURLConnection) new URL(actionName).openConnection();
	}

	@Override
	protected Serializable transport(HttpURLConnection request, String actionName, Serializable model) throws IOException {
		try {
			if (connectionTimeout != UNKNOWN_CONNECTION_TIMEOUT)
				request.setConnectTimeout(connectionTimeout);
			if (socketTimeout != UNKNOWN_SOCKET_TIMEOUT)
				request.setReadTimeout(socketTimeout);
			request.setRequestMethod("POST");
			request.setDoOutput(true);
			request.setDoInput(true);
			serializer.serialize(model, request.getOutputStream());
			request.connect();
			assertSuccessStatusCode(request.getResponseCode(), request.getResponseMessage());
			return serializer.deserialize(request.getInputStream());
		} finally {
			request.disconnect();
		}
	}

	@Override
	protected void abort(HttpURLConnection request) throws IOException {
		request.disconnect();
	}

}
