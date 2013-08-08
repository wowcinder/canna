/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.SessionFactory;

import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public interface IRpcDao<K extends Serializable, V extends RpcEntity<K>> {

	V saveAndReturn(V v) throws SharedException, ConstraintViolationException;

	K save(V v) throws SharedException, ConstraintViolationException;

	void update(V v) throws SharedException, ConstraintViolationException;

	void delete(List<K> ids) throws SharedException;

	void delete(K k) throws SharedException;

	List<V> get() throws SharedException;

	V get(K k) throws SharedException;

	public SessionFactory getSessionFactory();
}
