package org.masque.qq.demo.util;

import java.util.UUID;

/**
 * 
 * <p>
 * Project: Masque's Base
 * </p>
 * <p>
 * Description: 字符串的工具类
 * </p>
 * <p>
 * Copyright (c) 2014 Masque.All Rights Reserved.
 * </p>
 * 
 * @author <a href="masque.java@gmail.com">Masque</a>
 */
public class StringUtils {

	public static final String SET_STR = "set";

	public static final String GET_STR = "get";

	public static final String[] A2Str = new String[] { "00", "11", "22", "33",
			"44", "55", "66", "77", "88", "99" };

	public static final String[] A4Str = new String[] { "0000", "1111", "2222",
			"3333", "4444", "5555", "6666", "7777", "8888", "9999" };

	public static final String[] A5Str = new String[] { "00000", "11111",
			"22222", "33333", "44444", "55555", "66666", "77777", "88888",
			"99999" };

	public static final String[] A6Str = new String[] { "000000", "111111",
			"222222", "333333", "444444", "555555", "666666", "777777",
			"888888", "999999" };

	public static final String[] A7Str = new String[] { "0000000", "1111111",
			"2222222", "3333333", "4444444", "5555555", "6666666", "7777777",
			"8888888", "9999999" };

	public static final String[] A8Str = new String[] { "00000000", "11111111",
			"22222222", "33333333", "44444444", "55555555", "66666666",
			"77777777", "88888888", "99999999" };

	public static final String[] A9Str = new String[] { "000000000",
			"111111111", "222222222", "333333333", "444444444", "555555555",
			"666666666", "777777777", "888888888", "999999999" };

	public static final String[] A10Str = new String[] { "1111111111",
			"2222222222", "3333333333", "4444444444", "5555555555",
			"6666666666", "7777777777", "8888888888", "9999999999" };

	public static final String[] ABCDStr = new String[] { "0123", "1234",
			"3456", "4567", "5678", "6789", "7890", "0987", "9876", "8765",
			"7654", "6543", "5432", "4321", "3210" };

	public static final String[] AAAStr = new String[] { "000", "111", "222",
			"333", "444", "555", "666", "777", "888", "999" };

	public static final String[] ABCDEStr = new String[] { "01234", "12345",
			"23456", "34567", "45678", "56789", "67890", "09876", "98765",
			"87654", "76543", "65432", "54321", "43210" };

	public static final String[] ABCDEFStr = new String[] { "012345", "123456",
			"234567", "345678", "456789", "987654", "876543", "765432",
			"654321", "543210" };

	public static final String[] ABCDEFGStr = new String[] { "0123456",
			"1234567", "2345678", "3456789", "9876543", "8765432", "7654321",
			"6543210" };

	public static final String[] ABCDEFGHStr = new String[] { "01234567",
			"12345678", "23456789", "98765432", "87654321", "76543210" };

	public static final String[] ABCDEFGHIStr = new String[] { "012345678",
			"123456789", "987654321", "876543210" };

	public static final String[] ABCDEFGHIJStr = new String[] { "0123456789",
			"9876543210" };

	public static final String[] BAD_WORD = new String[] { "傻逼", "逗比" };

	private StringUtils() {
	}

	public static String getUUIDStr() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getMD5Str(String str) {
		return null;
	}

	public static boolean isAAAA(String str) {
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return false;
		}
		for (String query : A4Str) {
			if (query.equals(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFormatStr(String str) {
		return true;
	}

	public static String replaceBadWord(String str) {
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return "";
		}
		return null;
	}

	/**
	 * replace the char like 1,@,{,[,',+,A,e example:
	 * replaceNoChinese(",您a$好#5{}.;比"); will be return "您好"
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceNoChinese(String str) {
		return str.replaceAll("[^\u4E00-\u9FA5]", "");
	}

	public static void main(String[] args) {
		System.out.println("  000 ");
		System.out.println("\b");
		
		System.out.println(org.apache.commons.lang.StringUtils.trim("  000 "));
		System.out.println(org.apache.commons.lang.StringUtils.trim("     \b \t \n \f \r    "));
		System.out.println(org.apache.commons.lang.StringUtils.trim("     \b \t \n \f    "));
		System.out.println(org.apache.commons.lang.StringUtils.trim("     \b \t \n    "));
		System.out.println(org.apache.commons.lang.StringUtils.trim("     \b \t    "));
		System.out.println(org.apache.commons.lang.StringUtils.trim("     \b    ").equals("\b"));
		
		System.out.println(org.apache.commons.lang.StringUtils.strip("  000 "));
		System.out.println(org.apache.commons.lang.StringUtils.strip("     \b \t \n \f \r    "));
		System.out.println(org.apache.commons.lang.StringUtils.strip("     \b \t \n \f    "));
		System.out.println(org.apache.commons.lang.StringUtils.strip("     \b \t \n    "));
		System.out.println(org.apache.commons.lang.StringUtils.strip("     \b \t    "));
		System.out.println(org.apache.commons.lang.StringUtils.strip("     \b    ").equals("\b"));
		
		System.out.println(org.apache.commons.lang.StringUtils.strip("  abcyx", "xyz"));
	}
}
