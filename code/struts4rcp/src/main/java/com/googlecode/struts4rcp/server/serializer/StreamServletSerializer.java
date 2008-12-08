package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.struts4rcp.server.ServletSerializer;
import com.googlecode.struts4rcp.util.serializer.StreamSerializer;

public class StreamServletSerializer implements ServletSerializer {

	private final StreamSerializer serializer;

	public StreamServletSerializer(StreamSerializer serializer) {
		if (serializer == null)
			throw new NullPointerException("StreamSerializer == null!");
		this.serializer = serializer;
	}

	public Serializable deserialize(Class<? extends Serializable> baseClass, HttpServletRequest in) throws IOException {
		return serializer.deserialize(Serializable.class, in.getInputStream());
	}

	public String getContentType() {
		return serializer.getContentType();
	}

	public void serialize(Serializable obj, HttpServletResponse out)
			throws IOException {
		serializer.serialize(obj, out.getOutputStream());
	}

}
