package com.googlecode.struts4rcp.util.serializer;

import java.io.Reader;
import java.io.Writer;

/**
 * 文本序列化器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 *
 */
public interface TextSerializer extends Serializer<Reader, Writer> {

	/**
	 * 获取内容编码
	 * @return 内容编码
	 */
	public String getContentEncoding();

}
