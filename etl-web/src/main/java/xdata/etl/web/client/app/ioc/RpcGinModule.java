package xdata.etl.web.client.app.ioc;

import xdata.etl.web.client.ui.CenterContainer;
import xdata.etl.web.client.ui.CenterContainerImpl;
import xdata.etl.web.client.ui.MenuView;
import xdata.etl.web.client.ui.MenuViewImpl;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

public class RpcGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(MenuView.class).to(MenuViewImpl.class).in(Singleton.class);
		bind(CenterContainer.class).to(CenterContainerImpl.class).in(Singleton.class);
	}

}
