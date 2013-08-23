/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class HibernateDaoSupport extends
		org.springframework.orm.hibernate3.support.HibernateDaoSupport {
	public HibernateDaoSupport() {
	}

	@Resource
	public void setSessionFactory2(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
}
