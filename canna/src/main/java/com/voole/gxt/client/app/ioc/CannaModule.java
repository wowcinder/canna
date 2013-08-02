package com.voole.gxt.client.app.ioc;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.voole.gxt.client.app.MenuViewFinder;
import com.voole.gxt.client.app.mvp.CannaActivityMapper;
import com.voole.gxt.client.app.ui.CenterContainerView;
import com.voole.gxt.client.app.ui.CenterContainerViewImpl;
import com.voole.gxt.client.app.ui.MenuListView;
import com.voole.gxt.client.app.ui.MenuListViewImpl;

public class CannaModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(ActivityMapper.class).to(CannaActivityMapper.class);
		bind(MenuListView.class).to(MenuListViewImpl.class).in(Singleton.class);
		bind(CenterContainerView.class).to(CenterContainerViewImpl.class).in(
				Singleton.class);
		bind(MenuViewFinder.class).in(Singleton.class);
	}

	@Provides
	@Singleton
	PlaceController providePlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

}
