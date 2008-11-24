package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This is a layout that enables anchoring of contained elements relative to the container's dimensions. If the container is resized, all anchored items are automatically rerendered according to their anchor rules. This class is intended to be extended or created via the layout:'anchor' Ext.Container.layout config, and should generally not need to be created directly via the new keyword. \n"
		+ "AnchorLayout does not have any direct config options (other than inherited ones). However, the container using the AnchorLayout can supply an anchoring-specific config property of anchorSize. By default, AnchorLayout will calculate anchor measurements based on the size of the container itself. However, if anchorSize is specifed, the layout will use it as a virtual container for the purposes of calculating anchor measurements based on it instead, allowing the container to be sized independently of the anchoring logic if necessary."
		+ "The items added to an AnchorLayout can also supply an anchoring-specific config property of anchor which is a string containing two values: the horizontal anchor value and the vertical anchor value (for example, '100% 50%'). This value is what tells the layout how the item should be anchored to the container. The following types of anchor values are supported: "
		+ "Percentage: Any value between 1 and 100, expressed as a percentage. The first anchor is the percentage width that the item should take up within the container, and the second is the percentage height. Example: '100% 50%' would render an item the complete width of the container and 1/2 its height. If only one anchor value is supplied it is assumed to be the width value and the height will default to auto."
		+ "Offsets: Any positive or negative integer value. The first anchor is the offset from the right edge of the container, and the second is the offset from the bottom edge. Example: '-50 -100' would render an item the complete width of the container minus 50 pixels and the complete height minus 100 pixels. If only one anchor value is supplied it is assumed to be the right offset value and the bottom offset will default to 0."
		+ "Sides: Valid values are 'right' (or 'r') and 'bottom' (or 'b'). Either the container must have a fixed size or an anchorSize config value defined at render time in order for these to have any effect. "
		+ "Anchor values can also be mixed as needed. For example, '-50 75%' would render the width offset from the container right edge by 50 pixels and 75% of the container's height.")
public class AnchorLayout extends ContainerLayout {

}
