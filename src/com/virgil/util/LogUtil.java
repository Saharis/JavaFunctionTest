package com.virgil.util;

/**
 * Created by liuwujing on 14-9-19.
 */
public class LogUtil {
    public static void printlnInConsle(String info){
        print(info);
    }
    public static void printlnInConsle(long info){
        print(info);
    }
    private static void print(Object ob){
        System.out.println(ob);
    }
    public static void printInWindow(String info){

    }
    public static void logErrorInConsle(String error){
        printlnInConsle("ERROR==="+error);
    }
}
