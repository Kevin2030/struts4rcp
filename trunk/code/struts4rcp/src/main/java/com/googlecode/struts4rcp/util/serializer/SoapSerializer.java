package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

public class SoapSerializer implements TextSerializer {

	public String getContentType() {
		return "text/soap";
	}

	public String getContentEncoding() {
		return "UTF-8";
	}

	public Serializable deserialize(Reader in) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void serialize(Serializable obj, Writer out) throws IOException {
		// TODO Auto-generated method stub

	}

}
