package com.googlecode.struts4rcp.client;

import java.io.IOException;
import java.util.Stack;

public class Work implements Abortable, Backable {

	public Work() {}

	public Work(String title) {
		this.title = title;
	}

	public Work(boolean backable) {
		this.backable = backable;
	}

	public Work(String title, boolean backable) {
		this.title = title;
		this.backable = backable;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private final Stack<Abortable> abortorStack = new Stack<Abortable>();

	public void pushAbortor(Abortable abortor) {
		if (abortor == null)
			throw new NullPointerException("Abortable == null!");
		abortorStack.push(abortor);
	}

	public void popAbortor() {
		if (! abortorStack.isEmpty())
			abortorStack.pop();
	}

	public void abort() throws IOException {
		if (abortable && ! abortorStack.isEmpty()) {
			Abortable abortable = abortorStack.pop();
			if (abortable != null)
				abortable.abort();
		}
	}

	public boolean canAbort() {
		return abortable && ! abortorStack.isEmpty();
	}

	public void clear() {
		abortorStack.clear();
	}

	private boolean back;

	public boolean isBack() {
		return back;
	}

	public void setBack(boolean back) {
		this.back = back;
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