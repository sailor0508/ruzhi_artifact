package com.ruzhi.demo.util;

/*
 * there is a Package conflicts when the TBStringUtil class be used,so rewrite it
 */
public class PrivateTbStringUtil {

	/**
	 * 转换字符串为long
	 *
	 * @param s
	 * @param def
	 *
	 * @return
	 */
	public static long getLong(String s, long def) {
		long i = def;

		try {
			i = Long.parseLong(s);
		} catch (NumberFormatException e) {
			// ignore
		}

		return i;
	}

	/**
	 * 转换字符串为int
	 *
	 * @param s
	 * @param def
	 *
	 * @return
	 */
	public static int getInt(String s, int def) {
		int i = def;

		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			// ignore
		}

		return i;
	}

	/**
	 * 转换字符串为double
	 *
	 * @param s
	 * @param def
	 *
	 * @return
	 */
	public static double getDouble(String s, int def) {
		double i = def;
		if(s == null || s.trim().length()==0){
			return i;
		}
		try {
			i = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			// ignore
		}

		return i;
	}
}