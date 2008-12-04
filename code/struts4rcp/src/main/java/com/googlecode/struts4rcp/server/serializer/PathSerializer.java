package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PathSerializer extends AbstractServletSerializer {

	public Serializable deserialize(HttpServletRequest request)
			throws IOException {
		// TODO
		return null;
	}

	public void serialize(Serializable obj, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

}