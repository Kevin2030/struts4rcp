package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

/**
 * 序列化接口适配，便于实现
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class AbstractTextSerializer extends AbstractSerializer implements TextSerializer {

	public String getContentEncoding() {
		return "UTF-8";
	}

	public void serialize(Serializable obj, OutputStream out) throws IOException {
		serialize(obj, new OutputStreamWriter(out, getContentEncoding()));
	}

	public Serializable deserialize(InputStream in) throws IOException {
		return deserialize(new InputStreamReader(in, getContentEncoding()));
	}

	public String serializeAsString(Serializable obj) throws IOException {
		return new String(serialize(obj), getContentEncoding());
	}

	public Serializable deserialize(String data) throws IOException {
		return deserialize(data == null ? null : data.getBytes(getContentEncoding()));
	}

}
