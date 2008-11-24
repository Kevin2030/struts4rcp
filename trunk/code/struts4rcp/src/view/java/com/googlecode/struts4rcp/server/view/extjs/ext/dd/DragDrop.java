package com.googlecode.struts4rcp.server.view.extjs.ext.dd;

import com.googlecode.struts4rcp.server.view.extjs.ComponentTag;
import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("Defines the interface and base operation of items that that can be dragged or can be drop targets. It was designed to be extended, overriding the event handlers for startDrag, onDrag, onDragOver and onDragOut. Up to three html elements can be associated with a DragDrop instance:"
		+ "linked element: the element that is passed into the constructor. This is the element which defines the boundaries for interaction with other DragDrop objects."
		+ "handle element(s): The drag operation only occurs if the element that was clicked matches a handle element. By default this is the linked element, but there are times that you will want only a portion of the linked element to initiate the drag operation, and the setHandleElId() method provides a way to define this."
		+ "drag element: this represents the element that would be moved along with the cursor during a drag operation. By default, this is the linked element itself as in Ext.dd.DD. setDragElId() lets you define a separate element that would be moved, as in Ext.dd.DDProxy."
		+ "This class should not be instantiated until the onload event to ensure that the associated elements are available. The following would define a DragDrop obj that would interact with any other DragDrop obj in the 'group1' group: "
		+ "dd = new Ext.dd.DragDrop('div1', 'group1');"
		+ "Since none of the event handlers have been implemented, nothing would actually happen if you were to run the code above. Normally you would override this class or one of the default implementations, but you can also override the methods you want on an instance of the class..."
		+ "dd.onDragDrop = function(e, id) {"
		+ "alert('dd was dropped on ' + id);" + "}")
public class DragDrop extends ComponentTag {
}
