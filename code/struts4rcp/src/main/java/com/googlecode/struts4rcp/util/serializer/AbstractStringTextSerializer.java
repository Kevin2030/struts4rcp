package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import com.googlecode.struts4rcp.util.IOUtils;

public abstract class AbstractStringTextSerializer extends AbstractSerializer implements TextSerializer {

	public String getContentEncoding() {
		return "UTF-8";
	}

	public void serialize(Serializable obj, OutputStream out) throws IOException {
		serialize(obj, new OutputStreamWriter(out, getContentEncoding()));
	}

	public Serializable deserialize(InputStream in) throws IOException {
		return deserialize(new InputStreamReader(in, getContentEncoding()));
	}

	public Serializable deserialize(Reader reader) throws IOException {
		return deserialize(IOUtils.readToString(reader));
	}

	public void serialize(Serializable obj, Writer writer) throws IOException {
		writer.write(serializeAsString(obj));
		writer.flush();
	}

}
