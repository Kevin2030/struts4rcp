package com.googlecode.struts4rcp.server.view.extjs.ext.form;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Provides a lightweight HTML Editor component. "
		+ "Note: The focus/blur and validation marking functionality inherited from Ext.form.Field is NOT supported by this editor."
		+ "An Editor is a sensitive component that can't be used in all spots standard fields can be used. Putting an Editor within any element that has display set to 'none' can cause problems in Safari and Firefox due to their default iframe reloading bugs. ")
public class HtmlEditor extends Field {
	private String createLinkText;
	private String defaultLinkValue;
	private Boolean enableAlignments;
	private Boolean enableColors;
	private Boolean enableFont;
	private Boolean enableFontSize;
	private Boolean enableFormat;
	private Boolean enableLinks;
	private Boolean enableLists;
	private Boolean enableSourceEdit;
	private String fontFamilies;

	public String getCreateLinkText() {
		return createLinkText;
	}

	@Description("createLinkText : String"
			+ "The default text for the create link prompt ")
	public void setCreateLinkText(String createLinkText) {
		this.createLinkText = createLinkText;
	}

	public String getDefaultLinkValue() {
		return defaultLinkValue;
	}

	@Description("defaultLinkValue : String"
			+ "The default value for the create link prompt (defaults to http:/ /) ")
	public void setDefaultLinkValue(String defaultLinkValue) {
		this.defaultLinkValue = defaultLinkValue;
	}

	public Boolean isEnableAlignments() {
		return enableAlignments;
	}

	@Description("enableAlignments : Boolean"
			+ "Enable the left, center, right alignment buttons (defaults to true) ")
	public void setEnableAlignments(Boolean enableAlignments) {
		this.enableAlignments = enableAlignments;
	}

	public Boolean isEnableColors() {
		return enableColors;
	}

	@Description("enableColors : Boolean"
			+ "Enable the fore/highlight color buttons (defaults to true) ")
	public void setEnableColors(Boolean enableColors) {
		this.enableColors = enableColors;
	}

	public Boolean isEnableFont() {
		return enableFont;
	}

	@Description("enableFont : Boolean"
			+ "Enable font selection. Not available in Safari. (defaults to true) ")
	public void setEnableFont(Boolean enableFont) {
		this.enableFont = enableFont;
	}

	public Boolean isEnableFontSize() {
		return enableFontSize;
	}

	@Description("enableFontSize : Boolean"
			+ "Enable the increase/decrease font size buttons (defaults to true) ")
	public void setEnableFontSize(Boolean enableFontSize) {
		this.enableFontSize = enableFontSize;
	}

	public Boolean isEnableFormat() {
		return enableFormat;
	}

	@Description("enableFormat : Boolean"
			+ "Enable the bold, italic and underline buttons (defaults to true) ")
	public void setEnableFormat(Boolean enableFormat) {
		this.enableFormat = enableFormat;
	}

	public Boolean isEnableLinks() {
		return enableLinks;
	}

	@Description("enableLinks : Boolean"
			+ "Enable the create link button. Not available in Safari. (defaults to true) ")
	public void setEnableLinks(Boolean enableLinks) {
		this.enableLinks = enableLinks;
	}

	public Boolean isEnableLists() {
		return enableLists;
	}

	@Description("enableLists : Boolean"
			+ "Enable the bullet and numbered list buttons. Not available in Safari. (defaults to true) ")
	public void setEnableLists(Boolean enableLists) {
		this.enableLists = enableLists;
	}

	public Boolean isEnableSourceEdit() {
		return enableSourceEdit;
	}

	@Description("enableSourceEdit : Boolean"
			+ "Enable the switch to source edit button. Not available in Safari. (defaults to true) ")
	public void setEnableSourceEdit(Boolean enableSourceEdit) {
		this.enableSourceEdit = enableSourceEdit;
	}

	public String getFontFamilies() {
		return fontFamilies;
	}

	@Description("fontFamilies : Array"
			+ "An array of available font families ")
	public void setFontFamilies(String fontFamilies) {
		this.fontFamilies = fontFamilies;
	}

}
