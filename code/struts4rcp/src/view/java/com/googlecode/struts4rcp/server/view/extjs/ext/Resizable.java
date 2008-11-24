package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;
import com.googlecode.struts4rcp.server.view.extjs.ext.util.Observable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("Applies drag handles to an element to make it resizable. The drag handles are inserted into the element and positioned absolute. Some elements, such as a textarea or image, don't support this. To overcome that, you can wrap the textarea in a div and set 'resizeChild' to true (or to the id of the element), or set wrap:true in your config and the element will be wrapped for you automatically."
		+ "Here is the list of valid resize handles:"
		+ "Value   Description"
		+ "------  -------------------"
		+ " 'n'     north"
		+ " 's'     south"
		+ " 'e'     east"
		+ " 'w'     west"
		+ " 'nw'    northwest"
		+ " 'sw'    southwest"
		+ " 'se'    southeast"
		+ " 'ne'    northeast"
		+ " 'all'   all"
		+ "Here's an example showing the creation of a typical Resizable:"
		+ "var resizer = new Ext.Resizable('element-id', {"
		+ "    handles: 'all',"
		+ "    minWidth: 200,"
		+ "    minHeight: 100,"
		+ "    maxWidth: 500,"
		+ "    maxHeight: 400,"
		+ "    pinned: true"
		+ "});"
		+ "resizer.on('resize', myHandler);"
		+ "To hide a particular handle, set its display to none in CSS, or through script:"
		+ "resizer.east.setDisplayed(false);")
public class Resizable extends Observable {
	private String adjustments;
	private Boolean animate;
	private Boolean disableTrackOver;
	private Boolean draggable;
	private Integer duration;
	private Boolean dynamic;
	private String easing;
	private Boolean enabled;
	private String handles;
	private String height;
	private Integer heightIncrement;
	private Integer maxHeight;
	private Integer maxWidth;
	private Integer minHeight;
	private Integer minWidth;
	private Integer minX;
	private Integer minY;
	private Boolean multiDirectional;
	private Boolean pinned;
	private Boolean preserveRatio;
	private Boolean resizeChild;
	private String resizeRegion;
	private Boolean transparent;
	private String width;
	private Integer widthIncrement;
	private Boolean wrap;

	public String getAdjustments() {
		return adjustments;
	}

	@Description("adjustments : Array/String \n"
			+ "String 'auto' or an array [width, height] with values to be added to the resize operation's new size (defaults to [0, 0]) ")
	public void setAdjustments(String adjustments) {
		this.adjustments = adjustments;
	}

	public Boolean isAnimate() {
		return animate;
	}

	@Description("animate : Boolean \n"
			+ "True to animate the resize (not compatible with dynamic sizing, defaults to false) ")
	public void setAnimate(Boolean animate) {
		this.animate = animate;
	}

	public Boolean isDisableTrackOver() {
		return disableTrackOver;
	}

	@Description("disableTrackOver : Boolean \n"
			+ "True to disable mouse tracking. This is only applied at config time. (defaults to false) ")
	public void setDisableTrackOver(Boolean disableTrackOver) {
		this.disableTrackOver = disableTrackOver;
	}

	public Boolean isDraggable() {
		return draggable;
	}

	@Description("draggable : Boolean \n"
			+ "Convenience to initialize drag drop (defaults to false) ")
	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public Integer getDuration() {
		return duration;
	}

	@Description("duration : Number \n"
			+ "Animation duration if animate = true (defaults to .35) ")
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Boolean isDynamic() {
		return dynamic;
	}

	@Description("dynamic : Boolean \n"
			+ "True to resize the element while dragging instead of using a proxy (defaults to false) ")
	public void setDynamic(Boolean dynamic) {
		this.dynamic = dynamic;
	}

	public String getEasing() {
		return easing;
	}

