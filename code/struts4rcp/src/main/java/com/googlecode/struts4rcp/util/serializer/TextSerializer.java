package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/**
 * 文本序列化器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 *
 */
public interface TextSerializer extends Serializer {

	/**
	 * 类元属性名
	 */
	public static final String CLASS_NAME = "className";

	/**
	 * 获取内容编码
	 * @return 内容编码
	 */
	public String getContentEncoding();

	/**
	 * 将对象序列化成数据
	 * @param obj
	 *            可序列化对象
	 * @return 序列化数据字符串
	 * @throws IOException
	 *             序列化出错时抛出
	 */
	public String serializeAsString(Serializable obj) throws IOException;

	/**
	 * 将数据反序列化成对象
	 * @param data
	 *            序列化数据
	 * @return 反序列化对象
	 * @throws IOException
	 *             反序列化出错时抛出
	 */
	public Serializable deserialize(String data) throws IOException;

	/**
	 * 将对象序列化成文件
	 * @param obj
	 *            可序列化对象
	 * @param writer
	 *            序列化结果写入器
	 * @throws IOException
	 *             文件读取出错或序列化出错时抛出
	 */
	public void serialize(Serializable obj, Writer writer)
			throws IOException;

	/**
	 * 读取流反序列化成对象
	 * @param reader
	 *            序列化数据读取器
	 * @return 反序列化对象
	 * @throws IOException
	 *             文件读取出错或反序列化出错时抛出
	 */
	public Serializable deserialize(Reader reader) throws IOException;

}
