package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

public class AsyncTreeNode extends TreeNode {
	private String loader;

	@Override
	protected String getDefaultKey() {
		return "root";
	}

	@Variable
	public String getLoader() {
		return loader;
	}

	@Description("loader : TreeLoader \n"
			+ "A TreeLoader to be used by this node (defaults to the loader defined on the tree) ")
	public void setLoader(String loader) {
		this.loader = loader;
	}
}
