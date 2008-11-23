package com.googlecode.struts4rcp.client.view.swing;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

	/**
	 * 获取类路径图片
	 * @param name 图片名称
	 * @return 图片
	 */
	public static Image getImage(String name) {
		try {
			return ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(directory + name));
		} catch (Throwable e) { // 用全黑图片替代
			return new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		}
	}

	/**
	 * 获取类路径图标
	 * @param name 图标名称
	 * @return 图标
	 */
	public static ImageIcon getIcon(String name) {
		return new ImageIcon(getImage(name));
	}

}
