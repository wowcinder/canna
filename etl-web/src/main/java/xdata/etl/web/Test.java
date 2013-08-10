/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web;

import java.lang.reflect.ParameterizedType;

import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class Test {

	public static void main(String[] args) {
		EntityRpcCaller<Integer, Menu> test = new EntityRpcCaller<Integer, Menu>(){};
		ParameterizedType type = (ParameterizedType)test.getClass().getGenericSuperclass();
		System.out.println(type.getActualTypeArguments()[0]);
		
	}

}
