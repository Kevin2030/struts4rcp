package com.googlecode.struts4rcp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * 配置文件加载工具类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class PropertiesUtils {

	private PropertiesUtils() {}

	// 日志输出接口
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	/**
	 * 尝试从多种路径加载配置文件
	 * @param path 配置文件路径
	 * @return 配置文件信息
	 * @throws IOException 配置文件不存在时抛出
	 */
	public static Properties load(String path) throws IOException {
		try {
			return PropertiesUtils.loadFromFileSystem(path);
		} catch (IOException e) {
			return PropertiesUtils.loadFromClassPath(path);
		}
	}

	/**
	 * 尝试从多种路径加载指定编码的配置文件
	 * @param path 配置文件路径
	 * @param encoding 配置文件加载编码
	 * @return 配置文件信息
	 * @throws IOException 配置文件不存在时抛出
	 */
	public static Properties load(String path, String encoding) throws IOException {
		try {
			return PropertiesUtils.loadFromClassPath(path, encoding);
		} catch (IOException e) {
			return PropertiesUtils.loadFromFileSystem(path, encoding);
		}
	}

	/**
	 * 从类路径加载配置文件
	 * @param path 配置文件路径
	 * @return 配置文件信息
	 * @throws IOException 配置文件不存在时抛出
	 */
	public static Properties loadFromClassPath(String path) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (in == null)
			throw new FileNotFoundException("加载配置失败，配置路径：" + path);
		Properties properties = new Properties();
		properties.load(in);
		return properties;
	}

	/**
	 * 从文件系统加载配置文件
	 * @param path 配置文件路径
	 * @return 配置文件信息
	 * @throws IOException 配置文件不存在时抛出
	 */
	public static Properties loadFromFileSystem(String path) throws IOException {
		InputStream in = new FileInputStream(new File(path));
		Properties properties = new Properties();
		properties.load(in);
		return properties;
	}

	/**
	 * 从类路径加载指定编码的配置文件
	 * @param path 配置文件路径
	 * @param encoding 配置文件加载编码
	 * @return 配置文件信息
	 * @throws IOException 配置文件不存在时抛出
	 */
	public static Properties loadFromClassPath(String path, String encoding) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (in == null)
			throw new FileNotFoundException("加载配置失败，配置路径：" + path);
		EncodingProperties properties = new EncodingProperties();
		properties.load(in, encoding);
		return properties;
	}

	/**
	 * 从文件系统加载指定编码的配置文件
	 * @param path 配置文件路径
	 * @param encoding 配置文件加载编码
	 * @return 配置文件信息
	 * @throws IOException 配置文件不存在时抛出
	 */
	public static Properties loadFromFileSystem(String path, String encoding) throws IOException {
		InputStream in = new FileInputStream(new File(path));
		EncodingProperties properties = new EncodingProperties();
		properties.load(in, encoding);
		return properties;
	}

	public static String getStringProperty(Properties config, String key, String defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		return value;
	}

	public static boolean getBooleanProperty(Properties config, String key, boolean defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "1".equals(value);
	}

	public static char getCharProperty(Properties config, String key, char defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		return value.trim().charAt(0);
	}

	public static byte getByteProperty(Properties config, String key, byte defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return Byte.parseByte(value);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static short getShortProperty(Properties config, String key, short defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return Short.parseShort(value);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static int getIntProperty(Properties config, String key, int defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static long getLongProperty(Properties config, String key, long defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static float getFloatProperty(Properties config, String key, float defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static double getDoubleProperty(Properties config, String key, double defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static Date getDateProperty(Properties config, String key, Date defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static Date getDateTimeProperty(Properties config, String key, Date defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static Date getTimeProperty(Properties config, String key, Date defaultValue) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultValue;
		value = value.trim();
		if (value.length() == 0)
			return defaultValue;
		try {
			return new SimpleDateFormat("HH:mm:ss").parse(value);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
	}

	public static <T> Class<? extends T> getClassProperty(Properties config, String key, Class<T> baseClass, Class<? extends T> defaultClass) {
		String value = config.getProperty(key);
		if (value == null)
			return defaultClass;
		value = value.trim();
		if (value.length() == 0)
			return defaultClass;
		try {
			return ClassUtils.forName(value).asSubclass(baseClass);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			return defaultClass;
		}
	}

	public static <T> T getInstanceProperty(Properties config, String key, Class<T> baseClass, Class<? extends T> defaultClass) {
		try {
			String value = config.getProperty(key);
			if (value == null)
				return defaultClass.newInstance();
			value = value.trim();
			if (value.length() == 0)
				return defaultClass.newInstance();
			try {
				return ClassUtils.forName(value).asSubclass(baseClass).newInstance();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return defaultClass.newInstance();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static <T> List<T> getInstancesProperty(Properties config, String key, Class<T> baseClass) {
		List<T> list = new ArrayList<T>();
		String value = config.getProperty(key);
		if (value == null)
			return list;
		value = value.trim();
		if (value.length() == 0)
			return list;
		String[] classNames = value.split("\\,");
		for (String className : classNames) {
			try {
				list.add(ClassUtils.forName(className).asSubclass(baseClass).newInstance());
			} catch (Throwable e) {
				logger.error(e.getMessage(), e);
			}
		}
		return list;
	}

}
