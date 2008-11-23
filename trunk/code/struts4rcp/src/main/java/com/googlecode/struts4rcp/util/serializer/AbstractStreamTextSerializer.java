package com.googlecode.struts4rcp.util.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import com.googlecode.struts4rcp.util.IOUtils;

public abstract class AbstractStreamTextSerializer extends AbstractSerializer implements TextSerializer {

	public String getContentEncoding() {
		return "UTF-8";
	}

	public Serializable deserialize(Reader reader) throws IOException {
		return deserialize(IOUtils.readToString(reader));
	}

	public void serialize(Serializable obj, Writer writer) throws IOException {
		writer.write(serializeAsString(obj));
		writer.flush();
	}

	public Serializable deserialize(String data) throws IOException {
		return deserialize(new ByteArrayInputStream(data.getBytes(getContentEncoding())));
	}

	public String serializeAsString(Serializable obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		return new String(out.toByteArray(), getContentEncoding());
	}

}
