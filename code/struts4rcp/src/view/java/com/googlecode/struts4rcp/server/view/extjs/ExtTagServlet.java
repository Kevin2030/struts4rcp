package com.googlecode.struts4rcp.server.view.extjs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExtTag相关处理Servlet. <br/> 使用如:
 *
 * <pre>
 * &lt;servlet&gt;
 *     &lt;servlet-name&gt;exttag&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;com.googlecode.struts4rcp.server.view.extjs.ExtTagServlet&lt;/servlet-class&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;home&lt;/param-name&gt;
 *         &lt;param-value&gt;scripts/ext-2.0.2/&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;styles&lt;/param-name&gt;
 *         &lt;param-value&gt;ext-my1.css,ext-my2.css&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;scripts&lt;/param-name&gt;
 *         &lt;param-value&gt;ext-my1.js,ext-my2.js&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 *     &lt;load-on-startup&gt;1&lt;/load-on-startup&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 *     &lt;servlet-name&gt;exttag&lt;/servlet-name&gt;
 *     &lt;url-pattern&gt;/exttag/*&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 *
 * @author Achievo: LiangFei, Oscar.Xie
 *
 */
public class ExtTagServlet extends HttpServlet {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		String home = getInitParameter("home");
		if (home == null || home.trim().length() == 0) {
			home = "../ext-2.0.2/"; // default config
			log.warn("please config: <init-param><param-name>home</param-name><param-value>../ext-2.0.2</param-value></init-param> on "
							+ this.getClass().getName());
		} else {
			home = home.trim();
			if (!home.endsWith("/")) {
				home += "/";
			}
		}
		String styles = getInitParameter("styles");
		String scripts = getInitParameter("scripts");
		ExtConfig.init(getServletName(), home, "resources/css/ext-all.css," + styles, "adapter/ext/ext-base.js,ext-all.js,ext-datetime.js,"+ scripts);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*String uri = request.getRequestURI();
		int i = uri.lastIndexOf('/');
		if (i >= 0 && i < uri.length()) {
			String proxy = uri.substring(i + 1);
			if (ExtConfig.getTreeDataProxy().equalsIgnoreCase(proxy)) {
				doTreeDataProxy(request, response);
			}
		}*/
	}

	/*
	//树数据代理
	private void doTreeDataProxy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String node = request.getParameter("node");
		String nodeField = request.getParameter("nodeField");
		String dataUrl = request.getParameter("dataUrl");
		String textField = request.getParameter("textField");
		String idField = request.getParameter("idField");
		String leafField = request.getParameter("leafField");
		String clsField = request.getParameter("clsField");
		if (nodeField == null || nodeField.length() < 1)
			nodeField = "id";
		if (dataUrl == null)
			dataUrl = "";
		if (textField == null)
			textField = "";
		if (idField == null)
			idField = "";
		if (leafField == null)
			leafField = "";
		if (clsField == null)
			clsField = "";
		String url = forwardUrl(request, dataUrl.trim(), nodeField, node);
		log.debug("tree url:" + url);
		String data = NetUtils.getContent(url);
		String responseData = replaceJson(data, textField, idField, leafField,
				clsField);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(responseData);
	}

	// 服务器内代理, 采用127.0.0.1访问
	private String forwardUrl(HttpServletRequest request, String dataUrl, String nodeField, String nodeValue) {
		StringBuffer url = new StringBuffer("http://127.0.0.1");
		int port = request.getLocalPort();
		if (port != 80) {
			url.append(":");
			url.append(String.valueOf(port));
		}
		String context = request.getContextPath();
		if (context != null && ! "/".equals(context))
			url.append(context);
		if (dataUrl.length() > 0 && dataUrl.charAt(0) != '/')
			url.append("/");
		url.append(dataUrl);
		if (dataUrl.indexOf('?') != -1) {
			url.append("&");
		} else {
			url.append("?");
		}
		url.append(nodeField);
		url.append("=");
		url.append(nodeValue);
		String query = request.getQueryString();
		int i = query.indexOf('&');
		if (i > 0)
			url.append(query.substring(i));
		return url.toString();
	}

	@SuppressWarnings("unchecked")
	private String replaceJson(String data, String textField, String idField,
			String leafField, String clsField) {
		if (data == null) {
			return null;
		}
		JSONArray srcArray = JSONArray.fromObject(data);
		JSONArray distArray = new JSONArray();
		for (int i = 0; i < srcArray.size(); i++) {
			JSONObject srcJson = srcArray.getJSONObject(i);
			Iterator<String> keys = srcJson.keys(); // unchecked
			JSONObject distJson = new JSONObject();
			while (keys.hasNext()) {
				String key = ((String) keys.next()).trim();
				if (textField.equals(key)) {
					distJson.put("text", srcJson.get(key));
				} else if (idField.equals(key)) {
					distJson.put("id", srcJson.get(key));
				} else if (leafField.equals(key)) {
					distJson.put("leaf", isTrue(srcJson.get(key)));
				} else if (clsField.equals(key)) {
					distJson.put("cls", srcJson.get(key));
				} else {
					distJson.put(key, srcJson.get(key));
				}
			}
			distArray.add(distJson);
		}
		return distArray.toString();
	}

	@SuppressWarnings("unchecked")
	private boolean isTrue(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Boolean)
			return ((Boolean) obj).booleanValue();
		if (obj instanceof Object[])
			return ((Object[]) obj).length > 0;
		if (obj instanceof Collection)
			return ((Collection) obj).size() > 0;
		if (obj instanceof Map)
			return ((Map) obj).size() > 0;
		return true;
	}*/

}
