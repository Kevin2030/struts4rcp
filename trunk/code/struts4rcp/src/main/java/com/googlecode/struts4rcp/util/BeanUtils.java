package com.googlecode.struts4rcp.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * Bean属性工具类.<br/>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class BeanUtils {

	private BeanUtils(){}

	// 日志输出接口
	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 获取对象的属性值
	 *
	 * @param object 对象实例
	 * @param property 属性名
	 * @return 属性的值
	 * @throws NoSuchFieldException 未找到相应属性时抛出
	 */
	public static Object getProperty(Object object, String property) throws NoSuchPropertyException {
		if (object == null)
			return null;
		try {
			try {
				Class<?> clazz = object.getClass();
				property = property.trim();
				String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
				Method method;
				try {
					method = getGetterMethod(clazz, "get" + upper);
				} catch (NoSuchMethodException e1) {
					try {
						method = getGetterMethod(clazz, "is" + upper);
					} catch (NoSuchMethodException e2) {
						method = getGetterMethod(clazz, property);
					}
				}
				return method.invoke(object, new Object[0]);
			} catch (NoSuchMethodException e) {
				Field field = object.getClass().getField(property);
				field.setAccessible(true);
				return field.get(object);
			}
		} catch (SecurityException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalArgumentException e) {
			throw new NoSuchPropertyException(e);
		} catch (IllegalAccessException e) {
			throw new NoSuchPropertyException(e);
		} catch (InvocationTargetException e) {
			throw new NoSuchPropertyException(e);
		} catch (NoSuchFieldException e) {
			throw new NoSuchPropertyException(e);
		}
	}

	// 获取类的方法
	private static Method getGetterMethod(Class<?> clazz, String methodName) throws NoSuchMethodException, SecurityException {
		Method getter = clazz.getMethod(methodName, new Class[0]);
		if (! Modifier.isPublic(getter.getModifiers()))
			throw new NoSuchMethodException(clazz.getName() + "." + methodName + "() non public!");
		if (getter.getReturnType() == Void.class)
			throw new NoSuchMethodException(clazz.getName() + "." + methodName + "() return type is void!");
		if (! getter.isAccessible())
			getter.setAccessible(true);
		return getter;
	}

	/**
	 * 获取对象的所有属性值
	 * @param object 对象实例
	 * @return 属性集合
	 */
	public static Map<String, Object> getProperties(Object object) {
		if (object == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> cls = object.getClass();
		if (cls != null && Modifier.isPublic(cls.getModifiers())) {
			Method[] methods = cls.getMethods();
			for (int i = 0, n = methods.length; i < n; i ++) {
				try {
					Method method = methods[i];
					if (Modifier.isPublic(method.getModifiers())
							&& method.getDeclaringClass() != Object.class
							&& method.getReturnType() != Void.class
							&& method.getParameterTypes().length == 0) {
						String property = null;
						String methodName = method.getName();
						if (methodName.startsWith("get")) {
							property = methodName.substring(3);
						} else if (methodName.startsWith("is")) {
							property = methodName.substring(2);
						}
						if (property != null) {
							String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
							if (! method.isAccessible())
								method.setAccessible(true);
							map.put(lower, method.invoke(object, new Object[0]));
						}
					}
				} catch (Throwable t) {
					// ignore
				}
			}
		}
		return map;
	}

	/**
	 * 获取对象的所有属性值
	 * @param cls 类元
	 * @return 属性集合
	 */
	public static Collection<String> getProperties(Class<?> cls) {
		Collection<String> names = new ArrayList<String>();
		if (cls != null && Modifier.isPublic(cls.getModifiers())) {
			Method[] methods = cls.getMethods();
			for (int i = 0, n = methods.length; i < n; i ++) {
				try {
					Method method = methods[i];
					if (Modifier.isPublic(method.getModifiers())
							&& method.getDeclaringClass() != Object.class
							&& method.getReturnType() != Void.class
							&& method.getParameterTypes().length == 0) {
						String property = null;
						String methodName = method.getName();
						if (methodName.startsWith("get")) {
							property = methodName.substring(3);
						} else if (methodName.startsWith("is")) {
							property = methodName.substring(2);
						}
						if (property != null) {
							String lower = property.substring(0, 1).toLowerCase() + property.substring(1);
							names.add(lower);
						}
					}
				} catch (Throwable t) {
					// ignore
				}
			}
		}
		return names;
	}

	/**
	 * 设置对象的多个属性值
	 *
	 * @param object 对象实例
	 * @param properties 属性集合
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setProperties(Object object, Map<String, Object> properties) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null || properties == null || properties.isEmpty())
			return;
		for (Iterator<Map.Entry<String, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, Object> entry = iterator.next();
			String property = entry.getKey();
			if ("class".equals(property))
				continue;
			Object value = entry.getValue();
			try {
				setProperty(object, property, value);
			} catch (Throwable e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 设置对象的属性值
	 *
	 * @param object 对象实例
	 * @param property 属性名
	 * @param value 待设置属性的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setProperty(Object object, String property, Object value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (object == null || property == null)
			return;
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		String methodName = "set" + upper;
		Method setter = getSetterMethod(object.getClass(), methodName, value == null ? null : value.getClass());
		if (! setter.isAccessible())
			setter.setAccessible(true);
		value = convertType(value, setter.getParameterTypes()[0]);
		setter.invoke(object, new Object[]{value});
	}

	private static Method getSetterMethod(Class<?> clazz, String methodName, Class<?> paramClass) throws NoSuchMethodException {
		if (paramClass != null) {
			try {
				return clazz.getMethod(methodName, new Class<?>[]{paramClass});
			} catch (NoSuchMethodException e) {
				// ignore
			}
		}
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i ++) {
			Method method = methods[i];
			Class<?>[] paramTypes = method.getParameterTypes();
			if (Modifier.isPublic(method.getModifiers())
					&& method.getName().equals(methodName)
					&& paramTypes.length == 1) {
				return method;
			}
		}
		throw new NoSuchMethodException("No such method: " + clazz.getName() + "." + methodName + "()!");
	}

	private static Object convertType(Object value, Class<?> dest) {
		if (value == null)
			return null;
		Class<?> src = value.getClass();
		if (src == dest)
			return value;
		if (String.class == dest) {
			return String.valueOf(value);
		}
		if (isPrimitive(value.getClass())) {
			value = String.valueOf(value);
			src = String.class;
		}
		if (String.class == src) {
			String str = (String)value;
			if (dest == boolean.class || dest == Boolean.class) {
				return Boolean.valueOf(str);
			} else if (dest == char.class || dest == Character.class) {
				return str.length() > 0 ? str.charAt(0) : '\0';
			} else if (dest == byte.class || dest == Byte.class) {
				return Byte.valueOf(str);
			} else if (dest == short.class || dest == Short.class) {
				return Short.valueOf(str);
			} else if (dest == int.class || dest == Integer.class) {
				return Integer.valueOf(str);
			} else if (dest == long.class || dest == Long.class) {
				return Long.valueOf(str);
			} else if (dest == float.class || dest == Float.class) {
				return Float.valueOf(str);
			} else if (dest == double.class || dest == Double.class) {
				return Double.valueOf(str);
			} else if (Class.class == dest) {
				try {
					return ClassUtils.forName(str);
				} catch (ClassNotFoundException e) {
					return value;
				}
			} else if (Date.class == dest) {
				return parseDate(str);
			} else if (java.sql.Date.class == dest) {
				return new java.sql.Date(parseDate(str).getTime());
			} else if (java.sql.Time.class == dest) {
				return new java.sql.Time(parseDate(str).getTime());
			} else if (java.sql.Timestamp.class == dest) {
				return new java.sql.Timestamp(parseDate(str).getTime());
			}
		}
		return value;
	}

	private static boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive()
			|| cls == Boolean.class || cls == Character.class
			|| cls == Byte.class || cls == Short.class
			|| cls == Integer.class || cls == Long.class
			|| cls == Float.class || cls == Double.class;
	}

	private static Date parseDate(String source) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(source);
		} catch (ParseException e) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				return dateFormat.parse(source);
			} catch (ParseException e1) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					return dateFormat.parse(source);
				} catch (ParseException e2) {
					return null;
				}
			}
		}
	}

}
