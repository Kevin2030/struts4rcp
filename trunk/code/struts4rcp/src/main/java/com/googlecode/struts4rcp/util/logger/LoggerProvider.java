package com.googlecode.struts4rcp.util.logger;

/**
 * 日志输出器供给器
 *
 * @author liangfei0201@163.com
 *
 */
public interface LoggerProvider {

	/**
	 * 获取日志输出器
	 *
	 * @param key 分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public Logger getLogger(String key);

}
