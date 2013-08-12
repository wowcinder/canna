/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class Test {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	@Inherited
	public @interface KK {

	}

	@KK
	public void test() {

	}

	public static void main(String[] args) {
		for (Method method : Test.class.getMethods()) {
			System.out.println(method.getName());
			System.out.println(method.isAnnotationPresent(KK.class));
		}
	}

}
