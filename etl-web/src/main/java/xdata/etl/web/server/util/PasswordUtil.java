/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public class PasswordUtil {
	public static String getHexPassword(String password) {
		return DigestUtils.md5Hex("xdata.etl.web" + password);
	}
}
