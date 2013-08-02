package com.voole.gxt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class TestScanPackage {
	public static void main(String[] args) {
		// Reflections reflections = new Reflections(
		// "com.voole.gxt.client.service");
		// Set<Class<? extends RemoteService>> subTypes = reflections
		// .getSubTypesOf(RemoteService.class);
		//
		// for (Class<? extends RemoteService> class1 : subTypes) {
		// for (Method m : class1.getMethods()) {
		// Class<?>[] pTypes = m.getParameterTypes();
		// List<String> parStr = new ArrayList<String>();
		// for (int i = 0; i < pTypes.length; i++) {
		// parStr.add((pTypes[i]).getSimpleName());
		// }
		//
		// System.out.println(m.getName() + "("
		// + StringUtils.join(parStr, ',') + ")");
		// }
		// }
		try {
			String test = "sdfsd";
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String result = new String(md5.digest(test.getBytes("UTF-8")),"UTF-8");
			System.out.println(result);
			
			System.out.println(DigestUtils.md5Hex(test));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
