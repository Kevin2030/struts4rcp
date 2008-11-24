package com.googlecode.struts4rcp.server.view.extjs;

public class ExtScope {

	private final String parentName;

	public static final int IN_ROOT = 0;

	public static final int IN_LIST = 1;

	public static final int IN_MAP = 2;

	private final int scopeType;

	public ExtScope(String parentName, int scopeType) {
		if (scopeType != IN_ROOT && scopeType != IN_LIST && scopeType != IN_MAP)
			throw new java.lang.IllegalArgumentException("scopeType error!");
		this.parentName = parentName;
		this.scopeType = scopeType;
	}

	public String getParentName() {
		return parentName;
	}

	public int getScopeType() {
		return scopeType;
	}

	private boolean isFirst = true;

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

}
