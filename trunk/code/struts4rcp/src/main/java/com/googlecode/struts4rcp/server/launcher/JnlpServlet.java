package com.googlecode.struts4rcp.server.launcher;

import java.io.File;
import java.io.FileFilter;
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

	private String mainJar;

	private String launcher;

	private String os;

	private String title;

	private String vendor;

	private String homepage;

	private String description;

	@Override
	public void init() throws ServletException {
		super.init();
		mainJar = getParam("mainJar");
		String jarsConfig = getParam("jars");
		if (jarsConfig == null)
			throw new ServletException("The \"jars\" param is required in JnlpServlet!");
		jars = new ArrayList<String>();
		String root = super.getServletContext().getRealPath("/");
		root = root.replace('\\', '/');
		if (! root.endsWith("/"))
			root = root + "/";
		for (String token: jarsConfig.split("\\,")) {
			if (token != null && token.trim().length() > 0 && ! "*".equals(token)) {
				token = token.replace('\\', '/');
				if (token.startsWith("/"))
					token.substring(1);
				int i = token.indexOf('*');
				if (i >= 0) {
					String prefix = token.substring(0, i);
					final String suffix = token.substring(i + 1);
					String dir = root;
					if (prefix.length() > 0) {
						int j = prefix.lastIndexOf('/');
						if (j >= 0) {
							dir = root + prefix.substring(0, j);
							prefix = prefix.substring(j + 1);
						}
					}
					File dirFile = new File(dir);
					if (dirFile.exists() && dirFile.isDirectory()) {
						File[] files = dirFile.listFiles(new FileFilter() {
							public boolean accept(File file) {
								return file.isFile() &&
									(suffix == null || suffix.length() == 0 || file.getAbsolutePath().endsWith(suffix));
							}
						});
						if (files != null && files.length > 0) {
							for (File file : files) {
								String name = file.getAbsolutePath().substring(root.length()).replace('\\', '/');
								if (mainJar == null
										&& file.getName().startsWith("struts4rcp-client")) {
									mainJar = name;
								} else {
									jars.add(name);
								}
							}
						}
					}
				} else {
					jars.add(token.trim());
				}
			}
		}
		if (jars.size() == 0)
			throw new ServletException("The \"jars\" param is required in JnlpServlet!");
		if (mainJar == null)
			throw new ServletException("No such struts4rcp-client-x.x.jar!");
		launcher = getParam("launcher");
		if (launcher == null)
			throw new ServletException("The \"launcher\" param is required in JnlpServlet!");
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
		writer.write("		<jar href=\"" + mainJar + "\"/>\n");
		for (String jar : jars)
			writer.write("		<jar href=\"" + jar + "\"/>\n");
		writer.write("	</resources>\n");
		writer.write("	<application-desc main-class=\"com.googlecode.struts4rcp.client.launcher.Main\">\n");
		writer.write("		<argument>" + launcher + "</argument>\n");
		writer.write("		<argument>" + serverHost + "</argument>\n");
		writer.write("		<argument>" + serverPort + "</argument>\n");
		writer.write("		<argument>" + contextPath + "</argument>\n");
		writer.write("	</application-desc>\n");
		writer.write("</jnlp>\n");
	}

}
