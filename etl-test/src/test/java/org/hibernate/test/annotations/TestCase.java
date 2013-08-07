/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package org.hibernate.test.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public abstract class TestCase extends junit.framework.TestCase {

	private SessionFactory sf;
	private Configuration conf;
	
	@Override
	protected void setUp() throws Exception {
		beforeClass();
	}
	
	@Override
	protected void tearDown() throws Exception {
		afterClass();
	}

	public void beforeClass() {
		AnnotationConfiguration conf = new AnnotationConfiguration();
		for (Class<?> clazz : getAnnotatedClasses()) {
			conf.addAnnotatedClass(clazz);
		}
		this.conf = conf;
		sf = conf.configure().buildSessionFactory();
	}

	public Session openSession() {
		return sf.openSession();
	}

	public void afterClass() {
		sf.close();
	}
	
	public Configuration getCfg(){
		return this.conf;
	}

	protected abstract Class[] getAnnotatedClasses();

}
