package com.googlecode.struts4rcp.server.view.extjs;

public class ExtConfig {

	private static String treeDataProxy = "tree.proxy";

	private static String servletName;

	private static String home;

	private static String imports;

	public static void init(String servletName, String home, String styles,
			String scripts) {
		ExtConfig.servletName = servletName;
		ExtConfig.home = home;
		ExtConfig.imports = parseStyles(styles) + parseScripts(scripts);
	}

	public static final String parseStyles(String styles) {
		StringBuilder buf = new StringBuilder();
		if (styles != null && styles.trim().length() > 0) {
			for (String css : styles.split("\\,")) {
				if (css != null) {
					String str = css.trim();
					if (str.length() > 0) {
						buf.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
								+ ExtConfig.getHome() + str + "\" />");
					}
				}
			}
		}
		return buf.toString();
	}
	
	/**
	 * 导入自定义样式的方法
	 * @param styles
	 * @return
	 */
	public static final String parseCustomStyles(String styles) {
		StringBuilder buf = new StringBuilder();
		if (styles != null && styles.trim().length() > 0) {
			for (String css : styles.split("\\,")) {
				if (css != null) {
					String str = css.trim();
					if (str.length() > 0) {
						buf.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
								+ str + "\" />");
					}
				}
			}
		}
		return buf.toString();
	}

	public static final String parseScripts(String scripts) {
		StringBuilder buf = new StringBuilder();
		if (scripts != null && scripts.trim().length() > 0) {
			for (String js : scripts.split("\\,")) {
				if (js != null) {
					String str = js.trim();
					if (str.length() > 0) {
						buf.append("<script type=\"text/javascript\" src=\""
								+ ExtConfig.getHome() + str + "\"></script>");
					}
				}
			}
		}
		return buf.toString();
	}
	
	/**
	 * 自定义的脚本导入方法
	 * @param scripts
	 * @return
	 */
	public static final String parseCustomScripts(String scripts) {
		StringBuilder buf = new StringBuilder();
		if (scripts != null && scripts.trim().length() > 0) {
			for (String js : scripts.split("\\,")) {
				if (js != null) {
					String str = js.trim();
					if (str.length() > 0) {
						buf.append("<script type=\"text/javascript\" src=\""
								+ str + "\"></script>");
					}
				}
			}
		}
		return buf.toString();
	}

	public static String getHome() {
		return home;
	}

	public static String getImports() {
		return imports;
	}

	public static String getTreeDataProxy() {
		return treeDataProxy;
	}

	public static String getTreeDataProxyUrl() {
		return servletName + "/" + treeDataProxy;
	}

}
