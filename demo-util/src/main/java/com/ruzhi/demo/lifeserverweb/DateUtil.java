package com.ruzhi.demo.lifeserverweb;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static Logger log = LoggerFactory.getLogger(DateUtil.class) ;
	public static final int INTERVAL_DAY = 1;
	public static final int INTERVAL_WEEK = 2;
	public static final int INTERVAL_MONTH = 3;
	public static final int INTERVAL_YEAR = 4;
	public static final int INTERVAL_HOUR = 5;
	public static final int INTERVAL_MINUTE = 6;
	public static final int INTERVAL_SECOND = 7;
	public static final Date tempDate = new Date(new Long("-2177481952000").longValue());

	@SuppressWarnings("deprecation")
	public static boolean isToday(Date date) {
		Date now = new Date();
		boolean result = true;
		result &= date.getYear() == now.getYear();
		result &= date.getMonth() == now.getMonth();
		result &= date.getDate() == now.getDate();
		return result;
	}

	/**
	 * 取两个时间的秒差(date1 - date2)
	 * @param date1
	 * @param date2 null则为当前时间
	 * @return 负数则说明date2 > date1
	 */
	public static long dateBetween(Date date1, Date date2) {
		if (date2 == null){
			date2 = new Date();
		}

		return (date1.getTime() - date2.getTime())/1000L;
	}

	/**
	 * date1 在date2 之前为true
	 */
	public static boolean compareDate(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			return !d1.after(d2);
		} catch (ParseException e) {
			log.error("DateUtil.compareDate", e);
		}
		return false;
	}
	public static boolean isExpiredDay(Date date1) {
		long day = (new Date().getTime() - date1.getTime()) / 86400000L;

		return day >= 1L;
	}


	public static Date dateFormat(String date, String dateFormat) {
		if (date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if (date != null)
			try {
				return format.parse(date);
			} catch (Exception ex) {
				log.error("DateUtil.dateFormat", ex);
			}
		return null;
	}

	public static Date dateFormat(String date) {
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateFormat(Date date, String dateFormat) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}

	public static String dateFormat(Date date) {
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateFormatForTemplate (String datestr) {
		if (StringUtils.isBlank(datestr)) {
			return "";
		}
		Date date = dateFormat(datestr, "yyyyMMddHHmmss");
		return dateFormat(date, "yyyy-MM-dd HH:mm");
	}
	public static String getToday(String format) {
		String result = "";
		try {
			Date today = new Date();
			SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
			result = simpleFormat.format(today);
		} catch (Exception e) {
		}
		return result;
	}

	public static Date getToday() {
		Date today = new Date();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			today = format.parse(format.format(today));
		} catch (Exception e) {
		}
		return today;
	}

	public static Date getYesterday() {
		Date date = new Date();
		long time = date.getTime() / 1000L - 86400L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	public static Date getTomorrow() {
		Date date = new Date();
		long time = date.getTime() / 1000L + 86400L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	public static Date getWeekAgo() {
		Date date = new Date();
		long time = date.getTime() / 1000L - 604800L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	public static String getDaysAgo(int interval) {
		Date date = new Date();
		long time = date.getTime() / 1000L - interval * 60 * 60 * 24;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.format(date);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	public static String getDaysAgo1(int interval) {
		Date date = new Date();
		long time = date.getTime() / 1000L - interval * 60 * 60 * 24;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.format(date);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}
	public static Date nextDay(Date date) {
		if(date == null){
			return null;
		}

		Date newDate = (Date) date.clone();
		long time = newDate.getTime() / 1000L + 86400L;
		newDate.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newDate = format.parse(format.format(newDate));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return newDate;
	}

	public static Date getThisWeekStartTime() {
		Calendar today = Calendar.getInstance();
		today.set(7, today.getFirstDayOfWeek());
		Calendar weekFirstDay = Calendar.getInstance();
		weekFirstDay.clear();
		weekFirstDay.set(1, today.get(1));
		weekFirstDay.set(2, today.get(2));
		weekFirstDay.set(5, today.get(5));
		return weekFirstDay.getTime();
	}

	public static Date getDateByUnixTime(int unixTime) {
		return new Date(unixTime * 1000L);
	}

	public static int getUnixTimeByDate(Date date) {
		return (int) (date.getTime() / 1000L);
	}

	/**
	 * 时间增减
	 * @param interval 见静态变量
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date dateAdd(int interval, Date date, int n) {
		long time = date.getTime() / 1000L;
		switch (interval) {
			case INTERVAL_DAY:
				time += n * 86400;
				break;
			case INTERVAL_WEEK:
				time += n * 604800;
				break;
			case INTERVAL_MONTH:
				time += n * 2678400;
				break;
			case INTERVAL_YEAR:
				time += n * 31536000;
				break;
			case INTERVAL_HOUR:
				time += n * 3600;
				break;
			case INTERVAL_MINUTE:
				time += n * 60;
				break;
			case INTERVAL_SECOND:
				time += n;
				break;
		}

		Date result = new Date();
		result.setTime(time * 1000L);
		return result;
	}

	/**
	 * 时间对比
	 * @param interval 见静态变量
	 * @param date
	 * @param n
	 * @return
	 */
	public static int dateDiff(int interval, Date begin, Date end) {
		long beginTime = begin.getTime() / 1000L;
		long endTime = end.getTime() / 1000L;
		long tmp = 0L;
		if (endTime == beginTime) {
			return 0;
		}

		if (endTime < beginTime) {
			tmp = beginTime;
			beginTime = endTime;
			endTime = tmp;
		}

		long intervalTime = endTime - beginTime;
		long result = 0L;
		switch (interval) {
			case INTERVAL_DAY:
				result = intervalTime / 86400L;
				break;
			case INTERVAL_WEEK:
				result = intervalTime / 604800L;
				break;
			case INTERVAL_MONTH:
				result = intervalTime / 2678400L;
				break;
			case INTERVAL_YEAR:
				result = intervalTime / 31536000L;
				break;
			case INTERVAL_HOUR:
				result = intervalTime / 3600L;
				break;
			case INTERVAL_MINUTE:
				result = intervalTime / 60L;
				break;
			case INTERVAL_SECOND:
				result = intervalTime / 1L;
				break;
		}

		if (tmp > 0L) {
			result = 0L - result;
		}
		return (int) result;
	}


	public static Date getNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateStr = dateFormat(date);
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			log.error("DateUtil.getNowTime", e);
		}
		return date;
	}


	/**
	 * 获取小时
	 * @param date Date
	 * @return int
	 */
	public static final int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取月份
	 * @param date Date
	 * @return int
	 */
	public static final int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取月份
	 * @param date Date
	 * @return int
	 */
	public static final int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE) + 1;
	}

	/**
	 * 获取秒
	 * @param date Date
	 * @return int
	 */
	public static final int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND) + 1;
	}

	/**
	 * 获取年份
	 * @param date Date
	 * @return int
	 */
	public static final int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取日期
	 * @param date Date
	 * @return int
	 */
	public static final int getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}


}
