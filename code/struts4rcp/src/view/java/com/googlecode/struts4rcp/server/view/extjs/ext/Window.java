package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A specialized panel intended for use as an application window. Windows are floated and draggable by default, and also provide specific behavior like the ability to maximize and restore (with an event for minimizing, since the minimize behavior is application-specific). Windows can also be linked to a Ext.WindowGroup or managed by the Ext.WindowManager to provide grouping, activation, to front/back and other application-specific behavior.")
public class Window extends Panel {
	private String animateTarget;
	private Boolean closable;
	private String closeAction;
	private Boolean constrain;
	private Boolean constrainHeader;
	private String defaultButton;
	private Boolean expandOnShow;
	private String manager;
	private Boolean maximizable;
	private Integer minHeight;
	private Integer minWidth;
	private Boolean minimizable;
	private Boolean modal;
	private String onEsc;
	private Boolean plain;
	private Boolean resizable;
	private String resizeHandles;

	@Variable
	public String getAnimateTarget() {
		return animateTarget;
	}

	@Description("animateTarget : String/Element \n"
			+ "Id or element from which the window should animate while opening (defaults to null with no animation).")
	public void setAnimateTarget(String animateTarget) {
		this.animateTarget = animateTarget;
	}

	public Boolean isClosable() {
		return closable;
	}

	@Description("closable : Boolean \n"
			+ "True to display the 'close' tool button and allow the user to close the window, false to hide the button and disallow closing the window (default to true). ")
	public void setClosable(Boolean closable) {
		this.closable = closable;
	}

	public String getCloseAction() {
		return closeAction;
	}

	@Description("closeAction : String \n"
			+ "The action to take when the close button is clicked. The default action is 'close' which will actually remove the window from the DOM and destroy it. The other valid option is 'hide' which will simply hide the window by setting visibility to hidden and applying negative offsets, keeping the window available to be redisplayed via the show method. ")
	public void setCloseAction(String closeAction) {
		this.closeAction = closeAction;
	}

	public Boolean isConstrain() {
		return constrain;
	}

	@Description("constrain : Boolean \n"
			+ "True to constrain the window to the viewport, false to allow it to fall outside of the viewport (defaults to false). Optionally the header only can be constrained using constrainHeader. ")
	public void setConstrain(Boolean constrain) {
		this.constrain = constrain;
	}

	public Boolean isConstrainHeader() {
		return constrainHeader;
	}

	@Description("constrainHeader : Boolean \n"
			+ "True to constrain the window header to the viewport, allowing the window body to fall outside of the viewport, false to allow the header to fall outside the viewport (defaults to false). Optionally the entire window can be constrained using constrain. ")
	public void setConstrainHeader(Boolean constrainHeader) {
		this.constrainHeader = constrainHeader;
	}

	public String getDefaultButton() {
		return defaultButton;
	}

	@Description("defaultButton : String/Number/Button \n"
			+ "The id / index of a button or a button instance to focus when this window received the focus. ")
	public void setDefaultButton(String defaultButton) {
		this.defaultButton = defaultButton;
	}

	public Boolean isExpandOnShow() {
		return expandOnShow;
	}

	@Description("expandOnShow : Boolean \n"
			+ "True to always expand the window when it is displayed, false to keep it in its current state (which may be collapsed) when displayed (defaults to true). ")
	public void setExpandOnShow(Boolean expandOnShow) {
		this.expandOnShow = expandOnShow;
	}

	@Variable
	public String getManager() {
		return manager;
	}

	@Description("manager : Ext.WindowGroup \n"
			+ "A reference to the WindowGroup that should manage this window (defaults to Ext.WindowMgr). ")
	public void setManager(String manager) {
		this.manager = manager;
	}

	public Boolean isMaximizable() {
		return maximizable;
	}

	@Description("maximizable : Boolean \n"
			+ "True to display the 'maximize' tool button and allow the user to maximize the window, false to hide the button and disallow maximizing the window (defaults to false). Note that when a window is maximized, the tool button will automatically change to a 'restore' button with the appropriate behavior already built-in that will restore the window to its previous size. ")
	public void setMaximizable(Boolean maximizable) {
		this.maximizable = maximizable;
	}

	public Integer getMinHeight() {
		return minHeight;
	}

	@Description("minHeight : Number \n"
			+ "The minimum height in pixels allowed for this window (defaults to 100). Only applies when resizable = true. ")
	public void setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	@Description("minWidth : Number \n"
			+ "The minimum width in pixels allowed for this window (defaults to 200). Only applies when resizable = true. ")
	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	public Boolean isMinimizable() {
		return minimizable;
	}

	@Description("minimizable : Boolean \n"
			+ "True to display the 'minimize' tool button and allow the user to minimize the window, false to hide the button and disallow minimizing the window (defaults to false). Note that this button provides no implementation -- the behavior of minimizing a window is implementation-specific, so the minimize event must be handled and a custom minimize behavior implemented for this option to be useful. ")
	public void setMinimizable(Boolean minimizable) {
		this.minimizable = minimizable;
	}

	public Boolean isModal() {
		return modal;
	}

	@Description("modal : Boolean \n"
			+ "True to make the window modal and mask everything behind it when displayed, false to display it without restricting access to other UI elements (defaults to false). ")
	public void setModal(Boolean modal) {
		this.modal = modal;
	}

	public String getOnEsc() {
		return onEsc;
	}

	@Description("onEsc : Function \n"
			+ "Allows override of the built-in processing for the escape key. Default action is to close the Window (performing whatever action is specified in closeAction. To prevent the Window closing when the escape key is pressed, specify this as Ext.emptyFn (See Ext.emptyFn). ")
	public void setOnEsc(String onEsc) {
		this.onEsc = onEsc;
	}

	public Boolean isPlain() {
		return plain;
	}

	@Description("plain : Boolean \n"
			+ "True to render the window body with a transparent background so that it will blend into the framing elements, false to add a lighter background color to visually highlight the body element and separate it more distinctly from the surrounding frame (defaults to false). ")
	public void setPlain(Boolean plain) {
		this.plain = plain;
	}

	public Boolean isResizable() {
		return resizable;
	}

	@Description("resizable : Boolean \n"
			+ "True to allow user resizing at each edge and corner of the window, false to disable resizing (defaults to true). ")
	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}

	public String getResizeHandles() {
		return resizeHandles;
	}

	@Description("resizeHandles : String \n"
			+ "A valid Ext.Resizable handles config string (defaults to 'all'). Only applies when resizable = true. ")
	public void setResizeHandles(String resizeHandles) {
		this.resizeHandles = resizeHandles;
	}
}
