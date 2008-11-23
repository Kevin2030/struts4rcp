package com.googlecode.struts4rcp.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ExceptionUtils {

	private ExceptionUtils() {}

	/**
	 * 获取异常详细信息
	 * @param e 异常类实例
	 * @return 详细信息
	 */
	public static String getDetailMessage(Throwable e) {
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));
		String msg = out.getBuffer().toString();
		return e.getClass().getName() + ": " + e.getMessage() + "\n" + msg;
	}

}
