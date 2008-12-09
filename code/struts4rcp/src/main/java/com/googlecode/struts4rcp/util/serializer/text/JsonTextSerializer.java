package com.googlecode.struts4rcp.util.serializer.text;

import java.io.IOException;
import java.io.Serializable;

import com.googlecode.struts4rcp.util.JsonUtils;

/**
 * JSON序列化实现
 * @see com.googlecode.struts4rcp.util.JsonUtils
 * @see org.json.JSONObject
 * @see org.json.JSONArray
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class JsonTextSerializer extends StringTextSerializer {

	public String getContentType() {
		return "text/json";
	}

	public Serializable deserialize(Class<? extends Serializable> cls, String data) throws IOException {
		if (data == null || data.length() == 0)
			return null;
		return (Serializable)JsonUtils.fromJson(data, cls);
	}

	public String serialize(Serializable obj) throws IOException {
		return JsonUtils.toJson(obj);
	}

}