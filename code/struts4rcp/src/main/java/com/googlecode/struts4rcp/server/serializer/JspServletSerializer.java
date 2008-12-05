package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.server.ActionServletContext;
import com.googlecode.struts4rcp.server.ServletForwardException;

/**
 * JSP页面结果序列化器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class JspServletSerializer extends PageServletSerializer {

	protected static final String DEFAULT_PAGE_DIRECTORY = "/WEB-INF/classes/";

	private String pageDirectory = DEFAULT_PAGE_DIRECTORY;

	public JspServletSerializer() {
		ServletConfig servletConfig = ActionServletContext.getContext().getServletConfig();
		String dir = servletConfig.getInitParameter("pageDirectory");
		if (dir != null) {
			dir = dir.trim();
			if (dir.length() > 0) {
				dir = dir.replace('\\', '/');
				if (! dir.endsWith("/"))
					dir += "/";
				this.pageDirectory = dir;
			}
		}
	}

	/**
	 * 获取页面目录
	 * @return 页面目录
	 */
	protected String getPageDirectory() {
		return pageDirectory;
	}

	protected String getPageExtension() {
		return "jsp";
	}

	protected void serialize(Serializable result, HttpServletResponse response, String page) throws IOException {
		// 页面目录
		String pageDirectory = getPageDirectory();
		if (pageDirectory != null
				&& pageDirectory.length() > 0)
		page = pageDirectory + page;
		throw new ServletForwardException(page, result); // 跳转到指定页面
	}

}
