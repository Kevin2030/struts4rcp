package com.googlecode.struts4rcp.client.transporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.MultihomePlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.util.serializer.StreamSerializer;

/**
 * 基于HttpClient的传输器实现
 * @see org.apache.http.client.HttpClient
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class HttpClientTransporter extends AbstractHttpTransporter<HttpPost> {

	private HttpClient httpClient;

	/**
	 * 创建HttpClient实例，子类可以通过覆写此方法，替换实现类
	 * @return HttpClient实例
	 */
	protected HttpClient createHttpClient() {
		HttpParams params = new BasicHttpParams();
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", MultihomePlainSocketFactory.getSocketFactory(), 80));
		return new DefaultHttpClient(new ThreadSafeClientConnManager(params, registry), params);
	}

	@Override
	public void init(Client client, Properties config) {
		super.init(client, config);
		httpClient = createHttpClient();
		if (httpClient == null)
			throw new NullPointerException("HttpClient == null!");
		if (connectionTimeout != UNKNOWN_CONNECTION_TIMEOUT)
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), connectionTimeout);
		if (socketTimeout != UNKNOWN_SOCKET_TIMEOUT)
			HttpConnectionParams.setSoTimeout(httpClient.getParams(), socketTimeout);
	}

	@Override
	public void shutdown() {
		try {
			super.shutdown();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	@Override
	protected HttpPost getRequest(String url) throws IOException {
		return new HttpPost(url);
	}

	@Override
	protected Serializable transport(HttpPost request, String url,
			Serializable model) throws IOException {
		request.setEntity(new SerializeEntity(serializer, model));
		HttpResponse response = httpClient.execute(request);
		StatusLine status = response.getStatusLine();
		assertSuccessStatusCode(status.getStatusCode(), status.getReasonPhrase());
		HttpEntity entity = response.getEntity();
		try {
			return serializer.deserialize(Serializable.class, entity.getContent());
		} finally {
			entity.consumeContent();
		}
	}

	@Override
	protected void abort(HttpPost request) throws IOException {
		if (! request.isAborted())
			request.abort();
	}

	/**
	 * 序列化实体
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	private static class SerializeEntity extends AbstractHttpEntity {

		private StreamSerializer serializer;

		private Serializable object;

	    SerializeEntity(StreamSerializer serializer, Serializable object) throws IOException {
	        super();
	        this.serializer = serializer;
	        this.object = object;
	    }

	    public InputStream getContent() throws IOException, IllegalStateException {
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream out = new ObjectOutputStream(baos);
	        out.writeObject(object);
	        out.flush();
	        return new ByteArrayInputStream(baos.toByteArray());
	    }

	    public long getContentLength() {
	    	return -1;
	    }

	    public boolean isRepeatable() {
	        return true;
	    }

	    public boolean isStreaming() {
	        return true;
	    }

	    public void writeTo(OutputStream outstream) throws IOException {
	        if (outstream == null)
	            throw new IllegalArgumentException("Output stream may not be null");
	        serializer.serialize(object, outstream);
	    }

	}

}
