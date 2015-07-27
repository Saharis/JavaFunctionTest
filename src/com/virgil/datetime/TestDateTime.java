package com.virgil.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.virgil.util.DateTimeUtil;
import com.virgil.util.LogUtil;

public class TestDateTime {
    private static String st_strin = "content-st_strin";
    private static String st_strinNull;
    private String nor;
    private String norNull;
    private String norini = "content-norini";

    public static void main(String[] args) {
        LogUtil.printlnInConsle(DateTimeUtil.comareDateByLevel("20151215","20160131",  "yyyyMMdd", "yyyyMM"));
    }

    public static String c(String paramString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.getDefault());
        Date localDate = new Date();
        String str = localSimpleDateFormat.format(localDate);
        return str;
    }

    private void assgined() {
        this.nor = "content-nor";
    }

    public static boolean isDateWeekend(String dateStr) {
        boolean result = false;
        DateFormat df = new SimpleDateFormat("yyyyMMDD", Locale.SIMPLIFIED_CHINESE);
        try {
            Date date = df.parse(dateStr);
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(date.getTime());
            LogUtil.printlnInConsle(calendar.get(Calendar.DAY_OF_WEEK));
        } catch (ParseException e) {
            result = false;
        }
        return result;
    }
}
