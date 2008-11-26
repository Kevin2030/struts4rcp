package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.server.action.Page;
import com.googlecode.struts4rcp.server.action.PageAction;
import com.googlecode.struts4rcp.util.ClassUtils;

/**
 * 页面序列化器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class PageSerializer extends FormSerializer {

	public String getContentType() {
		return "text/html";
	}

	public void serialize(Serializable result, HttpServletResponse response)
			throws IOException {
		String page;
		Action<Serializable, Serializable> action = ActionContext.getContext().getAction();
		if (action instanceof PageAction) {
			PageAction<Serializable, Serializable> pageAction = (PageAction<Serializable, Serializable>)action;
			page = pageAction.getPage();
		} else {
			page = getPage(action);
		}
		// 页面扩展名
		String pageExtension = getPageExtension();
		if (pageExtension != null
				&& pageExtension.length() > 0)
			page = page + "." + pageExtension;
		serialize(result, response, page);
	}

	/**
	 * Action缺省页面名查找方式
	 * @param action Action实例
	 * @return 页面路径(不包含后缀)
	 */
	public static String getPage(Action<Serializable, Serializable> action) {
		if (action.getClass().isAnnotationPresent(Page.class))
			return action.getClass().getAnnotation(Page.class).value();
		try {
			Method method = ClassUtils.getMethod(action.getClass(), "execute");
			if (method.isAnnotationPresent(Page.class))
				return method.getAnnotation(Page.class).value();
		} catch (NoSuchMethodException e) {
			// ignore
		}
		return action.getClass().getName().replace('.', '/');
	}

	protected Locale getLocale() {
		return ActionContext.getContext().getRequest().getLocale();
	}

	/**
	 * 获取页面扩展
	 * @return 页面扩展
	 */
	protected abstract String getPageExtension();

	/**
	 * 序列化
	 * @param result 结果数据
	 * @param response 响应信息
	 * @param page 页面路径
	 * @throws IOException 序列化失败时抛出
	 */
	protected abstract void serialize(Serializable result, HttpServletResponse response, String page) throws IOException;

}
