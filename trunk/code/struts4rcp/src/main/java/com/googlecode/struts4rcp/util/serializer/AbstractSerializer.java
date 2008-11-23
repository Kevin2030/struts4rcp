package com.googlecode.struts4rcp.util.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * 序列化接口适配，便于实现
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class AbstractSerializer implements Serializer {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public byte[] serialize(Serializable obj) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		serialize(obj, bo);
		return bo.toByteArray();
	}

	public Serializable deserialize(byte[] data) throws IOException {
		return deserialize(new ByteArrayInputStream(data));
	}

	public void serialize(Serializable obj, File file) throws IOException {
		serialize(obj, new FileOutputStream(file));
	}

	public Serializable deserialize(File file) throws IOException {
		return deserialize(new FileInputStream(file));
	}

}
