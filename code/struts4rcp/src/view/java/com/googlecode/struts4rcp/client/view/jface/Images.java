package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * 图片资源门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Images {

	private Images() {}

	public static Image getImage(String name) {
		return ImageDescriptor.createFromFile(Images.class, "images/" + name).createImage();
	}

}
