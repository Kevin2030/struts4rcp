package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("A base editor field that handles displaying/hiding on demand and has some built-in sizing and event handling logic. ")
public class Editor extends Component {
	private String alignment;
	private Boolean autosize;
	private Boolean cancelOnEsc;
	private Boolean completeOnEnter;
	private Boolean constrain;
	private Boolean hideEl;
	private Boolean ignoreNoChange;
	private Boolean revertInvalid;
	private Boolean shadow;
	private Boolean swallowKeys;
	private Boolean updateEl;
	private String value;

	public String getAlignment() {
		return alignment;
	}

	@Description("alignment : String \n"
			+ "The position to align to (see Ext.Element.alignTo for more details, defaults to 'c-c?'). ")
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public Boolean isAutosize() {
		return autosize;
	}

	@Description("autosize : Boolean/String \n"
			+ "True for the editor to automatically adopt the size of the underlying field, 'width' to adopt the width only, or 'height' to adopt the height only (defaults to false) ")
	public void setAutosize(Boolean autosize) {
		this.autosize = autosize;
	}

	public Boolean isCancelOnEsc() {
		return cancelOnEsc;
	}

	@Description("cancelOnEsc : Boolean \n"
			+ "True to cancel the edit when the escape key is pressed (defaults to false) ")
	public void setCancelOnEsc(Boolean cancelOnEsc) {
		this.cancelOnEsc = cancelOnEsc;
	}

	public Boolean isCompleteOnEnter() {
		return completeOnEnter;
	}

	@Description("completeOnEnter : Boolean \n"
			+ "True to complete the edit when the enter key is pressed (defaults to false) ")
	public void setCompleteOnEnter(Boolean completeOnEnter) {
		this.completeOnEnter = completeOnEnter;
	}

	public Boolean isConstrain() {
		return constrain;
	}

	@Description("constrain : Boolean \n"
			+ "True to constrain the editor to the viewport ")
	public void setConstrain(Boolean constrain) {
		this.constrain = constrain;
	}

	public Boolean isHideEl() {
		return hideEl;
	}

	@Description("hideEl : Boolean \n"
			+ "False to keep the bound element visible while the editor is displayed (defaults to true) ")
	public void setHideEl(Boolean hideEl) {
		this.hideEl = hideEl;
	}

	public Boolean isIgnoreNoChange() {
		return ignoreNoChange;
	}

	@Description("ignoreNoChange : Boolean \n"
			+ "True to skip the the edit completion process (no save, no events fired) if the user completes an edit and the value has not changed (defaults to false). Applies only to string values - edits for other data types will never be ignored. ")
	public void setIgnoreNoChange(Boolean ignoreNoChange) {
		this.ignoreNoChange = ignoreNoChange;
	}

	public Boolean isRevertInvalid() {
		return revertInvalid;
	}

	@Description("revertInvalid : Boolean \n"
			+ "True to automatically revert the field value and cancel the edit when the user completes an edit and the field validation fails (defaults to true) ")
	public void setRevertInvalid(Boolean revertInvalid) {
		this.revertInvalid = revertInvalid;
	}

	public Boolean isShadow() {
		return shadow;
	}

	@Description("shadow : Boolean/String \n"
			+ "'sides' for sides/bottom only, /frame' for 4-way shadow, and 'drop' for bottom-right shadow (defaults to 'frame') ")
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public Boolean isSwallowKeys() {
		return swallowKeys;
	}

	@Description("swallowKeys : Boolean \n"
			+ "Handle the keydown/keypress events so they don't propagate (defaults to true) ")
	public void setSwallowKeys(Boolean swallowKeys) {
		this.swallowKeys = swallowKeys;
	}

	public Boolean isUpdateEl() {
		return updateEl;
	}

	@Description("updateEl : Boolean \n"
			+ "True to update the innerHTML of the bound element when the update completes (defaults to false) ")
	public void setUpdateEl(Boolean updateEl) {
		this.updateEl = updateEl;
	}

	public String getValue() {
		return value;
	}

	@Description("value : Mixed \n"
			+ "The data value of the underlying field (defaults to '') ")
	public void setValue(String value) {
		this.value = value;
	}

}
