package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.loader.ClasspathSourceLoader;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.tools.bean.ServletClassResourceLoader;
import org.commontemplate.tools.web.ModelMap;

import com.googlecode.struts4rcp.server.ActionServletContext;
import com.googlecode.struts4rcp.server.util.PropertiesUtils;

/**
 * CommonTemplate模板结果序列化器.
 * 参见：<a href="http://www.commontemplate.org">http://www.commontemplate.org</a>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class CommonTemplateSerializer extends PageSerializer {

	private final Engine engine;

	public CommonTemplateSerializer() {
		try {
			ServletContext servletContext = ActionServletContext.getContext().getServletContext();
	        Properties properties = PropertiesUtils.getProperties(getPropertiesName());
	        ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration(properties, new ServletClassResourceLoader(servletContext), null);
	        config.setSourceLoader(new ClasspathSourceLoader());
	        engine = new Engine(config);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	protected String getPageExtension() {
		return "ctl";
	}

	protected String getPropertiesName() {
		return "comontemplate.properties";
	}

	protected void serialize(Serializable obj, HttpServletResponse response, String page)
			throws IOException {
		Template template = engine.getTemplate(page);
		Context context = engine.createContext(response.getWriter());
		try {
			context.pushLocalContext(new ModelMap(obj));
			context.setLocale(getLocale());
			template.render(context);
		} finally {
			context.clear();
		}
	}

}
