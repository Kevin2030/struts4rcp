package com.googlecode.struts4rcp.util.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamTextSerializer implements TextSerializer {

	private XStream xstream = new XStream(new DomDriver());

	public String getContentType() {
		return "text/xstream";
	}

	public String getContentEncoding() {
		return "UTF-8";
	}

	public Serializable deserialize(Reader in) throws IOException {
		return (Serializable) xstream.fromXML(in);
	}

	public void serialize(Serializable obj, Writer out) throws IOException {
		xstream.toXML(obj, out);
	}

}
