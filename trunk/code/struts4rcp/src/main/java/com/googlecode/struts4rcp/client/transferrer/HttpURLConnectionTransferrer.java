package com.googlecode.struts4rcp.client.transferrer;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 基于HttpURLConnection的传输器实现
 * @see java.net.HttpURLConnection
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class HttpURLConnectionTransferrer extends AbstractHttpTransferrer<HttpURLConnection> {

	@Override
	protected HttpURLConnection getRequest(String method, String url) throws IOException {
		HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
		request.setRequestMethod(method);
		if (connectionTimeout != UNKNOWN_CONNECTION_TIMEOUT)
			request.setConnectTimeout(connectionTimeout);
		if (socketTimeout != UNKNOWN_SOCKET_TIMEOUT)
			request.setReadTimeout(socketTimeout);
		request.setDoOutput(true);
		request.setDoInput(true);
		return request;
	}

	@Override
	protected void setHeader(HttpURLConnection request, String key, String value)
			throws IOException {
		request.setRequestProperty(key, value);
	}

	@Override
	protected Serializable transfer(HttpURLConnection request, String url, Serializable model) throws IOException {
		try {
			serializer.serialize(model, request.getOutputStream());
			request.connect();
			assertSuccessStatusCode(request.getResponseCode(), request.getResponseMessage());
			return serializer.deserialize(Serializable.class, request.getInputStream());
		} finally {
			request.disconnect();
		}
	}

	@Override
	protected void abort(HttpURLConnection request) throws IOException {
		request.disconnect();
	}

}
