package com.voole.gxt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.state.client.CookieProvider;
import com.sencha.gxt.state.client.StateManager;
import com.voole.gxt.client.app.CannaApp;
import com.voole.gxt.client.app.ioc.CannaGinjector;

public class Canna implements EntryPoint {
	private final CannaGinjector injector = GWT.create(CannaGinjector.class);

	@Override
	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				StateManager.get().setProvider(
						new CookieProvider("/", null, null, GXT.isSecure()));

				CannaApp app = injector.getApp();
				app.run();

				onReady();
			}

		});
	}

	private native void onReady() /*-{
		if (typeof $wnd.GxtReady != 'undefined') {
			$wnd.GxtReady();
		}
	}-*/;
}
