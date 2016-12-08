package com.ruzhi.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ruzhi
 */
public class TimeUtils {

	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的时间 转化为 秒数,比如：2014-09-23 10:45:26 ----->   1411440326000
	 *
	 * @param position
	 * @return
	 * @throws ParseException
	 */
	public static long dateFormat2MillSecond(String timeStr) throws ParseException {
		/**
		 * 将字符串数据转化为秒数
		 */
		Date date = string2Date(timeStr, "yyyy-MM-dd HH:mm:ss");
		return date.getTime();

	}

	/*
	 * 将毫秒数转化为时间
	 */
	public static String second2DateFormat(long second) {
		Date nowTime = new Date(second);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

	/*
	 * 日期转字符串
	 */
	public static String date2String(Date date){
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(new Date());
		return str;
	}

	/*
	 * 字符串转日期
	 */
	public static Date string2Date(String dateStr,String formatStr) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat(formatStr);//"yyyy-MM-dd HH:mm:ss"
		Date date = sdf.parse(dateStr);
		return date;
	}

	public static void main(String args[]) {
		try {
			dateFormat2MillSecond("2014-09-23 10:45:26");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
