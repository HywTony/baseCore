package com.youdfu.basecorehelper.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static SimpleDateFormat sdf_long = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
	public static SimpleDateFormat sdf_simple = new SimpleDateFormat("HH:mm",
			Locale.CHINA);

	public static String DateToString(Date date, String format) {
		return new SimpleDateFormat(format, Locale.CHINA).format(date);
	}

	public static String DateToString(Date date, SimpleDateFormat sformat) {
		return sformat.format(date);
	}

	public static String DateToString(Date date) {
		return sdf.format(date);
	}

	public static String DateToLongString(Date date) {
		return sdf_long.format(date);
	}

	public static Date StringToDate(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date StringToDate(String date, String sdfs) {
		SimpleDateFormat sdf = new SimpleDateFormat(sdfs, Locale.CHINA);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date LongStringToDate(String date) {
		try {
			return sdf_long.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 数字转字符 转为00:00
	 */
	public static String digitToTimeString(double digit) {
		int h = (int) digit;
		int min = (int) ((digit - h) * 60);
		return (h > 9 ? "" : "0") + h + ":" + (min > 9 ? "" : "0") + min;
	}

	/**
	 * 数字转字符 转为0:00:00
	 */
	public static String digitToTimeStringHMinS(double digit) {
		int h = (int) digit;
		int min = (int) ((digit - h) * 60);
		int second = (int) ((digit * 3600) % 60);
		return h + ":" + (min > 9 ? "" : "0") + min + ":"
				+ (second > 9 ? "" : "0") + second;
	}

	// 判断邮箱格式是否符合
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
