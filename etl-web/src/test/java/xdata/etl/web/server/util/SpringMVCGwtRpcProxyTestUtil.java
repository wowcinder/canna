package xdata.etl.web.server.util;

import xdata.etl.web.server.controller.rpc.SpringMVCGwtRpcProxy;
import xdata.etl.web.server.controller.rpc.SpringMVCGwtRpcProxyUtilInterface;

import com.google.gwt.user.client.rpc.RemoteService;

public class SpringMVCGwtRpcProxyTestUtil implements
		SpringMVCGwtRpcProxyUtilInterface {

	@Override
	public SpringMVCGwtRpcProxy getService(Class<? extends RemoteService> clazz) {
		return null;
	}

	@Override
	public SpringMVCGwtRpcProxy getService(String rpcPath) {
		return null;
	}

}
