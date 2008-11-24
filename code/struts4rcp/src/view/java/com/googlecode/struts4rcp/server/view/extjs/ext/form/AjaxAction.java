package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import java.util.Map;

public abstract class AjaxAction extends Action {

	protected final String getEntryString() {
		StringBuilder buf = new StringBuilder("");
		Map<String, ?> properties = getAllProperties();
		boolean isFirst = true;
		for (Map.Entry<String, ?> entry : properties.entrySet()) {
			buf.append(getEntryString(isFirst, entry.getKey(), entry.getValue()));
			if (isFirst)
				isFirst = false;
		}
		return buf.toString();
	}

	protected final String getEntryString(boolean isFirst, String property, Object value) {
		if (property == null || value == null) {
			return "";
		}
		if (value instanceof String && ((String)value).trim().length() == 0) {
			return "";
		}
		StringBuilder buf = new StringBuilder();
		if (! isFirst)
			buf.append(",");
		buf.append(property);
		buf.append(":");
		if (value instanceof String)
			buf.append("\"" + value + "\"");
		else
			buf.append(value.toString());
		return buf.toString();
	}

	private String name;
	protected String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
