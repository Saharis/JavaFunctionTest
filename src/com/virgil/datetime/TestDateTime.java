package com.virgil.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDateTime {
	private static String st_strin="content-st_strin";
	private static String st_strinNull;
	private String nor;
	private String norNull;
	private String norini="content-norini";
public static void main(String[] args){
//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	long a=1398849663;
//	a=1398310472*1000;
//	System.out.println(simpleDateFormat.format(new Date(a)));
//	LogUtil.printlnInConsle(c("yyyy-MM-dd"));
	TestDateTime st=new TestDateTime();
	st.assgined();
}

	public static String c(String paramString)
	{
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.getDefault());
		Date localDate = new Date();
		String str = localSimpleDateFormat.format(localDate);
		return str;
	}
	private void assgined(){
		this.nor="content-nor";
	}
}
