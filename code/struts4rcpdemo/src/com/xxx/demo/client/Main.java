package com.xxx.demo.client;


/**
 * 客户端测试类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Main {

	public static void main(String[] args) {
		new LoginLauncher().start("localhost", 8080, "struts4rcpdemo", "data");
	}

}
