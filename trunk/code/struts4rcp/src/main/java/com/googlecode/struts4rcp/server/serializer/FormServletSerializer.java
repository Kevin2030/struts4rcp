package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.server.ServletSerializer;
import com.googlecode.struts4rcp.util.BeanUtils;
import com.googlecode.struts4rcp.util.ClassUtils;
import com.googlecode.struts4rcp.util.ExceptionUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * 表单数据序列化器.
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class FormServletSerializer implements ServletSerializer {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 类元属性名
	 */
	public static final String CLASS_NAME = "className";

	public Serializable deserialize(HttpServletRequest request)
			throws IOException {
		String className = request.getParameter(CLASS_NAME);
		try {
			if (className != null && className.length() > 0) {
				Class<?> cls = ClassUtils.forName(className);
				if (! Serializable.class.isAssignableFrom(cls))
					throw new ClassCastException(cls.getName() + " unimplements interface " + Serializable.class.getName());
				Serializable model = (Serializable)cls.newInstance();
				for (Enumeration<?> names = request.getParameterNames(); names.hasMoreElements();) {
					String name = (String)names.nextElement();
					if (! CLASS_NAME.equals(name)) {
						String value = request.getParameter(name);
						try {
							BeanUtils.setProperty(model, name, value);
						} catch (Throwable e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
				return model;
			} else {
				HashMap<String, Object> model = new HashMap<String, Object>();
				for (Enumeration<?> names = request.getParameterNames(); names.hasMoreElements();) {
					String name = (String)names.nextElement();
					String value = request.getParameter(name);
					model.put(name, value);
				}
				return model;
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IOException(ExceptionUtils.getDetailMessage(e));
		}
	}

}
