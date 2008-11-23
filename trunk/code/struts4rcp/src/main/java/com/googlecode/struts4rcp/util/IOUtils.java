package com.googlecode.struts4rcp.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOUtils {

	private IOUtils() {}

	public static String readToString(File file) throws IOException {
		return readToString(new FileReader(file));
	}

	public static char[] readToChars(File file) throws IOException {
		return readToChars(new FileReader(file));
	}

	public static byte[] readToBytes(File file) throws IOException {
		return readToBytes(new FileReader(file));
	}

	public static String readToString(InputStream inputStream) throws IOException {
		return readToString(new InputStreamReader(inputStream));
	}

	public static String readToString(InputStream inputStream, String encoding) throws IOException {
		return readToString(new InputStreamReader(inputStream, encoding));
	}

	public static char[] readToChars(InputStream inputStream) throws IOException {
		return readToChars(new InputStreamReader(inputStream));
	}

	public static char[] readToChars(InputStream inputStream, String encoding) throws IOException {
		return readToChars(new InputStreamReader(inputStream, encoding));
	}

	public static byte[] readToBytes(InputStream inputStream) throws IOException {
		return readToBytes(new InputStreamReader(inputStream));
	}

	public static byte[] readToBytes(InputStream inputStream, String encoding) throws IOException {
		return readToBytes(new InputStreamReader(inputStream, encoding));
	}

	public static String readToString(Reader reader) throws IOException {
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[8192];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			buffer.append(buf, 0, len);
		}
		return buffer.toString();
	}

	public static char[] readToChars(Reader reader) throws IOException {
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[8192];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			buffer.append(buf, 0, len);
		}
		int length = buffer.length();
		char[] dst = new char[length];
		buffer.getChars(0, length, dst, 0);
		return dst;
	}

	public static byte[] readToBytes(Reader reader) throws IOException {
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[8192];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			buffer.append(buf, 0, len);
		}
		return buffer.toString().getBytes();
	}

}
