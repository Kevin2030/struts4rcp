package com.googlecode.struts4rcp.client;

import java.util.Stack;

public class Work implements Cancellable, Backable {

	private static final ThreadLocal<Work> local = new ThreadLocal<Work>();

	public static Work getCurrent() {
		return local.get();
	}

	public static void setCurrent(Work work) {
		local.set(work);
	}

	public static void removeCurrent() {
		local.remove();
	}

	public Work(boolean back) {
		this.back = back;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	private final Stack<Cancellable> abortorStack = new Stack<Cancellable>();

	public void pushAbortor(Cancellable abortor) {
		if (abortor == null)
			throw new NullPointerException("Abortable == null!");
		abortorStack.push(abortor);
	}

	public void popAbortor() {
		if (! abortorStack.isEmpty())
			abortorStack.pop();
	}

	public void cancel() throws Exception {
		if (abortable && ! abortorStack.isEmpty()) {
			Cancellable abortable = abortorStack.pop();
			if (abortable != null)
				abortable.cancel();
		}
	}

	public boolean canAbort() {
		return abortable && ! abortorStack.isEmpty();
	}

	public void clear() {
		abortorStack.clear();
	}

	private final boolean back;

	public boolean isBack() {
		return back;
	}

	private boolean backable;

	public boolean isBackable() {
		return backable;
	}

	public void setBackable(boolean backable) {
		this.backable = backable;
	}

	private boolean abortable;

	public boolean isAbortable() {
		return abortable;
	}

	public void setAbortable(boolean abortable) {
		this.abortable = abortable;
	}

	private boolean worked = false;

	public boolean isWorked() {
		return worked;
	}

	public void worked() {
		this.worked = true;
		this.backer = null;
	}

	private Backable backer;

	public void working(Backable backer) {
		this.backer = backer;
	}

	public void back() {
		if (backable && backer != null)
			backer.back();
	}

	public boolean canBack() {
		return backable && backer != null;
	}

}