package com.googlecode.struts4rcp.client.view.jface;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * 图片资源门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Images {

	private Images() {}

	private static final String DEFAULT_DIRECTORY = "com/googlecode/struts4rcp/client/view/images/";

	private static String directory = DEFAULT_DIRECTORY;

	/**
	 * 设置图片目录(ClassPath中)
	 * @param directory 图片目录
	 */
	public static void setDirectory(String directory) {
		if (directory != null) {
			directory = directory.replace('\\', '/');
			if (! directory.endsWith("/"))
				directory = directory + "/";
			Images.directory = directory;
		}
	}

	public static Image getImage(String name) {
		return ImageDescriptor.createFromURL(Thread.currentThread().getContextClassLoader().getResource(directory + name)).createImage();
	}

}
