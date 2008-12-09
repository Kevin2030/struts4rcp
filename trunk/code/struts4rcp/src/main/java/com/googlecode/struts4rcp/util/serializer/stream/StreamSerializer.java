package com.googlecode.struts4rcp.util.serializer.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.googlecode.struts4rcp.util.serializer.Serializer;

public interface StreamSerializer extends Serializer<InputStream, OutputStream> {

	/**
	 * 将对象序列化成文件
	 * @param obj
	 *            可序列化对象
	 * @param out
	 *            序列化结果输出流
	 * @throws IOException
	 *             文件读取出错或序列化出错时抛出
	 */
	public void serialize(Serializable obj, OutputStream out) throws IOException;

	/**
	 * 读取流反序列化成对象
	 * @param in
	 *            序列化数据读入流
	 * @return 反序列化对象
	 * @throws IOException
	 *             文件读取出错或反序列化出错时抛出
	 */
	public Serializable deserialize(Class<? extends Serializable> baseClass, InputStream in) throws IOException;

}
