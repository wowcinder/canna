package com.voole.gxt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;

public interface CannaService<T> {
	T saveReturnObjct(T t) throws CannaException, ConstraintViolationException;

	Long save(T t) throws CannaException, ConstraintViolationException;

	void update(T t) throws CannaException, ConstraintViolationException;

	void delete(List<Long> ids) throws CannaException;

	void delete(Long id) throws CannaException;

	List<T> get() throws CannaException;

	PagingLoadResult<T> get(CannaPagingLoadConfigBean config)
			throws CannaException;

	ValidationSupport dummy();
}
