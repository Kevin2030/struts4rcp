package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.googlecode.struts4rcp.server.map.ModelMap;
import com.googlecode.struts4rcp.server.util.PropertiesUtils;

/**
 * Velocity模板结果序列化器.
 * 参见：<a href="http://velocity.apache.org">http://velocity.apache.org</a>
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class VelocitySerializer extends PageSerializer {

	protected final VelocityEngine velocityEngine;

	public VelocitySerializer() {
		try {
	        Properties properties = PropertiesUtils.getProperties(getPropertiesName());
	        properties.setProperty("resource.loader", "classpath");
	        properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	        velocityEngine = new VelocityEngine(properties);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected String getPageExtension() {
		return "vtl";
	}

	protected String getPropertiesName() {
		return "velocity.properties";
	}

	protected void serialize(Serializable obj, HttpServletResponse response, String page) throws IOException {
		try {
			Template template = velocityEngine.getTemplate(page, getContentEncoding());
			Context context = new VelocityContext(new ModelMap(obj));
			template.merge(context, response.getWriter());
		} catch (RuntimeException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
