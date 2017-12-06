package com.youdfu.basecorehelper.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static Calendar calday = Calendar.getInstance();

    private static int getSundyPlus(Calendar cd) {
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        // 1,2,3,4,5,6,7

        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    // 获得本周星期一的日期
    public static Calendar getCurrentMonday(Calendar cd) {
        int mondayPlus = getSundyPlus(cd);
        cd.add(Calendar.DATE, mondayPlus);
        return cd;
    }

    public static long DateDifference(Date firstday, Date secondday) {
        Calendar c = Calendar.getInstance();

        c.setTime(firstday);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long l1 = c.getTimeInMillis();

        c.setTime(secondday);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long l2 = c.getTimeInMillis();

        return (l1 - l2) / (1000 * 60 * 60 * 24); // 这个值就是相差的天数了
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static void setFirstDayOfWeek(Calendar c, Locale locale, int year, int week) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);

        if (Locale.CHINA.equals(locale)) {
            // 国内
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置周一
        } else {
            // 国外
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 设置周日
        }

        c.setFirstDayOfWeek(Calendar.MONDAY);
    }

    /**
     * 获取给定日期的零点时间
     *
     * @param cal
     * @param baseRtc
     */
    public static void setZeroTimeOfDay(Calendar cal, long baseRtc) {
        cal.setTimeInMillis(baseRtc);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 获取当前日期的零点时间
     *
     * @return
     */
    public static Calendar getZeroTimeOfToday() {
        Calendar c = Calendar.getInstance();
        setZeroTimeOfDay(c, new Date().getTime());
        return c;
    }

    /**
     * 获取某月的总天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthTotalDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
