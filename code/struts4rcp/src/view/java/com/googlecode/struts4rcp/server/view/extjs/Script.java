package com.googlecode.struts4rcp.server.view.extjs;

import javax.servlet.jsp.JspException;

/**
 * EXT扩展标签
 *
 * @author 梁飞
 *
 */
public class Script extends ExtTag {

	private static final long serialVersionUID = 1L;

	private boolean before;

	public boolean isBefore() {
		return before;
	}

	public void setBefore(boolean before) {
		this.before = before;
	}

	@Override
	public int doStartTag() throws JspException {
		super.pushExtScope(new ExtScope("script", ExtScope.IN_ROOT));
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		if (bodyContent != null) {
			if (before)
				defineBeforeScript(filter(bodyContent.getString()));
			else
				defineAfterScript(filter(bodyContent.getString()));
		}
		super.popExtScope();
		return EVAL_PAGE;
	}

}
