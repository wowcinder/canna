package com.voole.gxt.client.app.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.voole.gxt.client.app.ui.CenterContainerView;

public class CenterContainerActivity extends AbstractActivity {

	@Inject
	private CenterContainerView centerContainer;

	@Inject
	private PlaceController placeController;

	private String menuToken;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		centerContainer.showCenterView(getMenuToken());
	}

	public String getMenuToken() {
		return menuToken;
	}

	public void setMenuToken(String menuToken) {
		this.menuToken = menuToken;
	}

}
