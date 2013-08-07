/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import xdate.etl.test.entity.Child;
import xdate.etl.test.entity.Parent;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			String file = HibernateUtil.class.getClassLoader().getResource("hibernate_map.xml").getFile();
			AnnotationConfiguration conf = new AnnotationConfiguration();
			conf.addAnnotatedClass(Parent.class).addAnnotatedClass(Child.class);
			
			
//					.addFile(file);
			// Create the SessionFactory from hibernate.cfg.xml
			return conf.configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
