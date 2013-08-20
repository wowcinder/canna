/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.authority;

import com.google.gwt.user.client.rpc.AsyncCallback;

import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.service.RpcServiceAsync;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 *
 */
public interface AuthorityGroupServiceAsync extends
		RpcServiceAsync<Integer, AuthorityGroup> {

	void queryByName(String name, AsyncCallback<Integer> callback);

}
