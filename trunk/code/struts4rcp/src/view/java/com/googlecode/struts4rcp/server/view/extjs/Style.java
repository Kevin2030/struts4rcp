package com.googlecode.struts4rcp.server.view.extjs;

import javax.servlet.jsp.JspException;

public class Style extends ExtTag {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		super.pushExtScope(new ExtScope("style", ExtScope.IN_ROOT));
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		if (bodyContent != null) {
			defineStyle(filter(bodyContent.getString()));
		}
		super.popExtScope();
		return EVAL_PAGE;
	}

}
