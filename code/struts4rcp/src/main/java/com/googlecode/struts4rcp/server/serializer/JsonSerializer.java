package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

public class JsonSerializer extends FormSerializer {

	public String getContentType() {
		return "text/html";
	}

	public void serialize(Serializable obj, HttpServletResponse response)
			throws IOException {
		String json = com.googlecode.struts4rcp.util.serializer.JsonSerializer.toJson(obj);
		response.getWriter().write(json);
		response.getWriter().flush();
	}

}
