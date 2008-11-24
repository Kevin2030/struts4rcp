package com.googlecode.struts4rcp.server.launcher;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JnlpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<String> jars;

	private String launcher;

	private String actionSuffix;

	private String os;

	private String title;

	private String vendor;

	private String homepage;

	private String description;

	@Override
	public void init() throws ServletException {
		super.init();
		String jarsConfig = getParam("jars");
		if (jarsConfig == null)
			throw new ServletException("The \"jars\" param is required in JnlpServlet!");
		jars = new ArrayList<String>();
		for (String token: jarsConfig.split("\\,"))
			if (token != null && token.trim().length() > 0)
				jars.add(token.trim());
		if (jars.size() == 0)
			throw new ServletException("The \"jars\" param is required in JnlpServlet!");
		launcher = getParam("launcher");
		if (launcher == null)
			throw new ServletException("The \"launcher\" param is required in JnlpServlet!");
		actionSuffix = getParam("actionSuffix");
		if (actionSuffix == null)
			actionSuffix = "data";
		os = getParam("os");
		title = getParam("title");
		vendor = getParam("vendor");
		homepage = getParam("homepage");
		description = getParam("description");
	}

	private String getParam(String name) {
		String value = super.getInitParameter(name);
		if (value == null)
			return null;
		value = value.trim();
		if (value.length() == 0)
			return null;
		return value;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置类型
		response.setContentType("application/x-java-jnlp-file");
		// 请求参数
		String serverHost = request.getLocalAddr();
		int serverPort = request.getLocalPort();
		String contextPath = request.getContextPath();
		if (contextPath == null)
			contextPath = "";
		if (contextPath.startsWith("/"))
			contextPath = contextPath.substring(1);
		String url = request.getRequestURL().toString();
		int i = url.lastIndexOf('/');
		String codebase = url.substring(0, i);
		String href = url.substring(i + 1);
		// 输出JNLP
		Writer writer = response.getWriter();
		writer.write("<jnlp spec=\"1.0+\" codebase=\"" + codebase + "\" href=\"" + href + "\">\n");
		writer.write("	<information>\n");
		writer.write("		<title>" + (title != null ? title : href) + "</title>\n");
		writer.write("		<vendor>" + (vendor != null ? vendor : serverHost) + "</vendor>\n");
		writer.write("		<homepage href=\"" + (homepage != null ? homepage : "/") + "\"/>\n");
		writer.write("		<description>" + (description != null ? description : url) + "</description>\n");
		writer.write("	</information>\n");
		writer.write("	<security><all-permissions/></security>\n");
		writer.write("	<resources os=\"" + (os != null ? os : "Windows") + "\">\n");
		writer.write("		<j2se version=\"1.5+\"/>\n");
		for (String jar : jars)
			writer.write("		<jar href=\"" + jar + "\"/>\n");
		writer.write("	</resources>\n");
		writer.write("	<application-desc main-class=\"com.googlecode.struts4rcp.client.launcher.Main\">\n");
		writer.write("		<argument>" + launcher + "</argument>\n");
		writer.write("		<argument>" + serverHost + "</argument>\n");
		writer.write("		<argument>" + serverPort + "</argument>\n");
		writer.write("		<argument>" + contextPath + "</argument>\n");
		writer.write("		<argument>" + actionSuffix + "</argument>\n");
		writer.write("	</application-desc>\n");
		writer.write("</jnlp>\n");
	}

}
