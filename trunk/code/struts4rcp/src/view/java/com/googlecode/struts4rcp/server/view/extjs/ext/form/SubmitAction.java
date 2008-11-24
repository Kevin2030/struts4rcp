package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import javax.servlet.jsp.JspException;

public class SubmitAction extends AjaxAction {

	private String name;

	protected String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		String v = this.getCurrentExtScope().getParentName();
		if (v != null && v.trim().length() > 0)
			super.defineAfterScript(v + "." + getName() + " = function() {" + v + ".getForm().doAction('submit',{" + getEntryString() + "});}");
		return EVAL_PAGE;
	}

}
