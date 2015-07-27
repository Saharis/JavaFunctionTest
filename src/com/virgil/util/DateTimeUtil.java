package com.virgil.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.http.util.TextUtils;

/**
 * DateTimeUtil
 * Created by liuwujing on 15/4/10.
 */
public class DateTimeUtil {

    /**
     * 默认日期格式：yyyyMMdd
     */
    public static String DATE_YYYYMMDD = "yyyyMMdd";
    /**
     * 默认日期格式：yyyyMMddHHmmss
     */
    public static String DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String DATE_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static String last_format_type = "";
    public static SimpleDateFormat last_format;

    /**
     * 返回当前日期，格式为：yyyyMMDD
     *
     * @return String|当前日期
     */
    public static String getCurrentDate() {
        return getCurrentDate(DATE_YYYYMMDD);
    }

    /**
     * 返回当前日期，格式为传入的format_type
     *
     * @param format_type String
     * @return String|当前日期
     */
    public static String getCurrentDate(String format_type) {
        if (!TextUtils.isEmpty(last_format_type)) {
            if (last_format_type.equals(format_type) && last_format != null) {
                return last_format.format(new Date());
            }
        }
        SimpleDateFormat format = new SimpleDateFormat(format_type, Locale.SIMPLIFIED_CHINESE);
        last_format_type = format_type;
        last_format = format;
        return format.format(new Date());
    }

    /**
     * 返回当前的日期时间，格式为：yyyyMMDDHHmmss
     *
     * @return String|当前时间
     */
    public static String getCurrentDateTime() {
        return getCurrentDate(DATE_YYYYMMDDHHMMSS);
    }

    public static String getCurrentDateTime(String format_type) {
        return getCurrentDate(format_type);
    }

    /**
     * 计算第二个日期减去第一个日期的得到的天数，日期必须为{@link #DATE_YYYYMMDD}格式
     *
     * @param dateStr1 String|日期1，必须为yyyyMMDD格式
     * @param dateStr2 String|日期2，必须为yyyyMMDD格式
     * @return long|天
     */
    public static long compareTwoDate(String dateStr1, String dateStr2) {
        long minus = compareDate(dateStr1, dateStr2, DATE_YYYYMMDD) / (24 * 3600);
        return minus;
    }


    /**
     * 第二个日期减去第一个日期得到的秒数，日期必须为{@link #DATE_YYYYMMDDHHMMSS}格式
     *
     * @param dateStr1 String|日期1
     * @param dateStr2 String|日期2
     * @return long|秒
     */
    public static long compareTwoDateTime(String dateStr1, String dateStr2) {
        long minus = compareDate(dateStr1, dateStr2, DATE_YYYYMMDDHHMMSS);
        return minus;
    }

    /**
     * 第二个日期减去第一个日期，得到的差值
     *
     * @param dateStr1    String|日期1
     * @param dateStr2    String|日期2
     * @param format_type String|日期格式
     * @return long|如果传入的时间格式精确到毫秒，则返回毫秒；否则，返回到秒
     */
    public static long compareDate(String dateStr1, String dateStr2, String format_type) {
        long minus = comareDateByLevel(dateStr1, dateStr2, format_type, format_type);
        return minus;
    }

    /**
     * 比较两个日期的相差值，支持比较Level
     *
     * @param dateStr1     String|日期1
     * @param dateStr2     String|日期2
     * @param format_type  String|源日期格式
     * @param level_format String|比较level日期格式
     * @return long|如果传入的时间格式精确到毫秒，则返回毫秒；否则，返回到秒
     */
    public static long comareDateByLevel(String dateStr1, String dateStr2, String format_type, String level_format) {
        long minus = 0;
        if (TextUtils.isEmpty(format_type)) {
            format_type = DATE_YYYYMMDD;
        }
        if (TextUtils.isEmpty(level_format)) {
            level_format = DATE_YYYYMMDD;
        }
        if (!TextUtils.isEmpty(dateStr1) && !TextUtils.isEmpty(dateStr2) && dateStr1.length() == dateStr2.length() ) {

            try {
                DateFormat df = new SimpleDateFormat(format_type, Locale.SIMPLIFIED_CHINESE);
                Date date1 = df.parse(dateStr1);
                Date date2 = df.parse(dateStr2);

                DateFormat df_dest = new SimpleDateFormat(level_format, Locale.SIMPLIFIED_CHINESE);
                Date date1_dest = df_dest.parse(df_dest.format(date1));
                Date date2_dest = df_dest.parse(df_dest.format(date2));
                minus = (date2_dest.getTime() - date1_dest.getTime());
                if(dateStr1.length()<=14){
                    minus=minus/(1000*3600*24);
                }

            } catch (ParseException e) {
                LogUtil.logError("", e);
            }
        }
        return minus;
    }

    public static boolean isDateRight(String dateStr) {
        return isDateRight(dateStr, DATE_YYYYMMDD);
    }

    public static boolean isDateRight(String dateStr, String format_type) {
        boolean result = false;
        if (!TextUtils.isEmpty(dateStr)) {
            if (TextUtils.isEmpty(format_type)) {
                format_type = DATE_YYYYMMDD;
            }

            DateFormat df = new SimpleDateFormat(format_type, Locale.SIMPLIFIED_CHINESE);
            try {
                Date date = df.parse(dateStr);
                if (date == null) {
                    result = false;
                } else {
                    result = true;
                }
            } catch (ParseException e) {
                LogUtil.logError("", e);
                result = false;
            }
        }
        return result;
    }

    /**
     * 当前日期是否为周末，日期必须为{@link #DATE_YYYYMMDD}
     *
     * @param dateStr String|{@link #DATE_YYYYMMDD}格式
     * @return boolean|true:是周末;false:不是周末
     */
    public static boolean isDateWeekend(String dateStr) {
        boolean result = false;
        if (!TextUtils.isEmpty(dateStr) && dateStr.length() == 8) {
            result = isDateWeekend(dateStr, DATE_YYYYMMDD);
        }
        return result;
    }

    /**
     * 当前日期是否为周末
     *
     * @param dateStr     String|
     * @param format_type String|日期格式
     * @return boolean|true:是周末;false:不是周末
     */
    public static boolean isDateWeekend(String dateStr, String format_type) {
        boolean result = false;
        DateFormat df = new SimpleDateFormat(format_type, Locale.SIMPLIFIED_CHINESE);
        try {
            Date date = df.parse(dateStr);
            Calendar calendar = new GregorianCalendar();
            if (date != null) {
                calendar.setTimeInMillis(date.getTime());
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                if (day == 6 || day == 7) {
                    result = true;
                }
            }
        } catch (ParseException e) {
            LogUtil.logError("", e);
            result = false;
        }
        return result;
    }
}
