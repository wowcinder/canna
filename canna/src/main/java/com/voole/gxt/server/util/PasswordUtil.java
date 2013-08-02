package com.voole.gxt.server.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {
	public static final String HEX_STR = "jsldjfjsld";

	public static String getMd5Hex(String password) {
		return DigestUtils.md5Hex(password + HEX_STR);
	}

	public static boolean isRightPassword(String password, String hexStr) {
		return getMd5Hex(password).equals(hexStr);
	}	
}
