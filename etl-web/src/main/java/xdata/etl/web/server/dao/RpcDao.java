/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public class RpcDao<K extends Serializable, V extends RpcEntity<K>> extends
		HibernateDaoSupport implements IRpcDao<K, V> {

	public final static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	protected Class<V> clazz;

	@SuppressWarnings("unchecked")
	public RpcDao() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<V>) pt.getActualTypeArguments()[1];
	}

	public void validateBean(V t) {
		return;
		/*
		 * Set<ConstraintViolation<V>> violations = validator.validate(t); if
		 * (!violations.isEmpty()) { Set<ConstraintViolation<?>> temp = new
		 * HashSet<ConstraintViolation<?>>( violations); throw new
		 * ConstraintViolationException(temp); }
		 */
	}

	@Override
	public V saveAndReturn(V v) throws SharedException {
		validateBean(v);
		Session s = getSession();
		s.persist(v);
		return v;
	}

	@Override
	public K save(V v) throws SharedException {
		v = saveAndReturn(v);
		return v.getId();
	}

	@Override
	public void update(V v) throws SharedException {
		validateBean(v);
		Session s = getSession();
		s.update(v);
	}

	@Override
	public void delete(List<K> ids) throws SharedException {
		Session s = getSession();
		for (K id : ids) {
			@SuppressWarnings("unchecked")
			V lt = (V) s.load(clazz, id);
			s.delete(lt);
		}

	}

	@Override
	public void delete(Provider<K> k) throws SharedException {
		Session s = getSession();
		@SuppressWarnings("unchecked")
		V lt = (V) s.load(clazz, k.get());
		System.out.println(k.get());
		s.delete(lt);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V> get() throws SharedException {
		Criteria criteria = findGetCriteria();
		return criteria.list();
	}

	public Criteria findGetCriteria() {
		return getSession().createCriteria(clazz);
	}

	@Resource
	public void setSessionFactory2(SessionFactory sf) {
		super.setSessionFactory(sf);
	}

}
