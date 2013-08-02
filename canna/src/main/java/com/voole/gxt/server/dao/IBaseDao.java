package com.voole.gxt.server.dao;

import java.util.List;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;

public interface IBaseDao<T> {

	T saveReturnObjct(T t);

	Long save(T t);

	void update(T t);

	void delete(List<Long> ids);

	void delete(Long id);

	List<T> get();

	PagingLoadResult<T> get(CannaPagingLoadConfigBean config);
}
