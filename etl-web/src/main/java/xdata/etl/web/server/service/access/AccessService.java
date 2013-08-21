/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.access;

import org.aspectj.lang.JoinPoint;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public interface AccessService {
	public boolean isAccess(JoinPoint jp);
}
