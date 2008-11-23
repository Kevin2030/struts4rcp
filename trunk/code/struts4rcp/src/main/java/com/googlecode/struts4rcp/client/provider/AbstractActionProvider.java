package com.googlecode.struts4rcp.client.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;

import com.googlecode.struts4rcp.client.ActionProvider;
import com.googlecode.struts4rcp.client.Backable;
import com.googlecode.struts4rcp.client.Client;
import com.googlecode.struts4rcp.client.Execution;
import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExceptionPublisher;
import com.googlecode.struts4rcp.client.event.ExecutionEvent;
import com.googlecode.struts4rcp.client.event.ExecutionListener;
import com.googlecode.struts4rcp.client.event.ExecutionPublisher;
import com.googlecode.struts4rcp.util.logger.Logger;
import com.googlecode.struts4rcp.util.logger.LoggerFactory;

/**
 * Action供给器基类
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public abstract class AbstractActionProvider extends ActionProvider {

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

	private Collection<Execution> foreExecutions = new HashSet<Execution>();

	private Collection<Execution> backExecutions = new HashSet<Execution>();

	@Override
	public Collection<Execution> getExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (foreExecutions) {
			copies.addAll(foreExecutions);
		}
		synchronized (backExecutions) {
			copies.addAll(backExecutions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	@Override
	public boolean isExecuting() {
		synchronized (foreExecutions) {
			if (! foreExecutions.isEmpty())
				return true;
		}
		synchronized (backExecutions) {
			if (! backExecutions.isEmpty())
				return true;
		}
		return false;
	}

	@Override
	public Collection<Execution> getForeExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (foreExecutions) {
			copies.addAll(foreExecutions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	@Override
	public boolean isForeExecuting() {
		synchronized (foreExecutions) {
			return ! foreExecutions.isEmpty();
		}
	}

	@Override
	public Collection<Execution> getBackExecutions() {
		Collection<Execution> copies = new HashSet<Execution>();
		synchronized (backExecutions) {
			copies.addAll(backExecutions);
		}
		return Collections.unmodifiableCollection(copies);
	}

	@Override
	public boolean isBackExecuting() {
		synchronized (backExecutions) {
			return ! backExecutions.isEmpty();
		}
	}

	protected void addExecution(Execution execution) {
		if (execution == null)
			throw new NullPointerException("Execution == null!");
		if (execution.isBack()) {
			synchronized (backExecutions) {
				backExecutions.add(execution);
			}
		} else {
			synchronized (foreExecutions) {
				foreExecutions.add(execution);
			}
		}
		execution.executing(new Backer(execution));
		executionPublisher.publishEvent(new ExecutionEvent(this, execution));
	}

	protected void removeExecution(Execution execution) {
		if (execution == null)
			throw new NullPointerException("Execution == null!");
		synchronized (foreExecutions) {
			foreExecutions.remove(execution);
		}
		synchronized (backExecutions) {
			backExecutions.remove(execution);
		}
		execution.executed();
		executionPublisher.publishEvent(new ExecutionEvent(this, execution));
	}

	private class Backer implements Backable {

		private Execution execution;

		public Backer(Execution execution) {
			if (execution == null)
				throw new NullPointerException("Execution == null!");
			this.execution = execution;
		}

		public void back() {
			synchronized (foreExecutions) {
				foreExecutions.remove(execution);
			}
			synchronized (backExecutions) {
				backExecutions.add(execution);
			}
			executionPublisher.publishEvent(new ExecutionEvent(this, execution));
		}

	}

	private ExecutionPublisher executionPublisher = new ExecutionPublisher();

	@Override
	public void addExecutionListener(ExecutionListener listener) {
		executionPublisher.addListener(listener);
	}

	@Override
	public void removeExecutionListener(ExecutionListener listener) {
		executionPublisher.removeListener(listener);
	}

	private ExceptionPublisher exceptionPublisher = new ExceptionPublisher();

	@Override
	public void addExceptionListener(ExceptionListener listener) {
		exceptionPublisher.addListener(listener);
	}

	@Override
	public void removeExceptionListener(ExceptionListener listener) {
		exceptionPublisher.removeListener(listener);
	}

	protected void publishException(Throwable exception, boolean back) {
		exceptionPublisher.publishEvent(new ExceptionEvent(this, exception, back));
	}

	public void shutdown() {
		try {
			executionPublisher.clearListeners();
		} finally {
			exceptionPublisher.clearListeners();
		}
	}

}
