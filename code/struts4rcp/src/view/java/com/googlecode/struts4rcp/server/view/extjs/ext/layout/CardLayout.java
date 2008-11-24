package com.googlecode.struts4rcp.server.view.extjs.ext.layout;

import com.googlecode.struts4rcp.server.view.extjs.Description;

@Description("This layout contains multiple panels, each fit to the container, where only a single panel can be visible at any given time. This layout style is most commonly used for wizards, tab implementations, etc. This class is intended to be extended or created via the layout:'card' Ext.Container.layout config, and should generally not need to be created directly via the new keyword."
		+ "The CardLayout's focal method is setActiveItem. Since only one panel is displayed at a time, the only way to move from one panel to the next is by calling setActiveItem, passing the id or index of the next panel to display. The layout itself does not provide a mechanism for handling this navigation, so that functionality must be provided by the developer."
		+ "In the following example, a simplistic wizard setup is demonstrated. A button bar is added to the footer of the containing panel to provide navigation buttons. The buttons will be handled by a common navigation routine -- for this example, the implementation of that routine has been ommitted since it can be any type of custom logic. Note that other uses of a CardLayout (like a tab control) would require a completely different implementation. For serious implementations, a better approach would be to extend CardLayout to provide the custom functionality needed. Example usage:"
		+ "var navHandler = function(direction){"
		+ "    // This routine could contain business logic required to manage the navigation steps."
		+ "    // It would call setActiveItem as needed, manage navigation button state, handle any"
		+ "    // branching logic that might be required, handle alternate actions like cancellation"
		+ "    // or finalization, etc.  A complete wizard implementation could get pretty"
		+ "    // sophisticated depending on the complexity required, and should probably be"
		+ "    // done as a subclass of CardLayout in a real-world implementation."
		+ "};"
		+ "var card = new Ext.Panel({"
		+ "    title: 'Example Wizard',"
		+ "    layout:'card',"
		+ "    activeItem: 0, // make sure the active item is set on the container config!"
		+ "    bodyStyle: 'padding:15px',"
		+ "    defaults: {"
		+ "        // applied to each contained panel"
		+ "        border:false"
		+ "    },"
		+ "    // just an example of one possible navigation scheme, using buttons"
		+ "    bbar: ["
		+ "        {"
		+ "            id: 'move-prev',"
		+ "            text: 'Back',"
		+ "            handler: navHandler.createDelegate(this, [-1]),"
		+ "            disabled: true"
		+ "        },"
		+ "        '->', // greedy spacer so that the buttons are aligned to each side"
		+ "        {"
		+ "            id: 'move-next',"
		+ "            text: 'Next',"
		+ "            handler: navHandler.createDelegate(this, [1])"
		+ "        }"
		+ "   ],"
		+ "    // the panels (or 'cards') within the layout"
		+ "    items: [{"
		+ "        id: 'card-0',"
		+ "        html: '<h1>Welcome to the Wizard!</h1><p>Step 1 of 3</p>'"
		+ "    },{"
		+ "        id: 'card-1',"
		+ "        html: '<p>Step 2 of 3</p>'"
		+ "    },{"
		+ "       id: 'card-2',"
		+ "        html: '<h1>Congratulations!</h1><p>Step 3 of 3 - Complete</p>'"
		+ "    }]" + "});")
public class CardLayout extends FitLayout {
	private Boolean deferredRender;

	public Boolean isDeferredRender() {
		return deferredRender;
	}

	public void setDeferredRender(Boolean deferredRender) {
		this.deferredRender = deferredRender;
	}
}
