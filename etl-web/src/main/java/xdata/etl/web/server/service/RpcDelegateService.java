package xdata.etl.web.server.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface RpcDelegateService<K extends Serializable, V extends RpcEntity<K>> {

	V saveAndReturn(V v) throws SharedException, ConstraintViolationException;

	K save(V v) throws SharedException, ConstraintViolationException;

	V update(V v) throws SharedException, ConstraintViolationException;

	void delete(List<K> ids) throws SharedException;

	void delete(K k) throws SharedException;

	List<V> get() throws SharedException;

	V get(K k) throws SharedException;

	PagingLoadResult<V> get(EtlPagingLoadConfigBean config)
			throws SharedException;

	ValidationSupport dummy();
}
