package com.lami.tarsier.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	private static SimpleDateFormat formatter;

	public DateUtil() {
	}
	public static String yearMonth(Date aDate) {
		formatter = new SimpleDateFormat("yyyyMM");
		return formatter.format(aDate);
	}
	public static String shortDate(Date aDate) {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(aDate);
	}

	public static String mailDate(Date aDate) {
		formatter = new SimpleDateFormat("yyyyMMddHHmm");
		return formatter.format(aDate);
	}
	public static String mailSecondDate(Date aDate) {
		formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(aDate);
	}

	public static String longDate(Date aDate) {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(aDate);
	}
	public static String noSecondDate(Date aDate) {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(aDate);
	}	

	public static String longDateGB(Date aDate) {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(aDate);
	}
	public static String longDateGBK(Date aDate) {
		formatter = new SimpleDateFormat("yyyy年MM月dd日");
		return formatter.format(aDate);
	}

	public static String formatDate(Date aDate, String formatStr) {
		formatter = new SimpleDateFormat(formatStr);
		return formatter.format(aDate);

	}

	public static String LDAPDate(Date aDate) {
		return formatDate(aDate, "yyyyMMddHHmm'Z'");
	}

	public static Date getDate(String yyyymmdd) {
		if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0))
			return null;
		int year = Integer.parseInt(yyyymmdd
				.substring(0, yyyymmdd.indexOf("-")));
		int month = Integer.parseInt(yyyymmdd.substring(
				yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
		int day = Integer.parseInt(yyyymmdd
				.substring(yyyymmdd.lastIndexOf("-") + 1));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();

	}

	public static Date getDateFull(String yyyymmdd) {
		if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0))
			return null;
		int year = Integer.parseInt(yyyymmdd
				.substring(0, yyyymmdd.indexOf("-")));
		int month = Integer.parseInt(yyyymmdd.substring(
				yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
		int day = Integer.parseInt(yyyymmdd
				.substring(yyyymmdd.lastIndexOf("-") + 1));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 24, 60, 60);
		return cal.getTime();

	}

	public static Date parser(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parser(String strDate, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * get Date with only date num. baoxy add
	 * 
	 * @param yyyymmdd
	 * @return
	 */
	public static Date getDateOnly(String yyyymmdd) {
		if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0))
			return null;
		int year = Integer.parseInt(yyyymmdd
				.substring(0, yyyymmdd.indexOf("-")));
		int month = Integer.parseInt(yyyymmdd.substring(
				yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
		int day = Integer.parseInt(yyyymmdd
				.substring(yyyymmdd.lastIndexOf("-") + 1));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0);
		return cal.getTime();
	}

	public static Date addMonth(Date myDate, int amount) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(myDate);
		boolean isEndDayOfMonth_old = cal
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
				.get(GregorianCalendar.DAY_OF_MONTH);
		cal.add(GregorianCalendar.MONTH, amount);
		boolean isEndDayOfMonth_new = cal
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
				.get(GregorianCalendar.DAY_OF_MONTH);
		if (isEndDayOfMonth_old && !isEndDayOfMonth_new) {
			cal.set(GregorianCalendar.DATE, cal
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		}
		return cal.getTime();
	}

	public static Date addDay(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	public static Date addYear(Date myDate, int amount) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(myDate);
		boolean isEndDayOfMonth_old = cal
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
				.get(GregorianCalendar.DAY_OF_MONTH);
		cal.add(GregorianCalendar.YEAR, amount);
		boolean isEndDayOfMonth_new = cal
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
				.get(GregorianCalendar.DAY_OF_MONTH);
		if (isEndDayOfMonth_old && !isEndDayOfMonth_new) {
			cal.set(GregorianCalendar.DATE, cal
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		}
		return cal.getTime();
	}

	public static Date getFirstDay(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return parser(formatDate(cale.getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/*
	 * the mapping from jdk is : 1-Sun; 2-Mon;3-Tues; 4-Weds; 5-Thur;6-Fri;
	 * 7-Sat;
	 */
	public static int getWeekDay(Date myDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(myDate);
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}

	/*
	 * the mapping from vas2005 is : 1-Mon;2-Tues; 3-Weds; 4-Thur;5-Fri;
	 * 6-Sat;7-Sun;
	 */
	public static int getConvertWeekDay(Date myDate) {
		int day = getWeekDay(myDate);
		int result = day - 1;
		if (result == 0)
			result = 7;
		return result;
	}

	public static int getTimeFromDate(Date myDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
		int result = Integer.parseInt(sdf.format(myDate));
		return result;
	}
	
	public static long previousXMonth(long current, int count) {
	    Calendar aCalendar = Calendar.getInstance();
	    aCalendar.setTime(new Date(current));
	    aCalendar.getTime();
	    aCalendar.add(Calendar.MONTH, -count);
	    return aCalendar.getTime().getTime();
	  }
	public static long nextXMonth(long current, int count) {
	    Calendar aCalendar = Calendar.getInstance();
	    aCalendar.setTime(new Date(current));
	    aCalendar.getTime();
	    aCalendar.add(Calendar.MONTH, count);
	    return aCalendar.getTime().getTime();
	  }
}
