package com.googlecode.struts4rcp.util.serializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 序列化接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Serializer {

	/**
	 * 获取序列化内容类型
	 * @return 序列化内容类型
	 */
	public String getContentType();

	/**
	 * 将对象序列化成数据
	 * @param obj
	 *            可序列化对象
	 * @return 序列化数据
	 * @throws IOException
	 *             序列化出错时抛出
	 */
	public byte[] serialize(Serializable obj) throws IOException;

	/**
	 * 将数据反序列化成对象
	 * @param data
	 *            序列化数据
	 * @return 反序列化对象
	 * @throws IOException
	 *             反序列化出错时抛出
	 */
	public Serializable deserialize(byte[] data) throws IOException;

	/**
	 * 将对象序列化到指定文件
	 * @param obj
	 *            可序列化对象
	 * @param file
	 *            序列化目标文件
	 * @throws IOException
	 *             文件读取出错或序列化出错时抛出
	 */
	public void serialize(Serializable obj, File file) throws IOException;

	/**
	 * 将文件反序列化成对象
	 * @param file
	 *            序列化文件
	 * @return 反序列化对象
	 * @throws IOException
	 *             文件读取出错或反序列化出错时抛出
	 */
	public Serializable deserialize(File file) throws IOException;

	/**
	 * 将对象序列化成文件
	 * @param obj
	 *            可序列化对象
	 * @param out
	 *            序列化结果输出流
	 * @throws IOException
	 *             文件读取出错或序列化出错时抛出
	 */
	public void serialize(Serializable obj, OutputStream out)
			throws IOException;

	/**
	 * 读取流反序列化成对象
	 * @param in
	 *            序列化数据读入流
	 * @return 反序列化对象
	 * @throws IOException
	 *             文件读取出错或反序列化出错时抛出
	 */
	public Serializable deserialize(InputStream in) throws IOException;

}
