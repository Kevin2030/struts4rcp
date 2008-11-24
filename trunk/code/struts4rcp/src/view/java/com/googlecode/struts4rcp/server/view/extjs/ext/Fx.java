package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;
import com.googlecode.struts4rcp.server.view.extjs.Variable;

/**
 *
 * @author oscar.xie
 *
 */
@Description("A class to provide basic animation and visual effects support. Note: This class is automatically applied to the Ext.Element interface when included, so all effects calls should be performed via Element. Conversely, since the effects are not actually defined in Element, Ext.Fx must be included in order for the Element effects to work."
		+ "It is important to note that although the Fx methods and many non-Fx Element methods support 'method chaining' in that they return the Element object itself as the method return value, it is not always possible to mix the two in a single method chain. The Fx methods use an internal effects queue so that each effect can be properly timed and sequenced. Non-Fx methods, on the other hand, have no such internal queueing and will always execute immediately. For this reason, while it may be possible to mix certain Fx and non-Fx method calls in a single chain, it may not always provide the expected results and should be done with care."
		+ "Motion effects support 8-way anchoring, meaning that you can choose one of 8 different anchor points on the Element that will serve as either the start or end point of the animation. Following are all of the supported anchor positions:"
		+ "Value  Description"
		+ "-----  -----------------------------"
		+ "tl     The top left corner"
		+ "t      The center of the top edge"
		+ "tr     The top right corner"
		+ "l      The center of the left edge"
		+ "r      The center of the right edge"
		+ "bl     The bottom left corner"
		+ "b      The center of the bottom edge"
		+ "br     The bottom right corner"
		+ "Although some Fx methods accept specific custom config parameters, the ones shown in the Config Options section below are common options that can be passed to any Fx method. ")
public class Fx extends ComponentTag {
	private String afterCls;
	private String afterStyle;
	private Boolean block;
	private String callback;
	private Boolean concurrent;
	private Integer duration;
	private String easing;
	private Boolean remove;
	private String scope;
	private Boolean stopFx;
	private Boolean useDisplay;

	public String getAfterCls() {
		return afterCls;
	}

	@Description("afterCls : String \n"
			+ "A css class to apply after the effect ")
	public void setAfterCls(String afterCls) {
		this.afterCls = afterCls;
	}

	public String getAfterStyle() {
		return afterStyle;
	}

	@Description("afterStyle : String/Object/Function \n"
			+ "A style specification string, e.g. 'width:100px', or an object in the form {width:'100px'}, or a function which returns such a specification that will be applied to the Element after the effect finishes ")
	public void setAfterStyle(String afterStyle) {
		this.afterStyle = afterStyle;
	}

	public Boolean isBlock() {
		return block;
	}

	@Description("block : Boolean \n"
			+ "Whether the effect should block other effects from queueing while it runs ")
	public void setBlock(Boolean block) {
		this.block = block;
	}

	@Variable
	public String getCallback() {
		return callback;
	}

	@Description("callback : Function \n"
			+ "A function called when the effect is finished. Note that effects are queued internally by the Fx class, so do not need to use the callback parameter to specify another effect -- effects can simply be chained together and called in sequence (e.g., el.slideIn().highlight();). The callback is intended for any additional code that should run once a particular effect has completed. ")
	public void setCallback(String callback) {
		this.callback = callback;
	}

	public Boolean isConcurrent() {
		return concurrent;
	}

	@Description("concurrent : Boolean \n"
			+ "Whether to allow subsequently-queued effects to run at the same time as the current effect, or to ensure that they run in sequence ")
	public void setConcurrent(Boolean concurrent) {
		this.concurrent = concurrent;
	}

	public Integer getDuration() {
		return duration;
	}

	@Description("duration : Number \n"
			+ "The length of time (in seconds) that the effect should last ")
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getEasing() {
		return easing;
	}

	@Description("easing : String \n" + "A valid Easing value for the effect ")
	public void setEasing(String easing) {
		this.easing = easing;
	}

	public Boolean isRemove() {
		return remove;
	}

	@Description("remove : Boolean \n"
			+ "Whether the Element should be removed from the DOM and destroyed after the effect finishes ")
	public void setRemove(Boolean remove) {
		this.remove = remove;
	}

	@Variable
	public String getScope() {
		return scope;
	}

	@Description("scope : Object \n" + "The scope of the effect function ")
	public void setScope(String scope) {
		this.scope = scope;
	}

	public Boolean isStopFx() {
		return stopFx;
	}

	@Description("stopFx : Boolean \n"
			+ "Whether subsequent effects should be stopped and removed after the current effect finishes ")
	public void setStopFx(Boolean stopFx) {
		this.stopFx = stopFx;
	}

	public Boolean isUseDisplay() {
		return useDisplay;
	}

	@Description("useDisplay : Boolean \n"
			+ "Whether to use the display CSS property instead of visibility when hiding Elements (only applies to effects that end with the element being visually hidden, ignored otherwise) ")
	public void setUseDisplay(Boolean useDisplay) {
		this.useDisplay = useDisplay;
	}

}
