/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.util;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public interface SpringMVCGwtRpcProxyUtilInterface {
	public SpringMVCGwtRpcProxy getService(Class<? extends RemoteService> clazz);
}
