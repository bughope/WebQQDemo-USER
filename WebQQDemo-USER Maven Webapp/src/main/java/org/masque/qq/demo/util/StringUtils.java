package org.masque.qq.demo.util;

import java.util.UUID;

/**
 * 
 * <p>Project: Masque's Base</p>
 * <p>Description: 字符串的工具类</p>
 * <p>Copyright (c) 2014 Masque.All Rights Reserved.</p>
 * @author <a href="masque.java@gmail.com">Masque</a>
 */
public class StringUtils {

	public static final String SET_STR = "set";

	public static final String GET_STR = "get";

	public static final String[] AAAAStr = new String[] { "0000", "1111",
			"2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999" };

	public static final String[] ABCDStr = new String[] { "0123", "1234",
			"3456", "4567", "5678", "6789", "7890", "0987", "9876", "8765",
			"7654", "6543", "5432", "4321", "3210" };

	public static final String[] AAAStr = new String[] { "000", "111", "222",
			"333", "444", "555", "666", "777", "888", "999", "000" };

	private StringUtils() {
	}

	public static String getUUIDStr() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getMD5Str(String str) {
		return null;
	}
	
	public static boolean isAAAA(String str){
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return false;
		}
		for(String query:AAAAStr){
			if (query.equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isFormatStr(String str){
		return true;
	}
}
