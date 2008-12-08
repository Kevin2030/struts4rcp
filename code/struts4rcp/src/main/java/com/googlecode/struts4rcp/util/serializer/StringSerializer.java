package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import com.googlecode.struts4rcp.util.IOUtils;

/**
 * 字符串序列化器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class StringSerializer implements TextSerializer {

	public String getContentEncoding() {
		return "UTF-8";
	}

	public Serializable deserialize(Class<? extends Serializable> cls, Reader in) throws IOException {
		return deserialize(cls, IOUtils.readToString(in));
	}

	public void serialize(Serializable obj, Writer out) throws IOException {
		String result = serialize(obj);
		out.write(result);
		out.flush();
	}

	public abstract Serializable deserialize(Class<? extends Serializable> cls, String in) throws IOException;

	public abstract String serialize(Serializable obj) throws IOException;

}
