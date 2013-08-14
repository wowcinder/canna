/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author XuehuiHe
 * @date 2013年8月6日
 */
public class AuthorityUtil {
	public static String getToken(String authorityGroupName,
			String authorityName) {
		return DigestUtils.md5Hex(DigestUtils.md5Hex(authorityGroupName)
				+ DigestUtils.md5Hex(authorityName));
	}
	public static void main(String[] args) {
		System.out.println(getToken("菜单","查询"));
	}
}
