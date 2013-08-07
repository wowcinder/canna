/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.user;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
public class User extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -1801720788002068921L;

	private String email;
	private String password;
	
	private UserGroup userGroup;
	

}
