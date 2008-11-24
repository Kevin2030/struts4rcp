package com.googlecode.struts4rcp.server.view.extjs;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EXT标签基类
 *
 * @author 梁飞
 *
 */
public abstract class ExtTag extends BodyTagSupport {

	/**
	 * 日志输出端
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 获取当前标签的小写类名, 如: formPanel
	 *
	 * @return 小写类名
	 */
	protected final String getLowerClassName() {
		String name = this.getClass().getSimpleName();
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	/**
	 * 获取当前标签的EXT定义名, 如: Ext.FormPanel
	 *
	 * @return EXT定义名
	 */
	protected final String getExtClassName() {
		String name = this.getClass().getName().substring(
				ExtTag.class.getPackage().getName().length() + 1);
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	private static final String UNIQUE_NAME_KEY = "____EXT_UNIQUE_NAME_KEY_____";

	/**
	 * 获取当前标签的唯一类名, 用于实例命名
	 *
	 * @return 唯一类名/实例名
	 */
	protected final String getUniqueClassName() {
		Integer unique = (Integer) super.pageContext.getAttribute(
				UNIQUE_NAME_KEY, PageContext.PAGE_SCOPE);
		if (unique == null)
			unique = Integer.valueOf(0);
		unique = Integer.valueOf(unique.intValue());
		unique ++;
		super.pageContext.setAttribute(UNIQUE_NAME_KEY, unique, PageContext.PAGE_SCOPE);
		return getLowerClassName() + unique;
	}

	// 区域信息
	private static final String SCOPE_STACK_KEY = "____EXT_SCOPE_STACK_KEY_____";

	@SuppressWarnings("unchecked")
	private final Stack<ExtScope> getExtScopeStack() {
		Stack<ExtScope> stack = (Stack<ExtScope>) super.pageContext
				.getAttribute(SCOPE_STACK_KEY, PageContext.PAGE_SCOPE);
		if (stack == null) {
			stack = new Stack<ExtScope>();
			super.pageContext.setAttribute(SCOPE_STACK_KEY, stack,
					PageContext.PAGE_SCOPE);
		}
		return stack;
	}

	protected final ExtScope getCurrentExtScope() {
		if (getExtScopeStack().empty())
			pushExtScope(new ExtScope(null, ExtScope.IN_ROOT));
		return getExtScopeStack().peek();
	}

	protected final void pushExtScope(ExtScope extScope) {
		getExtScopeStack().push(extScope);
	}

	protected final void popExtScope() {
		getExtScopeStack().pop();
	}

	private static final String COMPONENT_BUFFER_KEY = "____EXT_COMPONENT_BUFFER_KEY_____";

	@SuppressWarnings("unchecked")
	private final StringBuilder getComponentBuffer() {
		StringBuilder buffer = (StringBuilder) super.pageContext.getAttribute(
				COMPONENT_BUFFER_KEY, PageContext.PAGE_SCOPE);
		if (buffer == null) {
			buffer = new StringBuilder();
			super.pageContext.setAttribute(COMPONENT_BUFFER_KEY, buffer,
					PageContext.PAGE_SCOPE);
		}
		return buffer;
	}

	protected final void defineComponent(String component) {
		getComponentBuffer().append("\n\n" + component);
	}

	protected final String getDefinedComponents() {
		return getComponentBuffer().toString();
	}

	private static final String STYLE_BUFFER_KEY = "____EXT_STYLE_BUFFER_KEY_____";

	@SuppressWarnings("unchecked")
	private final StringBuilder getStyleBuffer() {
		StringBuilder buffer = (StringBuilder) super.pageContext.getAttribute(
				STYLE_BUFFER_KEY, PageContext.PAGE_SCOPE);
		if (buffer == null) {
			buffer = new StringBuilder();
			super.pageContext.setAttribute(STYLE_BUFFER_KEY, buffer,
					PageContext.PAGE_SCOPE);
		}
		return buffer;
	}

	protected final void defineStyle(String style) {
		getStyleBuffer().append("\n" + style);
	}

	protected final String getDefinedStyles() {
		return getStyleBuffer().toString();
	}

	private static final String BEFORE_SCRIPT_BUFFER_KEY = "____EXT_BEFORE_SCRIPT_BUFFER_KEY_____";

	@SuppressWarnings("unchecked")
	private final StringBuilder getBeforeScriptBuffer() {
		StringBuilder buffer = (StringBuilder) super.pageContext.getAttribute(
				BEFORE_SCRIPT_BUFFER_KEY, PageContext.PAGE_SCOPE);
		if (buffer == null) {
			buffer = new StringBuilder();
			super.pageContext.setAttribute(BEFORE_SCRIPT_BUFFER_KEY, buffer,
					PageContext.PAGE_SCOPE);
		}
		return buffer;
	}

	protected final void defineBeforeScript(String script) {
		getBeforeScriptBuffer().append("\n" + script);
	}

	protected final String getDefinedBeforeScripts() {
		return getBeforeScriptBuffer().toString();
	}

	private static final String AFTER_SCRIPT_BUFFER_KEY = "____EXT_AFTER_SCRIPT_BUFFER_KEY_____";

	@SuppressWarnings("unchecked")
	private final StringBuilder getAfterScriptBuffer() {
		StringBuilder buffer = (StringBuilder) super.pageContext.getAttribute(
				AFTER_SCRIPT_BUFFER_KEY, PageContext.PAGE_SCOPE);
		if (buffer == null) {
			buffer = new StringBuilder();
			super.pageContext.setAttribute(AFTER_SCRIPT_BUFFER_KEY, buffer,
					PageContext.PAGE_SCOPE);
		}
		return buffer;
	}

	protected final void defineAfterScript(String script) {
		getAfterScriptBuffer().append("\n" + script);
	}

	protected final String getDefinedAfterScripts() {
		return getAfterScriptBuffer().toString();
	}

	public void release() {
		super.release();
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
					if (methodName.startsWith("set")) {
						method.invoke(this, new Object[] { null });
					}
				}
			} catch (Exception e) {
				// Ignore
			}
		}
	}

	protected final String filter(String str) {
		if (str == null || str.trim().length() == 0)
			return "";
		return str;
	}

	private static final String STATUS_KEY = "____STATUS_KEY_____";

	@SuppressWarnings("unchecked")
	private final Map<String, String> getStatus() {
		Map<String, String> status = (Map<String, String>) super.pageContext.getAttribute(
				STATUS_KEY, PageContext.PAGE_SCOPE);
		if (status == null) {
			status = new HashMap<String, String>();
			super.pageContext.setAttribute(STATUS_KEY, status,
					PageContext.PAGE_SCOPE);
		}
		return status;
	}

	protected final void setStatus(String key, String value) {
		getStatus().put(key, value);
	}

	protected final String getStatus(String key) {
		return getStatus().get(key);
	}

	protected final void removeStatus(String key) {
		getStatus().remove(key);
	}

	protected final void clearStatus() {
		getStatus().clear();
	}

}
