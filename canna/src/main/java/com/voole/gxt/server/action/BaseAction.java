package com.voole.gxt.server.action;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.server.util.BeanFinder;
import com.voole.gxt.server.util.GWTRemoteServiceServletHelper;

public class BaseAction {
	private Map<Class<?>, GWTRemoteServiceServletHelper> helperMap = new HashMap<Class<?>, GWTRemoteServiceServletHelper>();

	public BaseAction() {

	}

	public GWTRemoteServiceServletHelper getGWTHelper(Class<?> clazz) {
		if (!helperMap.containsKey(clazz)
				|| helperMap.get(clazz).getDelegate() == null) {
			createGWTHelper(clazz);
		}
		return helperMap.get(clazz);
	}

	protected RemoteService getService(Class<?> clazz) {
		return (RemoteService) BeanFinder.getBean(clazz);
	}

	private synchronized void createGWTHelper(Class<?> clazz) {
		if (helperMap.containsKey(clazz)) {
			return;
		}
		GWTRemoteServiceServletHelper helper = new GWTRemoteServiceServletHelper(
				getService(clazz));
		helperMap.put(clazz, helper);
	}
}
