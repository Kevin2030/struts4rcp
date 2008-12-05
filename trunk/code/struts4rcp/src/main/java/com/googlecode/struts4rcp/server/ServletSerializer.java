package com.googlecode.struts4rcp.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.util.serializer.Serializer;

/**
 * Servlet序列化接口
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public interface ServletSerializer extends Serializer<HttpServletRequest, HttpServletResponse> {

}
