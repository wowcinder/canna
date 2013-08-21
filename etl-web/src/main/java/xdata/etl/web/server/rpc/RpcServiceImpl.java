/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.service.RpcDelegateService;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;
import xdata.etl.web.shared.service.RpcService;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public class RpcServiceImpl<K extends Serializable, V extends RpcEntity<K>, T extends RpcDelegateService<K, V>>
		implements RpcService<K, V> {
	protected T delegateService;

	public RpcServiceImpl() {
	}

	@Override
	@AccessAuthority("添加")
	public V saveAndReturn(V v) throws SharedException,
			ConstraintViolationException {
		return getDelegateService().saveAndReturn(v);
	}

	@Override
	@AccessAuthority("添加")
	public K save(V v) throws SharedException, ConstraintViolationException {
		return getDelegateService().save(v);
	}

	@Override
	@AccessAuthority("修改")
	public V update(V v) throws SharedException, ConstraintViolationException {
		return getDelegateService().update(v);
	}

	@Override
	@AccessAuthority("删除")
	public void delete(List<K> ids) throws SharedException {
		getDelegateService().delete(ids);
	}

	@Override
	@AccessAuthority("删除")
	public void delete(Provider<K> k) throws SharedException {
		getDelegateService().delete(k.get());
	}

	@Override
	@AccessAuthority("查询")
	public List<V> get() throws SharedException {
		return getDelegateService().get();
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AccessAuthority("查询")
	public V get(Provider<K> k) throws SharedException {
		return getDelegateService().get(k.get());
	}

	@Override
	@AccessAuthority("查询")
	public PagingLoadResult<V> get(EtlPagingLoadConfigBean config)
			throws SharedException {
		PagingLoadResultBean<V> pr = (PagingLoadResultBean<V>) getDelegateService().get(
				config);
		return pr;
	}

	public T getDelegateService() {
		return delegateService;
	}

	@Autowired
	public void setDelegateService(T delegateService) {
		this.delegateService = delegateService;
	}

}
