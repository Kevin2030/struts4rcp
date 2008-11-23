package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.util.serializer.TextSerializer;

/**
 * Servlet序列化接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ServletSerializer extends TextSerializer {

	/**
	 * 序列化对象
	 * @param obj 序列化对象
	 * @param response 响应信息
	 * @throws IOException 序列化失败时抛出
	 */
	void serialize(Serializable obj, HttpServletResponse response) throws IOException;

	/**
	 * 反序列化对象
	 * @param request 请求信息
	 * @return 序列化对象
	 * @throws IOException 反序列化失败时抛出
	 */
	Serializable deserialize(HttpServletRequest request) throws IOException;

}
