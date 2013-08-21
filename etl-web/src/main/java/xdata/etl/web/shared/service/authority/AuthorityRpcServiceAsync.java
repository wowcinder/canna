/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.authority;

import com.google.gwt.user.client.rpc.AsyncCallback;

import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.service.RpcServiceAsync;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 *
 */
public interface AuthorityRpcServiceAsync extends
		RpcServiceAsync<Integer, Authority> {

	void queryByName(Integer agId, String name,
			AsyncCallback<Authority> callback);

}
