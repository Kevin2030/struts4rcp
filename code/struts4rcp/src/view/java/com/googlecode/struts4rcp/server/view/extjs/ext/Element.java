package com.googlecode.struts4rcp.server.view.extjs.ext;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("Represents an Element in the DOM."
		+ "Usage:"
		+ "var el = Ext.get('my-div');"
		+ "// or with getEl"
		+ "var el = getEl('my-div');"
		+ "// or with a DOM element"
		+ "var el = Ext.get(myDivElement);"
		+ "Using Ext.get() or getEl() instead of calling the constructor directly ensures you get the same object each call instead of constructing a new one."
		+ "Animations"
		+ "Many of the functions for manipulating an element have an optional 'animate' parameter. The animate parameter should either be a boolean (true) or an object literal with animation options. Note that the supported Element animation options are a subset of the Ext.Fx animation options specific to Fx effects. The Element animation options are:"
		+ "Option    Default   Description"
		+ "--------- --------  ---------------------------------------------"
		+ "duration  .35       The duration of the animation in seconds"
		+ "easing    easeOut   The easing method"
		+ "callback  none      A function to execute when the anim completes"
		+ "scope     this      The scope (this) of the callback function"
		+ "Also, the Anim object being used for the animation will be set on your options object as 'anim', which allows you to stop or manipulate the animation. Here's an example:"
		+ "var el = Ext.get('my-div');" + "// no animation"
		+ "el.setWidth(100);" + "// default animation"
		+ "el.setWidth(100, true);" + "// animation with some options set"
		+ "el.setWidth(100, {" + "    duration: 1," + "    callback: this.foo,"
		+ "    scope: this" + "});"
		+ "// using the 'anim' property to get the Anim object" + "var opt = {"
		+ "    duration: 1," + "    callback: this.foo," + "    scope: this"
		+ "};" + "el.setWidth(100, opt);" + "..."
		+ "if(opt.anim.isAnimated()){" + "    opt.anim.stop();" + "}"
		+ "Composite (Collections of) Elements"
		+ "For working with collections of Elements, see Ext.CompositeElement ")
public class Element extends ComponentTag {

}
