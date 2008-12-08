package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.googlecode.struts4rcp.util.IOUtils;

/**
 * 字节序列化器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class ByteArraySerializer implements StreamSerializer {

	public Serializable deserialize(Class<? extends Serializable> cls, InputStream in) throws IOException {
		return deserialize(cls, IOUtils.readToBytes(in));
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		byte[] bytes = serialize(obj);
		out.write(bytes);
		out.flush();
	}

	public abstract Serializable deserialize(Class<? extends Serializable> cls, byte[] in) throws IOException;

	public abstract byte[] serialize(Serializable obj) throws IOException;

}
