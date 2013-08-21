package xdata.etl.web.client.app.ioc;

import xdata.etl.web.client.app.EtlApp;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(RpcGinModule.class)
public interface RpcGinjector extends Ginjector {
	EtlApp getApp();
}
