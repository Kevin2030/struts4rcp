package com.googlecode.struts4rcp.server.view.extjs;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * EXT标签TLD文件生成器
 *
 * @author Achievo: 梁飞
 *
 */
public class TldGenerator {

	/**
	 * 生成tld
	 *
	 * @param javaDir
	 *            java文件所在目录
	 * @param tldFile
	 *            tld文件存放路径
	 * @throws Exception
	 */
	public void generate(String javaDir, String tldFile) throws Exception {
		PrintWriter tldOut = new PrintWriter(new FileWriter(new File(tldFile)));
		tldOut.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		tldOut.println("<!DOCTYPE taglib PUBLIC "
				+ "\"-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN\" "
				+ "\"http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd\">");
		tldOut.println("<taglib>");
		tldOut.println("    <tlib-version>2.2.3</tlib-version>");
		tldOut.println("    <jsp-version>1.2</jsp-version>");
		tldOut.println("    <short-name>extjs</short-name>");
		tldOut.println("    <uri>/extjs</uri>");
		tldOut.println("    <display-name>ExtJS Tags</display-name>");
		tldOut.println("    <description><![CDATA[ExtJS Tags]]></description>");
		File dir = new File(javaDir);
		List<File> list = new ArrayList<File>();
		findJava(dir, list);
		for (File file : list) {
			String path = file.getCanonicalPath();
			path = path.replace("/", ".").replace("\\", ".");
			path = path.substring(path.indexOf(this.getClass().getPackage()
					.getName())); // 去掉src
			path = path.substring(0, path.length() - 5); // 去掉".java"
			Class<?> tagClass = Class.forName(path);
			String tagName = tagClass.getSimpleName();
			if (tagName.length() > 2)
				tagName = tagName.substring(0, 1).toLowerCase()
						+ tagName.substring(1);
			else
				tagName = tagName.toLowerCase();
			if ((tagClass.getModifiers() & Modifier.ABSTRACT) == 0 // 非抽象类
					&& JspTag.class.isAssignableFrom(tagClass)) { // 实现JspTag接口
				System.out.println(tagClass.getName());
				String tagDesc = "";
				if (tagClass.isAnnotationPresent(Description.class))
					tagDesc = tagClass.getAnnotation(Description.class).value();
				tldOut.println("    <tag>");
				tldOut.println("        <name>" + tagName + "</name>");
				tldOut.println("        <tag-class>" + tagClass.getCanonicalName()
						+ "</tag-class>");
				tldOut.println("        <body-content>JSP</body-content>"); // JSP/Empty
				tldOut.println("        <description><![CDATA[" + tagDesc
						+ "]]></description>");
				for (TagAttribute attr : getAllPropertyTypes(tagClass)) {
					tldOut.println("        <attribute>");
					tldOut.println("            <name>" + attr.getName() + "</name>");
					tldOut.println("            <required>false</required>");
					tldOut.println("            <rtexprvalue>true</rtexprvalue>");
					tldOut.println("            <type>"
							+ attr.getType().getCanonicalName() + "</type>");
					tldOut.println("            <description><![CDATA[" + attr.getDesc()
							+ "]]></description>");
					tldOut.println("        </attribute>");
				}
				tldOut.println("    </tag>");
			}
		}
		tldOut.println("</taglib>");
		tldOut.flush();
		tldOut.close();
	}

	protected List<TagAttribute> getAllPropertyTypes(Class<?> clazz) {
		List<TagAttribute> list = new ArrayList<TagAttribute>();
		list.add(new TagAttribute("id", String.class, "tag id"));
		Method[] methods = clazz.getMethods();
		for (int i = 0, n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				if ((method.getModifiers() & Modifier.PUBLIC) == 1
						&& method.getDeclaringClass() != BodyTagSupport.class
						&& method.getDeclaringClass() != TagSupport.class
						&& method.getDeclaringClass() != Object.class
						&& method.getParameterTypes() != null
						&& method.getParameterTypes().length == 1) {
					String methodName = method.getName();
					if (methodName.startsWith("set")) {
						String property = methodName.substring(3, 4)
								.toLowerCase()
								+ methodName.substring(4);
						String attrDesc = "";
						if (method.isAnnotationPresent(Description.class))
							attrDesc = method.getAnnotation(Description.class)
									.value();
						list.add(new TagAttribute(property, method
								.getParameterTypes()[0], attrDesc));
					}
				}
			} catch (Exception e) {
				// Ignore
			}
		}
		return list;
	}

	private static final class TagAttribute {

		private final String name;

		private final Class<?> type;

		private final String desc;

		public TagAttribute(String name, Class<?> type, String desc) {
			this.name = name;
			this.type = type;
			this.desc = desc;
		}

		public String getName() {
			return name;
		}

		public Class<?> getType() {
			return type;
		}

		public String getDesc() {
			return desc;
		}

	}

	private void findJava(File dir, List<File> list) {
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					findJava(file, list);
				} else if (file.getName().endsWith(".java")) {
					list.add(file);
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			new TldGenerator()
					.generate(
							"src/view/java/com/googlecode/struts4rcp/server/view/extjs/",
							"src/view/resources/META-INF/extjs.tld");
		} catch (Exception e) {
		}
	}

}
