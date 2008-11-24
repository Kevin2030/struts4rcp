package com.googlecode.struts4rcp.server.view.extjs.ext.grid;

import javax.servlet.jsp.JspException;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.form.AjaxAction;

public abstract class GridAction extends AjaxAction {

	private String columns;

	// protected: 扩展字段
	protected String getColumns() {
		return columns;
	}

	@Description("Action submit columns, separator is comma.")
	public void setColumns(String columns) {
		this.columns = columns;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		String v = this.getCurrentExtScope().getParentName();
		if (v != null && v.trim().length() > 0) {
			StringBuilder columnsBuf = new StringBuilder();
			if (columns != null) {
				String[] cols = columns.split("\\,");
				if (cols != null && cols.length > 0) {
					columnsBuf.append("data={");
					boolean first = true;
					for (int i = 0, n = cols.length; i < n; i ++) {
						String col = cols[i];
						if (col != null && col.length() > 0) {
							if (! first) {
								columnsBuf.append(",");
							}
							first = false;
							columnsBuf.append(col);
							columnsBuf.append(":data.");
							columnsBuf.append(col);
						}
					}
					columnsBuf.append("};");
				}
			}
			super.defineAfterScript(v + "." + getName() + " = function() {var arr = new Array();var rows = "+v+"." + getDataFunction() + ";for (var i = 0; i < rows.length; i ++){var data = rows[i].data; " + columnsBuf.toString() + " arr.push(data);}var params = Ext.util.JSON.encode(arr);Ext.Ajax.request({params: {"+getParamsName()+": params}," + getEntryString() + "});};");
		}
		return EVAL_PAGE;
	}

	protected abstract String getParamsName();

	protected abstract String getDataFunction();

}