	@Description("easing : String \n"
			+ "Animation easing if animate = true (defaults to 'easingOutStrong') ")
	public void setEasing(String easing) {
		this.easing = easing;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	@Description("enabled : Boolean \n"
			+ "False to disable resizing (defaults to true) ")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Variable
	public String getHandles() {
		return handles;
	}

	@Description("handles : String \n"
			+ "String consisting of the resize handles to display (defaults to undefined) ")
	public void setHandles(String handles) {
		this.handles = handles;
	}

	@Variable
	public String getHeight() {
		return height;
	}

	@Description("height : Number \n"
			+ "The height of the element in pixels (defaults to null) ")
	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getHeightIncrement() {
		return heightIncrement;
	}

	@Description("heightIncrement : Number \n"
			+ "The increment to snap the height resize in pixels (dynamic must be true, defaults to 0) ")
	public void setHeightIncrement(Integer heightIncrement) {
		this.heightIncrement = heightIncrement;
	}

	public Integer getMaxHeight() {
		return maxHeight;
	}

	@Description("minHeight : Number \n"
			+ "The minimum height for the element (defaults to 5) ")
	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Integer getMaxWidth() {
		return maxWidth;
	}

	@Description("maxWidth : Number \n"
			+ "The maximum width for the element (defaults to 10000) ")
	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}

	public Integer getMinHeight() {
		return minHeight;
	}

	@Description("minHeight : Number \n"
			+ "The minimum height for the element (defaults to 5) ")
	public void setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	@Description("minWidth : Number \n"
			+ "The minimum width for the element (defaults to 5) ")
	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	public Integer getMinX() {
		return minX;
	}

	@Description("minX : Number \n"
			+ "The minimum allowed page X for the element (only used for west resizing, defaults to 0) ")
	public void setMinX(Integer minX) {
		this.minX = minX;
	}

	public Integer getMinY() {
		return minY;
	}

	@Description("minY : Number \n"
			+ "The minimum allowed page Y for the element (only used for north resizing, defaults to 0) ")
	public void setMinY(Integer minY) {
		this.minY = minY;
	}

	public Boolean isMultiDirectional() {
		return multiDirectional;
	}

	@Description("multiDirectional : Boolean \n"
			+ "Deprecated. The old style of adding multi-direction resize handles, deprecated in favor of the handles config option (defaults to false) ")
	public void setMultiDirectional(Boolean multiDirectional) {
		this.multiDirectional = multiDirectional;
	}

	public Boolean isPinned() {
		return pinned;
	}

	@Description("pinned : Boolean \n"
			+ "True to ensure that the resize handles are always visible, false to display them only when the user mouses over the resizable borders. This is only applied at config time. (defaults to false) ")
	public void setPinned(Boolean pinned) {
		this.pinned = pinned;
	}

	public Boolean isPreserveRatio() {
		return preserveRatio;
	}

	@Description("preserveRatio : Boolean \n"
			+ "True to preserve the original ratio between height and width during resize (defaults to false) ")
	public void setPreserveRatio(Boolean preserveRatio) {
		this.preserveRatio = preserveRatio;
	}

	public Boolean isResizeChild() {
		return resizeChild;
	}

	@Description("resizeChild : Boolean/String/Element \n"
			+ "True to resize the first child, or id/element to resize (defaults to false) ")
	public void setResizeChild(Boolean resizeChild) {
		this.resizeChild = resizeChild;
	}

	public String getResizeRegion() {
		return resizeRegion;
	}

	@Description("resizeRegion : Ext.lib.Region \n"
			+ "Constrain the resize to a particular region ")
	public void setResizeRegion(String resizeRegion) {
		this.resizeRegion = resizeRegion;
	}

	public Boolean isTransparent() {
		return transparent;
	}

	@Description("transparent : Boolean \n"
			+ "True for transparent handles. This is only applied at config time. (defaults to false) ")
	public void setTransparent(Boolean transparent) {
		this.transparent = transparent;
	}

	public String getWidth() {
		return width;
	}

	@Description("width : Number \n"
			+ "The width of the element in pixels (defaults to null) ")
	public void setWidth(String width) {
		this.width = width;
	}

	public Integer getWidthIncrement() {
		return widthIncrement;
	}

	@Description("widthIncrement : Number \n"
			+ "The increment to snap the width resize in pixels (dynamic must be true, defaults to 0) ")
	public void setWidthIncrement(Integer widthIncrement) {
		this.widthIncrement = widthIncrement;
	}

	public Boolean isWrap() {
		return wrap;
	}

	@Description("wrap : Boolean \n"
			+ "True to wrap an element with a div if needed (required for textareas and images, defaults to false) ")
	public void setWrap(Boolean wrap) {
		this.wrap = wrap;
	}

}
