package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public abstract class RestfulSerializer extends AbstractServletSerializer {

	public Serializable deserialize(HttpServletRequest request)
			throws IOException {
		// TODO
		return null;
	}

}