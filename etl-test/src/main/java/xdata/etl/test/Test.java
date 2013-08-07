/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.test;

import org.hibernate.Session;

import xdate.etl.test.entity.Child;
import xdate.etl.test.entity.Parent;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public class Test {
	public static void main(String[] args) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		
		Parent p = new Parent();
		p.setName("test");
		s.persist(p);
		
		Child c = new Child();
		c.setParent(p);
		
		s.persist(c);
		
		
		
		s.getTransaction().commit();
	}
}
