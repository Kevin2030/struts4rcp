package com.googlecode.struts4rcp.server.mapper;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.struts4rcp.server.ServletSerializer;
import com.googlecode.struts4rcp.server.serializer.PathServletSerializer;

public class RestfulActionMapper extends DefaultActionMapper {

	@Override
	public ServletSerializer getSerializer(HttpServletRequest request) {
		ServletSerializer serializer = super.getSerializer(request.getHeader("Accept"));
		if (serializer == null)
			serializer = super.getSerializer(request);
		if (serializer == null)
			return null;
		return new PathServletSerializer(serializer);
	}

}
