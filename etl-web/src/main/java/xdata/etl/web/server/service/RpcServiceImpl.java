/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.client.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.server.util.HibernateBeanReplicatorUtil;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.annotations.AuthenticationMethod;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public class RpcServiceImpl<K extends Serializable, V extends RpcEntity<K>>
		implements RpcService<K, V> {
	protected IRpcDao<K, V> rpcDao;

	public RpcServiceImpl() {
	}

	public RpcServiceImpl(IRpcDao<K, V> rpcDao) {
		this.rpcDao = rpcDao;
	}

	@Override
	@AuthenticationMethod("添加")
	public V saveAndReturn(V v) throws SharedException,
			ConstraintViolationException {
		return getRpcDao().saveAndReturn(v);
	}

	@Override
	@AuthenticationMethod("添加")
	public K save(V v) throws SharedException, ConstraintViolationException {
		return getRpcDao().save(v);
	}

	@Override
	@AuthenticationMethod("修改")
	public V update(V v) throws SharedException, ConstraintViolationException {
		return getRpcDao().update(v);
	}

	@Override
	@AuthenticationMethod("删除")
	public void delete(List<K> ids) throws SharedException {
		getRpcDao().delete(ids);
	}

	@Override
	@AuthenticationMethod("删除")
	public void delete(Provider<K> k) throws SharedException {
		getRpcDao().delete(k.get());
	}

	@Override
	@AuthenticationMethod("查询")
	public List<V> get() throws SharedException {
		return getRpcDao().get();
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	public IRpcDao<K, V> getRpcDao() {
		return rpcDao;
	}

	@Override
	@AuthenticationMethod("查询")
	public V get(Provider<K> k) throws SharedException {
		return getRpcDao().get(k.get());
	}

	@Override
	@AuthenticationMethod("查询")
	public PagingLoadResult<V> get(EtlPagingLoadConfigBean config)
			throws SharedException {
		PagingLoadResultBean<V> result = (PagingLoadResultBean<V>) getRpcDao()
				.get(config);
		// excludeCollectionCopy(result);
		// TODO
		return result;
	}

	protected void excludeCollectionCopy(PagingLoadResultBean<V> pr) {
		pr.setData(HibernateBeanReplicatorUtil.excludeCollectionCopy(pr
				.getData()));
	}
}
