package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

public class JsonStreamSerializer implements StreamSerializer {

	private JsonTextSerializer serializer = new JsonTextSerializer();

	public Serializable deserialize(InputStream in) throws IOException {
		return serializer.deserialize(new InputStreamReader(in));
	}

	public void serialize(Serializable obj, OutputStream out)
			throws IOException {
		serializer.serialize(obj, new OutputStreamWriter(out));
	}

	public String getContentType() {
		return serializer.getContentType();
	}

}
