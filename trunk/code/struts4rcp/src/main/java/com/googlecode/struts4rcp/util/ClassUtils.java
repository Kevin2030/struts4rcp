package com.googlecode.struts4rcp.util;

import java.lang.reflect.Method;

/**
 * Class工具类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public final class ClassUtils {

	private ClassUtils(){}

	/**
	 * 通过类名反射得到类元.
	 * 使用的类名应该和<code>java.lang.Class#getName()</code>一致.
	 * @see java.lang.Class#getName()
	 * @param className 类名
	 * @return 类元
	 * @throws ClassNotFoundException 类不存在时抛出
	 */
	public static Class<?> forName(String className) throws ClassNotFoundException {
		if (className == null)
			throw new NullPointerException("className == null");
		return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
	}

	/**
	 * 通过类名反射得到类元.
	 * 使用的类名应该和<code>java.lang.Class#getName()</code>一致.
	 * @see java.lang.Class#getName()
	 * @param className 类名
	 * @param classLoader 类加载器
	 * @return 类元
	 * @throws ClassNotFoundException 类不存在时抛出
	 */
	public static Class<?> forName(String className, ClassLoader classLoader) throws ClassNotFoundException {
		if (className == null)
			throw new NullPointerException("className == null");
		if (classLoader == null)
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		return Class.forName(className, true, classLoader);
	}

	public static Method getMethod(Class<?> cls, String methodName) throws NoSuchMethodException {
		if (cls == null)
			throw new NullPointerException("class == null");
		if (methodName == null)
			throw new NullPointerException("methodName == null");
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (methodName.equals(method.getName())) {
				return method;
			}
		}
		throw new NoSuchMethodException("No such method:" + methodName + " from class: " + cls.getName());
	}

	public static Object getInstance(String value) {
		if (value == null)
			return null;
		try {
			return forName(value).newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T getInstance(String value, Class<T> baseClass, Class<T> defaultClass) {
		try {
			if (value == null)
				return defaultClass.newInstance();
			value = value.trim();
			if (value.length() == 0)
				return defaultClass.newInstance();
			try {
				return forName(value).asSubclass(baseClass).newInstance();
			} catch (Exception e) {
				return defaultClass.newInstance();
			}
		} catch (Exception e) {
			return null;
		}
	}

}
