/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.authority;

import com.google.gwt.user.client.rpc.AsyncCallback;

import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 *
 */
public interface AuthorityServiceAsync extends
		RpcServiceAsync<Integer, Authority> {

	void queryByName(Integer agId, String name,
			AsyncCallback<Authority> callback);

}
