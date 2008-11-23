package com.googlecode.struts4rcp.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ThreadUtils {

	private ThreadUtils() {}

	private static ExecutorService executor;

	public static void init() {
		if (ThreadUtils.executor != null)
			destroy();
		ThreadUtils.executor = Executors.newCachedThreadPool();
	}

	public static void init(ExecutorService executor) {
		if (executor != null)
			ThreadUtils.executor = executor;
	}

	public static void destroy() {
		if (ThreadUtils.executor != null) {
			if (! ThreadUtils.executor.isShutdown())
				ThreadUtils.executor.shutdown();
			ThreadUtils.executor = null;
		}
	}

	public static ExecutorService getExecutor() {
		return executor;
	}

	public static void execute(Runnable command) {
		executor.execute(command);
	}

	public static <T> Future<T> submit(Callable<T> task) {
		return executor.submit(task);
	}

}
