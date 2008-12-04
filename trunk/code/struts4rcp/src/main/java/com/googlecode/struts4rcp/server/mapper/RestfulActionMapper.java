package com.googlecode.struts4rcp.server.mapper;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.server.serializer.PathSerializer;
import com.googlecode.struts4rcp.util.serializer.Serializer;

public class RestfulActionMapper extends DefaultActionMapper {

	@Override
	public Serializer getSerializer(HttpServletRequest request) {
		Serializer serializer = super.getSerializer(request.getHeader("Accept"));
		if (serializer == null)
			serializer = super.getSerializer(request);
		if (serializer == null)
			return null;
		return new PathSerializer(serializer);
	}

}
