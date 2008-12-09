package com.googlecode.struts4rcp.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;
import com.googlecode.struts4rcp.util.serializer.text.JsonTextSerializer;

public final class JsonUtils {

	private JsonUtils() {}

	// 日志输出接口
	private static final Logger logger = LoggerFactory.getLogger(JsonTextSerializer.class);

	/**
	 * 类元属性名
	 */
	public static final String CLASS_PROPERTY_NAME = "class";

	public static Object fromJson(String json) {
		return fromJson(json, Map.class); // 缺省使用Map
	}

	/**
	 * 将JSON字符串解析成对象(或数组)。
	 * <p>注意：
	 * <br>1. json对象中的className属性必需是类名的全路径名。
	 * <br>2. 待转换对象类必需有无参构造函数，以及符合JavaBean规范的setter方法。
	 * <br>3. 如果没有class属性，将使用<code>java.util.HashMap</code>作为缺省实现，如：
	 * <br>   {class: "com.xxx.User", username: "xxx", password: "xxx"} 将转换成com.xxx.User对象实例
	 * <br>   {username: "xxx", password: "xxx"} 将转换成一个<code>java.util.HashMap</code>实例
	 * @see java.util.HashMap
	 * @param json JSON字符串
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static Object fromJson(String json, Class<?> cls) {
		if (json == null || json.length() == 0)
			return null;
		json = json.trim();
		if ("null".equals(json))
			return null;
		if ("true".equals(json))
			return Boolean.TRUE;
		if ("false".equals(json))
			return Boolean.FALSE;
		if (json.length() >= 2 && json.trim().startsWith("\"") && json.trim().endsWith("\""))
			return json.substring(1, json.length() - 1);
		if (json.length() >= 2 && json.trim().startsWith("\'") && json.trim().endsWith("\'"))
			return json.substring(1, json.length() - 1);
		if (json.trim().startsWith("[") && json.trim().endsWith("]"))
			return jsonToArray(createJsonArray(json));
		if (json.trim().startsWith("{") && json.trim().endsWith("}"))
			return jsonToObject(createJsonObject(json));
		Map<String, Object> map = (Map)jsonToObject(createJsonObject("{tmp:" + json + "}"));
		return map.get("tmp");
	}

	/**
	 * 将对象(或数组或集合)转换成JSON字符串。
	 * <p>注意：
	 * <br>1. 如果对象为Map集合，则读取所有键值对，否则按JavaBean规范的getter方法转换属性。
	 * <br>2. 如果对象的属性出现循环引用，将以null代替。
	 * <br>3. 如果对象的是属性为long类型，将转换为字符串，因为JavaScript不支持long类型(16位以上的数字)。
	 * @param bean 对象
	 * @return JSON字符串
	 */
	public static String toJson(Object bean) {
		StringBuffer buf = new StringBuffer();
		appendObject(bean, buf, new Stack<Object>());
		return buf.toString();
	}

