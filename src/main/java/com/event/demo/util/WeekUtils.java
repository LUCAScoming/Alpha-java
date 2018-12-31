package com.event.demo.util;

import java.util.*;

/**
 * 自然周工具类
 *
 * @author xiaoyu.li
 * @Time 2017-04-23 17:21:16
 */
public class WeekUtils {


    public static String dateSplit = "~";

    public static String monthSplitDay = ".";


    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }


    /**
     * 获取当前自然周数（周期从星期一开始）
     *
     * @return
     */
    public static int getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.setMinimalDaysInFirstWeek(7);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * get Calendar of given year
     *
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /**
     * 获取一周的开始时间
     * get start date of given week no of a year
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static Date getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一周的结束时间
     * get end date of given week no of a year
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static Date getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static int getWeekNo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.setMinimalDaysInFirstWeek(7);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取一年的最大周
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNo(c.getTime());
    }

    public static int getLastWeek() {
        Integer week = WeekUtils.getCurrentWeek();
        Integer year = WeekUtils.getCurrentYear();
        Integer lastWeekNo;
        //需要判定是否为新的一年的开始时间
        if (week == 1) {
            year--;
            lastWeekNo = WeekUtils.getMaxWeekNumOfYear(year);
        } else {
            lastWeekNo = week - 1;
        }
        return lastWeekNo;
    }

    public static int getYearOfLastWeek() {
        Integer week = WeekUtils.getCurrentWeek();
        Integer year = WeekUtils.getCurrentYear();
        //需要判定是否为新的一年的开始时间
        if (week == 1) {
            year--;
        } else {
            year = year;
        }
        return year;
    }

    public static int getLastWeek(int week, int year) {
        Integer lastWeekNo;
        //需要判定是否为新的一年的开始时间
        if (week == 1) {
            year--;
            lastWeekNo = WeekUtils.getMaxWeekNumOfYear(year);
        } else {
            lastWeekNo = week - 1;
        }
        return lastWeekNo;
    }

    public static int getYearOfLastWeek(int week, int year) {
        //需要判定是否为新的一年的开始时间
        if (week == 1) {
            year--;
        } else {
            year = year;
        }
        return year;
    }

    public static int getMonth(int week, int year) {
        Date date = getStartDayOfWeekNo(year, week);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Date getCurrentFirstDayOfMonth() {
        int year = WeekUtils.getCurrentYear();
        int week = WeekUtils.getCurrentWeek();
        Date d = WeekUtils.getStartDayOfWeekNo(year, week);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getStartDayTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date getEndDayTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }
    /**
     * 获取上一周第一天和最后一天 返回list 0为第一天 1为最后一天
     *
     * @param
     * @return
     */
    public static List<Date> getLastMonthStartTimeAndEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        //获取本月最后一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 59, 59);
        Date endTime = calendar.getTime();
        //获取本月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        Date startTime = calendar.getTime();
        List<Date> dateList = new ArrayList<>();
        dateList.add(0, startTime);
        dateList.add(1, endTime);
        return dateList;
    }
    public static Date getDateAfterNow(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,days);
        return getEndDayTime(calendar.getTime());
    }
}
