package com.googlecode.struts4rcp.server.view.extjs;

import java.io.IOException;

import javax.servlet.jsp.JspException;

public class UI extends ExtTag {

	private static final long serialVersionUID = 1L;

	private String title;

	public String getTitle() {
		if (title == null)
			title = "SF-Express C2 System";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String styles;

	public String getStyles() {
		if (styles == null)
			return "";
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	private String scripts;

	public String getScripts() {
		if (scripts == null)
			return "";
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().println(
					"<html><head><title>" + title + "</title>" + ExtConfig.getImports()
					+ ExtConfig.parseCustomStyles(styles) + ExtConfig.parseCustomScripts(scripts)
					+ "<script type=\"text/javascript\" src=\"" + ExtConfig.getHome() + "source/locale/ext-lang-" + pageContext.getRequest().getLocale() + ".js\"></script>"
					+ "<script type=\"text/javascript\">Ext.BLANK_IMAGE_URL = '" + ExtConfig.getHome() + "resources/images/default/s.gif';</script>"
					+ "</head><body onload=\"if(document.images) new Image().src=Ext.BLANK_IMAGE_URL;\" onclick=\"if (window.self != window.top && window.top && window.top.Ext) window.top.Ext.menu.MenuMgr.hideAll();\"><script type=\"text/javascript\">"
					+ "Ext.onReady(function() {Ext.QuickTips.init();Ext.state.Manager.setProvider(new Ext.state.CookieProvider({expires:null}));");
			super.pushExtScope(new ExtScope("ui", ExtScope.IN_ROOT));
			return EVAL_BODY_INCLUDE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			super.popExtScope();
			pageContext.getOut().println(
					"}); "
					+ getDefinedBeforeScripts()
					+ getDefinedComponents()
					+ getDefinedAfterScripts()
					+ "</script><style type=\"text/css\">"+ getDefinedStyles() + "</style></body></html>");
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

}