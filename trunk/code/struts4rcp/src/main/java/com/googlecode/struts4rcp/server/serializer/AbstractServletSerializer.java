package com.googlecode.struts4rcp.server.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import com.googlecode.struts4rcp.server.ActionContext;
import com.googlecode.struts4rcp.util.serializer.AbstractTextSerializer;

public abstract class AbstractServletSerializer extends AbstractTextSerializer implements ServletSerializer {

	public Serializable deserialize(Reader reader) throws IOException {
		return deserialize(ActionContext.getContext().getRequest());
	}

	public void serialize(Serializable obj, Writer writer) throws IOException {
		serialize(obj, ActionContext.getContext().getResponse());
	}

	public Serializable deserialize(InputStream in) throws IOException {
		return deserialize(ActionContext.getContext().getRequest());
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		serialize(obj, ActionContext.getContext().getResponse());
	}

}
