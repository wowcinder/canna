package com.voole.gxt.client.app.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.voole.gxt.client.app.CannaApp;

@GinModules(CannaModule.class)
public interface CannaGinjector extends Ginjector {

	CannaApp getApp();
}
