package xdata.etl.web.client.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

public interface RpcService<K extends Serializable, V extends RpcEntity<K>> {

	V saveAndReturn(V v) throws SharedException, ConstraintViolationException;

	K save(V v) throws SharedException, ConstraintViolationException;

	void update(V v) throws SharedException, ConstraintViolationException;

	void delete(List<K> ids) throws SharedException;

	void delete(Provider<K> k) throws SharedException;

	List<V> get() throws SharedException;

	ValidationSupport dummy();
}