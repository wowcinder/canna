package com.voole.gxt.client.service;

import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;

public interface CannaServiceAsync<T> {
	void saveReturnObjct(T t, AsyncCallback<T> callback);

	void save(T t, AsyncCallback<Long> callback);

	void update(T t, AsyncCallback<Void> callback);

	void delete(Long id, AsyncCallback<Void> callback);

	void get(CannaPagingLoadConfigBean config,
			AsyncCallback<PagingLoadResult<T>> callback);

	void get(AsyncCallback<List<T>> callback);

	void delete(List<Long> ids, AsyncCallback<Void> callback);

	void dummy(AsyncCallback<ValidationSupport> callback);
}
