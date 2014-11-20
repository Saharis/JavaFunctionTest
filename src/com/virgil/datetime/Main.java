package com.virgil.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
public static void main(String[] args){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	long a=1398849663;
	a=1398310472*1000;
	System.out.println(simpleDateFormat.format(new Date(a)));
}
}
