package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.server.map.ModelMap;
import com.googlecode.struts4rcp.server.util.PropertiesUtils;

import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarker模板结果序列化器.
 * 参见：<a href="http://www.freemarker.org">http://www.freemarker.org</a>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class FreeMarkerServletSerializer extends PageServletSerializer {

	protected final Configuration configuration = new Configuration();

	public FreeMarkerServletSerializer() {
		try {
			configuration.setTemplateLoader(new ClasspathTemplateLoader());
	        configuration.setSettings(PropertiesUtils.getProperties(getPropertiesName()));
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected String getPageExtension() {
		return "ftl";
	}

	protected String getPropertiesName() {
		return "freemarker.properties";
	}

	protected void serialize(Serializable obj, HttpServletResponse response, String page) throws IOException {
		Template template = configuration.getTemplate(page, getLocale());
		try {
			template.process(new ModelMap(obj), response.getWriter());
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected static class ClasspathTemplateLoader extends URLTemplateLoader {
	    protected URL getURL(String name) {
	        return Thread.currentThread().getContextClassLoader().getResource(name);
	    }
	}

}
