package com.virgil.classtest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;

public class Test {
	public static void main(String[] args) {
		A a = new A();
        a.ss="main";
        change(a);
		System.out.println(a.ss);
//		b.ss=a.ss;
//		a.ss="";
//		test(a,b);
//		System.out.println(c.tt+";tt.hashCode()="+c.tt.hashCode());
//		Integer[] ary = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
//
//		MyCompare my = new MyCompare();
//		Arrays.sort(ary, my::compareInt);
//
//		System.out.println(Arrays.toString(ary));
	}

    private static  void change(A a){
        a=new A();
        a.ss="change";
    }
	private static void test(Object obSource, Object obTarget) {}
}
