/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.authority;

import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.authority.Authority;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public interface AuthorityServiceAsync {

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#delete(java.util.List)
	 */
	void delete(List<String> ids, AsyncCallback<Void> callback);

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#delete(java.io.Serializable)
	 */
	void delete(Provider<String> k, AsyncCallback<Void> callback);

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#dummy()
	 */
	void dummy(AsyncCallback<ValidationSupport> callback);

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#get()
	 */
	void get(AsyncCallback<List<Authority>> callback);

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#save(xdata.etl.web.shared.entity.RpcEntity)
	 */
	void save(Authority v, AsyncCallback<String> callback);

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#saveAndReturn(xdata.etl.web.shared.entity.RpcEntity)
	 */
	void saveAndReturn(Authority v, AsyncCallback<Authority> callback);

	/**
	 * 
	 * @see xdata.etl.web.client.service.RpcService#update(xdata.etl.web.shared.entity.RpcEntity)
	 */
	void update(Authority v, AsyncCallback<Void> callback);

}
