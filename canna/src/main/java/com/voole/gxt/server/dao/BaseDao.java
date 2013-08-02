package com.voole.gxt.server.dao;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport {
	public final static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	@Resource(name = "cannaSessionFactory")
	public void setSessionFactory2(SessionFactory sf) {
		super.setSessionFactory(sf);
	}

}
