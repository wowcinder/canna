package xdata.etl.web.client.app;

import xdata.etl.web.client.ui.CenterView;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public abstract class CenterViewFinder {
	@Inject
	protected EventBus eventBus;

	protected CenterViewFinder() {
	}

	public abstract CenterView findCenterView(String token);
}
