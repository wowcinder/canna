/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public class RpcDelegateServiceImpl<K extends Serializable, V extends RpcEntity<K>, T extends IRpcDao<K, V>>
		implements RpcDelegateService<K, V> {
	protected T dao;

	public RpcDelegateServiceImpl() {
	}

	@Override
	@AccessAuthority("添加")
	public V saveAndReturn(V v) throws SharedException,
			ConstraintViolationException {
		return getDao().saveAndReturn(v);
	}

	@Override
	@AccessAuthority("添加")
	public K save(V v) throws SharedException, ConstraintViolationException {
		return getDao().save(v);
	}

	@Override
	@AccessAuthority("修改")
	public V update(V v) throws SharedException, ConstraintViolationException {
		return getDao().update(v);
	}

	@Override
	@AccessAuthority("删除")
	public void delete(List<K> ids) throws SharedException {
		getDao().delete(ids);
	}

	@Override
	@AccessAuthority("删除")
	public void delete(K k) throws SharedException {
		getDao().delete(k);
	}

	@Override
	@AccessAuthority("查询")
	public List<V> get() throws SharedException {
		return getDao().get();
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	public T getDao() {
		return dao;
	}

	@Autowired
	public void setDao(T dao) {
		this.dao = dao;
	}

	@Override
	@AccessAuthority("查询")
	public V get(K k) throws SharedException {
		return getDao().get(k);
	}

	@Override
	@AccessAuthority("查询")
	public PagingLoadResult<V> get(EtlPagingLoadConfigBean config)
			throws SharedException {
		PagingLoadResultBean<V> pr = (PagingLoadResultBean<V>) getDao().get(
				config);
		return pr;
	}

}
