/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.testweb.server.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
public class BaseDao extends HibernateDaoSupport {
	public BaseDao() {
	}

	@Autowired
	public void setSessionFactory2(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
}
