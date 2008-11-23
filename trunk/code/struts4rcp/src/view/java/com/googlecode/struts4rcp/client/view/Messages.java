package com.googlecode.struts4rcp.client.view;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化信息资源门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Messages {

	private static final String DEFAULT_BASE_NAME = "com/googlecode/struts4rcp/client/view/messages";

	private static String baseName = DEFAULT_BASE_NAME;

	/**
	 * 设置国际化信息配置文件基名，如：com/xxx/messages
	 * @param baseName 国际化信息配置文件基名
	 */
	public static void setBaseName(String baseName) {
		if (baseName != null)
			Messages.baseName = baseName;
	}

	private static final Locale DEFAULT_LOCALE = Locale.getDefault();

	private static Locale locale = DEFAULT_LOCALE;

	/**
	 * 设置国际化区域
	 * @param locale 国际化区域
	 */
	public static void setLocale(Locale locale) {
		if (locale != null)
			Messages.locale = locale;
	}

	public static String getMessage(String key) {
		return ResourceBundle.getBundle(baseName, locale).getString(key);
	}

	public static String getMessage(String key, Object... args) {
		return formatMessage(getMessage(key), args);
	}

	// 格式化占位符
	private static String formatMessage(String msg, Object[] args) {
		if (msg != null && msg.length() > 0
				&& args != null && args.length > 0) {
			return new MessageFormat(msg, locale).format(args);
		}
		return msg;
	}

}
