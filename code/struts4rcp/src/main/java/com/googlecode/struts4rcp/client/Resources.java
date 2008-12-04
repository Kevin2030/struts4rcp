package com.googlecode.struts4rcp.client;

import java.io.Serializable;

import com.googlecode.struts4rcp.Resource;

/**
 * 资源静态门面
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Resources {

	private Resources() {}

	public static <M extends Serializable> Resource<M> getResource(String uri) {
		return null;
	}

}
