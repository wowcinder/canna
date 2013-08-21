package xdata.etl.web.client;

import xdata.etl.web.client.app.EtlApp;
import xdata.etl.web.client.app.ioc.RpcGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.state.client.CookieProvider;
import com.sencha.gxt.state.client.StateManager;

public class Etl implements EntryPoint {

	private final RpcGinjector injector = GWT.create(RpcGinjector.class);

	@Override
	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				StateManager.get().setProvider(
						new CookieProvider("/", null, null, GXT.isSecure()));

				EtlApp app = injector.getApp();
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
