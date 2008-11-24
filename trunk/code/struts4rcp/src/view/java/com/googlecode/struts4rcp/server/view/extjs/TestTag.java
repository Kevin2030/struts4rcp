package com.googlecode.struts4rcp.server.view.extjs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TestTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().println("aaa");
			return EVAL_BODY_BUFFERED;
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().println("bbb");
			String body = "";
			if (bodyContent != null && bodyContent.getString() != null) {
				body = bodyContent.getString().trim();
			}
			pageContext.getOut().println("ccc");
			pageContext.getOut().println(body);
			pageContext.getOut().println("ddd");
			return EVAL_PAGE;
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

}
