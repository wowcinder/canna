/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.RpcEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public interface RpcServiceAsync<K extends Serializable, V extends RpcEntity<K>> {
	void delete(List<K> ids, AsyncCallback<Void> callback);

	void delete(Provider<K> k, AsyncCallback<Void> callback);

	void dummy(AsyncCallback<ValidationSupport> callback);

	void get(AsyncCallback<List<V>> callback);

	void get(Provider<K> k, AsyncCallback<V> callback);

	void save(V v, AsyncCallback<K> callback);

	void saveAndReturn(V v, AsyncCallback<V> callback);

	void update(V v, AsyncCallback<V> callback);

	void get(EtlPagingLoadConfigBean config,
			AsyncCallback<PagingLoadResult<V>> callback);

}
