package com.googlecode.struts4rcp.client;

public class Worker {

	public static void fore(Workable workable) {
		Client.getClient().getActionFactory().sync(workable);
	}

	public static void back(Workable workable) {
		Client.getClient().getActionFactory().async(workable);
	}

	public static void fore(Client client, Workable workable) {
		client.getActionFactory().sync(workable);
	}

	public static void back(Client client, Workable workable) {
		client.getActionFactory().async(workable);
	}

}