	private static JSONObject createJsonObject(String json) {
		try {
			return new JSONObject(json);
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static JSONArray createJsonArray(String json) {
		try {
			return new JSONArray(json);
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private static Object jsonToObject(JSONObject json) {
		if (json == null)
			return null;
		Object classProperty = getJsonProperty(json, CLASS_PROPERTY_NAME);
		String className = classProperty == null ? null : String.valueOf(classProperty);
		Object bean;
		if (className != null && className.length() > 0) {
			bean = newInstance(className);
		} else { // 没有class属性采用HashMap作为缺省实现
			bean = new HashMap<String, Object>();
		}
		for (Iterator<String> iterator = json.keys(); iterator.hasNext();) {
			String key = (String)iterator.next();
			if (! CLASS_PROPERTY_NAME.equals(key)) {
				Object value = getJsonProperty(json, key);
				if (value instanceof JSONObject) {
					JSONObject obj = (JSONObject) value;
					setJsonProperty(bean, key, jsonToObject(obj));
				} else if (value instanceof JSONArray) {
					JSONArray arr = (JSONArray) value;
					setJsonProperty(bean, key, jsonToArray(arr));
				} else {
					setJsonProperty(bean, key, value);
				}
			}
		}
		return bean;
	}

	private static Object[] jsonToArray(JSONArray json) {
		if (json == null)
			return null;
		List<Object> list = new ArrayList<Object>();
		for (int i = 0, n = json.length(); i < n; i ++) {
			Object value = getJsonItem(json, i);
			if (value instanceof JSONObject) {
				JSONObject obj = (JSONObject) value;
				list.add(jsonToObject(obj));
			} else if (value instanceof JSONArray) {
				JSONArray arr = (JSONArray) value;
				list.add(jsonToArray(arr));
			} else {
				list.add(value);
			}
		}
		return list.toArray();
	}

	private static Object getJsonProperty(JSONObject json, String key) {
		try {
			return json.get(key);
		} catch (JSONException e) {
			return null;
		}
	}

	private static Object getJsonItem(JSONArray json, int index) {
		try {
			return json.get(index);
		} catch (JSONException e) {
			return null;
		}
	}

	private static Object newInstance(String className) {
		try {
			Class<?> cls = ClassUtils.forName(className);
			Object obj = cls.newInstance();
			return obj;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@SuppressWarnings("unchecked")
	private static void setJsonProperty(Object bean, String key, Object value) {
		if (bean instanceof Map) {
			((Map)bean).put(key, value);
		} else {
			try {
				BeanUtils.setProperty(bean, key, value);
			} catch (Throwable e) { // 不能注入则忽略
				logger.warn(e.getMessage(), e);
			}
		}
	}

	// 判断是否循环引用
	private static boolean isCycleReferenced(Object object, Stack<Object> ref) {
		if (! ref.isEmpty()) {
			for (Iterator<Object> i = ref.iterator(); i.hasNext();) {
				if (i.next() == object)
					return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void appendObject(Object object, StringBuffer buf, Stack<Object> ref) {
		if (object == null) {
			buf.append("null");
		} else if ( object.getClass() == boolean.class
				|| object.getClass() == byte.class
				|| object.getClass() == short.class
				|| object.getClass() == int.class
				|| object.getClass() == long.class
				|| object.getClass() == float.class
				|| object.getClass() == double.class
				|| object.getClass() == Boolean.class
				|| object.getClass() == Byte.class
				|| object.getClass() == Short.class
				|| object.getClass() == Integer.class
				|| object.getClass() == Long.class
				|| object.getClass() == Float.class
				|| object.getClass() == Double.class) {
			buf.append(String.valueOf(object));
		} else if (object.getClass() == char.class
				|| object.getClass() == Character.class) {
			buf.append("\'" + String.valueOf(object) + "\'");
		} else if (object instanceof CharSequence) {
			buf.append("\"" + escapeJavaScript(object.toString()) + "\"");
		} else if (object instanceof Date) {
			buf.append("new Date(" + ((Date)object).getTime() + ")");
		} else if (isCycleReferenced(object, ref)){
			buf.append("null"); // 如果循环引用，则用null代替
		} else {
			ref.push(object); // 压入引用
			if (object.getClass().isArray()) {
				appendArray(object, buf, ref);
			} else if (object instanceof Collection) {
				appendCollection((Collection<Object>)object, buf, ref);
			} else if (object instanceof Map) {
				appendMap(object.getClass(), (Map<String, Object>)object, buf, ref);
			} else {
				appendMap(object.getClass(), BeanUtils.getProperties(object), buf, ref);
			}
			ref.pop(); // 弹出引用
		}
	}

	// 添加数组，包括基本类型数组
	private static void appendArray(Object array, StringBuffer buf, Stack<Object> ref) {
		buf.append("[");
		boolean isFirst = true;
		for (int i = 0, n = Array.getLength(array); i < n; i ++) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(Array.get(array, i), buf, ref);
		}
		buf.append("]");
	}

	// 添加集合
	private static void appendCollection(Collection<Object> collection, StringBuffer buf, Stack<Object> ref) {
		buf.append("[");
		boolean isFirst = true;
		for (Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
			if (isFirst)
				isFirst = false;
			else
				buf.append(",");
			appendObject(iterator.next(), buf, ref);
		}
		buf.append("]");
	}

	// 添加Map
	private static void appendMap(Class<?> cls, Map<String, Object> properties, StringBuffer buf, Stack<Object> ref) {
		buf.append("{\"");
		buf.append(CLASS_PROPERTY_NAME);
		buf.append("\":\"");
		buf.append(cls.getName());
		buf.append("\"");
		for (Iterator<Map.Entry<String, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)iterator.next();
			if (! CLASS_PROPERTY_NAME.equals(entry.getKey())) {
				buf.append(",\"");
				buf.append(escapeJavaScript(entry.getKey()));
				buf.append("\":");
				appendObject(entry.getValue(), buf, ref);
			}
		}
		buf.append("}");
	}

	// 过滤名称
    private static String escapeJavaScript(String input) {
        if (input == null)
            return null;
        StringBuffer filtered = new StringBuffer(input.length());
        char prevChar = '\u0000';
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '"') {
                filtered.append("\\\"");
            } else if (c == '\'') {
                filtered.append("\\'");
            } else if (c == '\\') {
                filtered.append("\\\\");
            } else if (c == '/') {
                filtered.append("\\/");
            } else if (c == '\t') {
                filtered.append("\\t");
            } else if (c == '\n') {
                if (prevChar != '\r')
                    filtered.append("\\n");
            } else if (c == '\r') {
                filtered.append("\\n");
            } else if (c == '\f') {
                filtered.append("\\f");
            } else {
                filtered.append(c);
            }
            prevChar = c;
        }
        return filtered.toString();
    }

}
