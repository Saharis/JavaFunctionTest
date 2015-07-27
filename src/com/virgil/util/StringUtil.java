package com.virgil.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class StringUtil {

	public static boolean emptyOrNull(String str) {
		return str == null || "".equals(str);
	}

    public static boolean isDateTimeEmpty(String dateTime) {
        final String emptyDate = "00010101";// 8位空日期
        final String emptyDateTime = "00010101000000";// 14位空日期
        if (StringUtil.emptyOrNull(dateTime)) {
            return true;
        }
        // 14位时间
        if (dateTime.equals(emptyDateTime)) {
            return true;
        }
        // 8位日期
        if (dateTime.equals(emptyDate)) {
            return true;
        }
        return false;
    }

    /**
     * 将String转换为int，异常时，返回传入的{@code #defaultValue}
     *
     * @param str          需要转换为int的String
     * @param defaultValue 异常时的默认值
     * @return int
     */
    public static int toInt(String str, int defaultValue) {
        int i;
        try {
            i = Integer.parseInt(str);
        }
        catch (Exception e) {
            i = defaultValue;
        }
        return i;
    }

    public static int toInt(String s) {
        return toInt(s,-1);
    }

    /**
     * 获取异常信息
     *
     * @param arg1 Throwable
     * @return String
     */
    public static String getExceptionInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        // arg1.printStackTrace(pw);
        arg1.getCause().printStackTrace(pw);
        pw.close();

        String[] errorInfo = writer.toString().split("\n\tat");
        String error = "";
        int length = errorInfo.length > 6 ? 6 : errorInfo.length;
        for (int i = 0; i < length; i++) {
            error = error + errorInfo[i] + "\n\tat";
        }
        return "异常内容：" + error;
    }

    /**
     * 获取异常信息
     * @param e Exception
     * @return String
     */
    public static String getExceptionInfo(Exception e){
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw =  new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

}
