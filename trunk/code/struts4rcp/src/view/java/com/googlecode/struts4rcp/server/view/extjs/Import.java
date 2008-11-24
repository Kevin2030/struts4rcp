package com.googlecode.struts4rcp.server.view.extjs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class Import extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().println(ExtConfig.getImports());
			return EVAL_BODY_INCLUDE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}