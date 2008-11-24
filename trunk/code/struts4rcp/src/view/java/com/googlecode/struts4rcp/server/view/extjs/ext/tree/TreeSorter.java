package com.googlecode.struts4rcp.server.view.extjs.ext.tree;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

@Description("Provides sorting of nodes in a TreePanel ")
public class TreeSorter extends ComponentTag {
	private Boolean caseSensitive;
	private String dir;
	private Boolean folderSort;
	private String leafAttr;
	private String property;
	private String sortType;

	public Boolean isCaseSensitive() {
		return caseSensitive;
	}

	@Description("caseSensitive : Boolean \n"
			+ "true for case sensitive sort (defaults to false) ")
	public void setCaseSensitive(Boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public String getDir() {
		return dir;
	}

	@Description("dir : String \n"
			+ "The direction to sort (asc or desc) (defaults to asc) ")
	public void setDir(String dir) {
		this.dir = dir;
	}

	public Boolean isFolderSort() {
		return folderSort;
	}

	@Description("folderSort : Boolean \n"
			+ "True to sort leaf nodes under non leaf nodes")
	public void setFolderSort(Boolean folderSort) {
		this.folderSort = folderSort;
	}

	public String getLeafAttr() {
		return leafAttr;
	}

	@Description("leafAttr : String \n"
			+ "The attribute used to determine leaf nodes in folder sort (defaults to 'leaf') ")
	public void setLeafAttr(String leafAttr) {
		this.leafAttr = leafAttr;
	}

	public String getProperty() {
		return property;
	}

	@Description("property : String \n"
			+ "The named attribute on the node to sort by (defaults to text) ")
	public void setProperty(String property) {
		this.property = property;
	}

	@Variable
	public String getSortType() {
		return sortType;
	}

	@Description("sortType : Function \n"
			+ "A custom 'casting' function used to convert node values before sorting ")
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
