package com.googlecode.struts4rcp.server.view.extjs.ext.data;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

@Description("Represents a tree data structure and bubbles all the events for its nodes. The nodes in the tree have most standard DOM functionality. ")
public class Tree extends Observable {
	private String pathSeparator;

	public String getPathSeparator() {
		return pathSeparator;
	}

	@Description("pathSeparator : String"
			+ "The token used to separate paths in node ids (defaults to '/'). ")
	public void setPathSeparator(String pathSeparator) {
		this.pathSeparator = pathSeparator;
	}
}
