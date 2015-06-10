package com.virgil.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.virgil.util.LogUtil;

public class TestDateTime {
    private static String st_strin = "content-st_strin";
    private static String st_strinNull;
    private String nor;
    private String norNull;
    private String norini = "content-norini";

    public static void main(String[] args) {
//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	long a=1398849663;
//	a=1398310472*1000;
//	System.out.println(simpleDateFormat.format(new Date(a)));
//	LogUtil.printlnInConsle(c("yyyy-MM-dd"));

        try {

            DateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.SIMPLIFIED_CHINESE);
            Date date1 = df.parse("20150729");
            Date date2 = df.parse("20150730");
            LogUtil.printlnInConsle(df.format(date1));
            LogUtil.printlnInConsle(df.format(date2));
            long minus = (date2.getTime() - date1.getTime()) / (1000);
            LogUtil.printlnInConsle(minus);

            SimpleDateFormat  df2=new SimpleDateFormat("yyyyMM", Locale.SIMPLIFIED_CHINESE);
            Date date11=df2.parse(df2.format(date1));
            Date date22=df2.parse(df2.format(date2));
            LogUtil.printlnInConsle(df2.format(date1));
            LogUtil.printlnInConsle(df2.format(date2));
            long minus2 = (date22.getTime() - date11.getTime()) / (1000);
            LogUtil.printlnInConsle(minus2);
        } catch (ParseException e) {

        }
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
