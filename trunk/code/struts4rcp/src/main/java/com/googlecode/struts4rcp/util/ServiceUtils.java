package com.googlecode.struts4rcp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * 根据JDK6.0的自动发现机制实现.
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ServiceUtils {

	public static final String SERVICES_DIRECTORY = "META-INF/services/";

	/**
	 * 获取接口的服务类
	 * @param interfaceClass 接口
	 * @return 服务类
	 */
	public static String[] getServices(String interfaceClassName) {
		if (interfaceClassName == null)
			throw new NullPointerException("interfaceClassName == null");
		interfaceClassName = interfaceClassName.trim();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?> interfaceClass;
		try {
			interfaceClass = Class.forName(interfaceClassName, true, classLoader);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		ArrayList<String> clses = new ArrayList<String>();
		try {
			String fileName = SERVICES_DIRECTORY + interfaceClassName;
			Enumeration<URL> urls = classLoader.getResources(fileName);
			if (urls != null) {
				while (urls.hasMoreElements()) {
					URL url = urls.nextElement();
					try {
						InputStream in = url.openStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						String line = reader.readLine();
						while (line != null) {
							line = line.trim();
							if (line.length() > 0) {
								try {
									Class<?> cls = Class.forName(line, true, classLoader);
									if (interfaceClass.isAssignableFrom(cls)) {
										clses.add(cls.getName());
									}
								} catch (Throwable t) {
									// ignore
								}
							}
							line = reader.readLine();
						}
					} catch (Throwable t) {
						// ignore
					}
				}
			}
		} catch (Throwable t) {
			// ignore
		}
		return clses.toArray(new String[clses.size()]);
	}

	/**
	 * 获取接口的服务类
	 * @param <T>
	 * @param interfaceClass 接口
	 * @return 服务类
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T>[] getServices(Class<T> interfaceClass) {
		if (interfaceClass == null)
			throw new NullPointerException("interfaceClass == null");
		ArrayList<Class<T>> clses = new ArrayList<Class<T>>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			String fileName = SERVICES_DIRECTORY + interfaceClass.getName();
			Enumeration<URL> urls = classLoader.getResources(fileName);
			if (urls != null) {
				while (urls.hasMoreElements()) {
					URL url = urls.nextElement();
					try {
						InputStream in = url.openStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						String line = reader.readLine();
						while (line != null) {
							line = line.trim();
							if (line.length() > 0) {
								try {
									Class<?> cls = Class.forName(line, true, classLoader);
									if (interfaceClass.isAssignableFrom(cls)) {
										clses.add((Class<T>)cls);
									}
								} catch (Throwable t) {
									// ignore
								}
							}
							line = reader.readLine();
						}
					} catch (Throwable t) {
						// ignore
					}
				}
			}
		} catch (Throwable t) {
			// ignore
		}
		return clses.toArray(new Class[clses.size()]);
	}

}
