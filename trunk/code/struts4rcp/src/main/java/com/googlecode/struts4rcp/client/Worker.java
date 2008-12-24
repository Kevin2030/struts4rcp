package com.googlecode.struts4rcp.client;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import com.googlecode.struts4rcp.client.event.ExceptionEvent;
import com.googlecode.struts4rcp.client.event.ExceptionListener;
import com.googlecode.struts4rcp.client.event.ExceptionPublisher;
import com.googlecode.struts4rcp.client.event.Listenable;
import com.googlecode.struts4rcp.client.event.Listener;
import com.googlecode.struts4rcp.client.event.WorkEvent;
import com.googlecode.struts4rcp.client.event.WorkListener;
import com.googlecode.struts4rcp.client.event.WorkPublisher;
import com.googlecode.struts4rcp.util.ThreadUtils;

/**
 * 工作执行者
 * @author <a href="mailto:liangfei0201@gmail.com">liangfei</a>
 */
public class Worker implements Listenable {

	private Worker() {}

	private static final Worker WORKER = new Worker();

	public static Worker getWorker() {
		return WORKER;
	}

	/**
	 * 前台工作
	 * @param workable 工作内容
	 */
	public void runInForeground(Workable workable) {
		runInForeground(workable, null);
	}

	/**
	 * 前台工作
	 * @param workable 工作内容
	 */
	public void runInForeground(Workable workable, String message) {
		runInForeground(workable, message, false, true);
	}

	/**
	 * 前台工作
	 * @param workable 工作内容
	 * @param backable 是否可转为后台运行
	 */
	public void runInForeground(Workable workable, String message, boolean backable, boolean abortable) {
		Work work = new Work(false);
		work.setMessage(message);
		work.setBackable(backable);
		work.setAbortable(abortable);
		work(work, workable);
	}

	/**
	 * 后台工作
	 * @param workable 工作内容
	 */
	public void runInBackground(Workable workable) {
		work(new Work(true), workable);
	}

	/**
	 * 后台工作
	 * @param workable 工作内容
	 */
	public void runInBackground(Workable workable, boolean abortable) {
		work(new Work(true), workable);
	}

	private void work(final Work work, final Workable workable) {
		ThreadUtils.execute(new Runnable() {
			public void run() {
				Work.setCurrent(work);
				try {
					addWork(work);
					try {
						workable.run(work);
					} finally {
						removeWork(work);
					}
				} catch (Throwable e) {
					publishException(e, work.isBack());
				} finally {
					Work.removeCurrent();
				}
			}
		});
	}

	private final WorkPublisher workPublisher = new WorkPublisher();

	/**
	 * 添加前台执行监听器
	 * @param listener 前台执行监听器
	 */
	public void addWorkListener(WorkListener listener) {
		workPublisher.addListener(listener);
	}

	/**
	 * 移除前台执行监听器
	 * @param listener 前台执行监听器
	 */
	public void removeWorkListener(WorkListener listener) {
		workPublisher.removeListener(listener);
	}

	private final ExceptionPublisher exceptionPublisher = new ExceptionPublisher();

	/**
	 * 添加异常事件监听器
	 * @param listener 异常事件监听器
	 */
	public void addExceptionListener(ExceptionListener listener) {
		exceptionPublisher.addListener(listener);
	}

	/**
	 * 移除异常事件监听器
	 * @param listener 异常事件监听器
	 */
	public void removeExceptionListener(ExceptionListener listener) {
		exceptionPublisher.removeListener(listener);
	}

	private Collection<Work> foreWorks = new HashSet<Work>();

	private Collection<Work> backWorks = new HashSet<Work>();

	/**
	 * 获取所有正在执行的执行项
	 * @return 正在前台执行的执行项
	 */
	public Collection<Work> getWorks() {
		Collection<Work> copies = new HashSet<Work>();
		synchronized (foreWorks) {
			copies.addAll(foreWorks);
		}
		synchronized (backWorks) {
			copies.addAll(backWorks);
		}
		return Collections.unmodifiableCollection(copies);
	}

	/**
	 * 是否正在执行
	 * @return 是否正在执行
	 */
	public boolean isWorking() {
		synchronized (foreWorks) {
			if (! foreWorks.isEmpty())
				return true;
		}
		synchronized (backWorks) {
			if (! backWorks.isEmpty())
				return true;
		}
		return false;
	}

	/**
	 * 获取正在前台执行的执行项
	 * @return 正在前台执行的执行项
	 */
	public Collection<Work> getForeWorks() {
		Collection<Work> copies = new HashSet<Work>();
		synchronized (foreWorks) {
			copies.addAll(foreWorks);
		}
		return Collections.unmodifiableCollection(copies);
	}

	/**
	 * 是否正在前台执行
	 * @return 是否正在前台执行
	 */
	public boolean isForeWorking() {
		synchronized (foreWorks) {
			return ! foreWorks.isEmpty();
		}
	}

	/**
	 * 获取正在后台执行的执行项
	 * @return 正在后台执行的执行项
	 */
	public Collection<Work> getBackWorks() {
		Collection<Work> copies = new HashSet<Work>();
		synchronized (backWorks) {
			copies.addAll(backWorks);
		}
		return Collections.unmodifiableCollection(copies);
	}

	/**
	 * 是否正在后台执行
	 * @return 是否正在后台执行
	 */
	public boolean isBackWorking() {
		synchronized (backWorks) {
			return ! backWorks.isEmpty();
		}
	}

	private void addWork(Work work) {
		if (work == null)
			throw new NullPointerException("Work == null!");
		if (work.isBack()) {
			synchronized (backWorks) {
				backWorks.add(work);
			}
		} else {
			synchronized (foreWorks) {
				foreWorks.add(work);
			}
		}
		work.working(new Backer(work));
		workPublisher.publishEvent(new WorkEvent(this, work));
	}

	private void removeWork(Work work) {
		if (work == null)
			throw new NullPointerException("Work == null!");
		synchronized (foreWorks) {
			foreWorks.remove(work);
		}
		synchronized (backWorks) {
			backWorks.remove(work);
		}
		work.worked();
		workPublisher.publishEvent(new WorkEvent(this, work));
	}

	private void publishException(Throwable exception, boolean back) {
		this.exceptionPublisher.publishEvent(new ExceptionEvent(this, exception, back));
	}

	private class Backer implements Backable {

		private Work work;

		public Backer(Work work) {
			if (work == null)
				throw new NullPointerException("Work == null!");
			this.work = work;
		}

		public void back() {
			synchronized (foreWorks) {
				foreWorks.remove(work);
			}
			synchronized (backWorks) {
				backWorks.add(work);
			}
			workPublisher.publishEvent(new WorkEvent(this, work));
		}

	}

	public void shutdown() {
		try {
			workPublisher.clearListeners();
		} finally {
			exceptionPublisher.clearListeners();
		}
	}

	/**
	 * 向默认客户端实例中，注册事件监听器
	 *
	 * @param listener 事件监听器
	 */
	public void addListener(Listener listener) {
		if (listener instanceof WorkListener)
			addWorkListener(
					(WorkListener) listener);
		if (listener instanceof ExceptionListener)
			addExceptionListener(
					(ExceptionListener) listener);
	}

	/**
	 * 从默认客户端实例中，移除事件监听器
	 * @param listener 事件监听器
	 */
	public void removeListener(Listener listener) {
		if (listener instanceof WorkListener)
			removeWorkListener(
					(WorkListener) listener);
		if (listener instanceof ExceptionListener)
			removeExceptionListener(
					(ExceptionListener) listener);
	}

}