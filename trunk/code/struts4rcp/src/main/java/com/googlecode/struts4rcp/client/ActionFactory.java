package com.googlecode.struts4rcp.client;

import java.io.Serializable;
import java.util.Properties;

import com.googlecode.struts4rcp.Action;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * Action代理工厂
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class ActionFactory implements ClientElement {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Client client;

	public Client getClient() {
		return client;
	}

	public void init(Client client, Properties config) {
		if (client == null)
			throw new NullPointerException("Client == null!");
		if (config == null)
			throw new NullPointerException("Properties == null!");
		if (this.client != null)
			throw new IllegalStateException("ActionProvider already initialized!");
		this.client = client;
	}

	public void shutdown() {}

	/**
	 * 获取同步Action代理
	 *
	 * @param actionName action名称
	 * @return 同步Action代理
	 */
	public <M extends Serializable, R extends Serializable> Action<M, R> getAction(String actionName) {
		return new ActionProxy<M, R>(actionName);
	}

	/**
	 * Action代理
	 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
	 */
	protected final class ActionProxy<M extends Serializable, R extends Serializable> implements Action<M, R> {

		protected final String actionName;

		ActionProxy(String actionName) {
			if (actionName == null)
				throw new NullPointerException("actionName == null!");
			this.actionName = actionName;
		}

		@SuppressWarnings("unchecked")
		public R execute(final M model) throws Exception {
			Serializable result = client.getTransmitter().transmit(actionName, model);
			if (result instanceof Exception) { // 抛出异常
				Exception e = (Exception)result;
				logger.error(e.getMessage(), new RuntimeException());
				throw e;
			}
			if (result instanceof Error) { // 抛出错误
				Error e = (Error)result;
				logger.error(e.getMessage(), new RuntimeException());
				throw e;
			}
			return (R)result;
		}
	}

}