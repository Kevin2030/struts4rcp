package com.googlecode.struts4rcp.server.view.extjs;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class ComponentTag extends ExtTag {

	private String var;

	// protected 不输出
	protected String getVar() {
		if (isVarRequired() && var == null) {
			var = getUniqueClassName();
		}
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	private String key;

	// protected 不输出
	protected String getKey() {
		if (key == null)
			key = getDefaultKey();
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String def = "";

	public int doStartTag() throws JspException {
		try {
			beforeStartTag();
			if (getCurrentExtScope().getScopeType() != ExtScope.IN_ROOT)
				appendComma(pageContext.getOut());
			if (getVar() == null || getVar().trim().length() == 0) {
				pageContext.getOut().println(getKeyPrefix() + getComponentOpen());
				return EVAL_BODY_BUFFERED;
			} else {
				if (getCurrentExtScope().getScopeType() != ExtScope.IN_ROOT)
					pageContext.getOut().println(getKeyPrefix() + getVar());
				def = "var " + getVar() + " = " + getComponentOpen();
				return EVAL_BODY_BUFFERED;
			}
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	public int doEndTag() throws JspException {
		try {
			if (getVar() == null || getVar().trim().length() == 0) {
				pageContext.getOut().println(getComponentClose());
			} else {
				def += getComponentClose() + ";";
				defineComponent(def);
			}
			afterEndTag();
			return EVAL_PAGE;
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	protected final void appendComma(Appendable app) throws IOException {
		if (getCurrentExtScope().isFirst()) {
			getCurrentExtScope().setFirst(false);
		} else {
			app.append(",");
		}
	}

	private final String getKeyPrefix() {
		if (getCurrentExtScope().getScopeType() == ExtScope.IN_MAP) {
			String prefix = getKey();
			return (prefix == null ? "" : prefix + ": ");
		}
		return "";
	}

	private final String getComponentOpen() {
		String define = getComponentDefine();
		if (define != null)
			return "";
		String begin = isList() ? "[" : "{";
		String constructor = getComponentConstructor();
		String open;
		if (constructor == null || constructor.trim().length() == 0)
			open = begin;
		else
			open = constructor + "(" + filter(getComponentBefore()) + begin;
		pushExtScope(new ExtScope(getVar(), isList() ? ExtScope.IN_LIST : ExtScope.IN_MAP)); // 设置容器状态
		open = open + filter(getComponentBegin());
		return open;
	}

	private final String getComponentClose() {
		String define = getComponentDefine();
		if (define != null)
			return define;
		String body = "";
		if (bodyContent != null)
			body = filter(bodyContent.getString());
		String end = isList() ? "]" : "}";
		String name = getComponentConstructor();
		String close;
		if (name == null || name.trim().length() == 0)
			close = end;
		else
			close = end + filter(getComponentAfter()) + ")";
		close = filter(getComponentEnd()) + close;
		popExtScope();
		return body + close;
	}

	/**
	 * 标签开始前处理事情
	 */
	protected void beforeStartTag() {

	}

	/**
	 * 标签结束后处理事情
	 */
	protected void afterEndTag() {

	}

	/**
	 * 组件是否为List参数
	 *
	 * @return 是否为List参数
	 */
	protected boolean isList() {
		return false;
	}

	/**
	 * 匿名引用时的key.(子类override)
	 *
	 * @return 引用key, 输出如: key: new ComponentName({xxx: xxx, yyy: yyy}),
	 *         为null时不输出属性前缀
	 */
	protected String getDefaultKey() {
		if (isList())
			return getLowerClassName();
		return null;
	}

	/**
	 * 获取组件表示串
	 *
	 * @return 组件表示串, 直接输出
	 */
	protected String getComponentDefine() {
		return null;
	}

	/**
	 * 获取组件构造器
	 *
	 * @return 组件名称, 输出如: new ComponentName({xxx: xxx, yyy: yyy}), 为null时作为匿名组件,
	 *         输出如: {xxx: xxx, yyy: yyy}
	 */
	protected String getComponentConstructor() {
		String name = getComponentName();
		if (name == null)
			return null;
		return "new " + name;
	}

	/**
	 * 获取组件名称
	 *
	 * @return 组件名称, 输出如: new ComponentName({xxx: xxx, yyy: yyy}), 为null时作为匿名组件,
	 *         输出如: {xxx: xxx, yyy: yyy}
	 */
	protected String getComponentName() {
		if (!isList())
			return getExtClassName();
		return null;
	}

	/**
	 * 配置括号之前输出内容
	 *
	 * @return 配置括号之前输出内容
	 */
	protected String getComponentBefore() {
		return "";
	}

	/**
	 * 组件开始部分内容
	 *
	 * @return 开始部分内容
	 */
	protected String getComponentBegin() {
		if (!isList()) {
			try {
				StringBuffer buf = new StringBuffer();
				for (Map.Entry<String, ?> property : getAllProperties()
						.entrySet()) {
					String key = property.getKey();
					if (!key.matches("^[_|A-Z|a-z]([_|0-9|A-Z|a-z]*)$"))
						key = "\"" + key + "\"";
					Object value = property.getValue();
					if (value != null) {
						if (value instanceof String)
							value = "\"" + value + "\"";
						appendComma(buf);
						buf.append(key + ":" + value);
					}
				}
				return buf.toString();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return "";
	}

	/**
	 * 组件结束部分内容
	 *
	 * @return 结束部分内容
	 */
	protected String getComponentEnd() {
		return "";
	}

	/**
	 * 配置括号之后输出内容
	 *
	 * @return 配置括号之后输出内容
	 */
	protected String getComponentAfter() {
		return "";
	}

	/**
	 * 是否必需声明var变量
	 *
	 * @return 是否必需声明, 默认为false, 如果必需声明则override该函数返回true
	 */
	protected boolean isVarRequired() {
		return false;
	}

	protected final Map<String, ?> getAllProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		Method[] methods = this.getClass().getMethods();
		for (int i = 0, n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				if ((method.getModifiers() & Modifier.PUBLIC) == 1
						&& method.getDeclaringClass() != BodyTagSupport.class
						&& method.getDeclaringClass() != TagSupport.class
						&& method.getDeclaringClass() != Object.class
						&& (method.getParameterTypes() == null || method
								.getParameterTypes().length == 0)) {
					String methodName = method.getName();
					String property = null;
					if (methodName.startsWith("get")) {
						property = methodName.substring(3, 4).toLowerCase()
								+ methodName.substring(4);
					} else if (methodName.startsWith("is")) {
						property = methodName.substring(2, 3).toLowerCase()
								+ methodName.substring(3);
					}
					if (property != null) {
						Object value = method.invoke(this, new Object[0]);
						if (value != null) {
							if (value instanceof String) {
								if (method.isAnnotationPresent(Variable.class)
										|| isVariable((String)value))
									value = new VariableWrapper(value);
							}
							map.put(property, value);
						}
					}
				}
			} catch (Exception e) {
				// Ignore
			}
		}
		return map;
	}

	private final boolean isVariable(String value) {
		if (value.startsWith("[") && value.endsWith("]"))
			return true;
		if (value.startsWith("{") && value.endsWith("}"))
			return true;
		return false;
	}

	@Override
	public void release() {
		super.release();
		this.def = "";
	}

}
