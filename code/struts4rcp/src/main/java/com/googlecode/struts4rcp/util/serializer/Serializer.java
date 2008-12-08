package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.Serializable;

/**
 * 序列化接口
 *
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface Serializer<I, O> {

	/**
	 * 获取序列化内容类型
	 *
	 * @return 序列化内容类型
	 */
	public String getContentType();

	/**
	 * 将对象序列化成文件
	 *
	 * @param obj
	 *            可序列化对象
	 * @param out
	 *            序列化结果输出流
	 * @throws IOException
	 *             文件读取出错或序列化出错时抛出
	 */
	public void serialize(Serializable obj, O out) throws IOException;

	/**
	 * 读取流反序列化成对象
	 *
	 * @param cls
	 *            根类型
	 * @param in
	 *            序列化数据读入流
	 * @return 反序列化对象
	 * @throws IOException
	 *             文件读取出错或反序列化出错时抛出
	 */
	public Serializable deserialize(Class<? extends Serializable> cls,
			I in) throws IOException;

}
