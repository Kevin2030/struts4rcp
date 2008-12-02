package com.googlecode.struts4rcp.client.event;

import java.util.Collection;
import java.util.HashSet;

import com.googlecode.struts4rcp.util.ThreadUtils;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * 事件发布器
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 * @param <L> 监听器类型
 * @param <E> 事件类型
 */
public abstract class Publisher<L extends Listener, E extends Event> {

	/**
	 * 日志输出接口
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final Collection<L> listeners = new HashSet<L>();

	/**
	 * 添加传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public void addListener(L listener) {
		if (listener == null)
			throw new NullPointerException("Listener == null");
		logger.debug("add listener: " + listener.getClass().getName());
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	/**
	 * 移除传输事件监听器
	 * @param listener 传输事件监听器
	 */
	public void removeListener(L listener) {
		if (listener == null)
			throw new NullPointerException("Listener == null");
		logger.debug("remove listener: " + listener.getClass().getName());
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	/**
	 * 清空传输事件监听器
	 */
	public void clearListeners() {
		synchronized(listeners) {
			listeners.clear();
		}
	}

	/**
	 * 发布传输事件
	 * @param event
	 */
	public void publishEvent(E event) {
		if (event == null)
			throw new NullPointerException("Event == null");
		Collection<L> temp = new HashSet<L>();
		synchronized (listeners) {
			temp.addAll(listeners);
		}
		for (L listener : temp) {
			executeEvent(listener, event);
		}
		temp.clear();
	}

	/**
	 * 向指定监听器发布事件(如果监听器未注册，则将自动注册)
	 * @param listener 监听器
	 * @param event 事件
	 */
	public void publishEvent(L listener, E event) {
		if (listener == null)
			throw new NullPointerException("Listener == null");
		if (event == null)
			throw new NullPointerException("Event == null");
		synchronized (listeners) {
			if (! listeners.contains(listener))
				addListener(listener);
		}
		executeEvent(listener, event);
	}

	protected void executeEvent(final L listener, final E event) {
		if (listener.isAsync()) {
			ThreadUtils.execute(new Runnable() {
				public void run() {
					try {
						doEvent(listener, event);
					} catch (Throwable t) {
						logger.debug(t.getMessage(), t);
						// ignore
					}
				}
			});
		} else {
			try {
				doEvent(listener, event);
			} catch (Throwable t) {
				logger.debug(t.getMessage(), t);
				// ignore
			}
		}
	}

	/**
	 * 处理事件
	 * @param listener 监听器
	 * @param event 事件
	 */
	protected abstract void doEvent(L listener, E event);

}
