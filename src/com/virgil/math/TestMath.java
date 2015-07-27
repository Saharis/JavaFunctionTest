package com.virgil.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

import com.virgil.util.LogUtil;

/**
 * Created by liuwujing on 15/3/5.
 */
public class TestMath {
    public static void main(String[] args) {
//        LogUtil.printlnInConsle(Math.sqrt(81)+"");
//        testMath();
//        int a=46;
//        float x=Float.valueOf(String.format("%1$.2f", a / 100.0f));
//        LogUtil.printlnInConsle(String.valueOf(x));
//        LogUtil.printlnInConsle(Math.round(x));
        BigDecimal s=new BigDecimal(123.8);
        BigDecimal result=s.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP);
        DecimalFormat df=new DecimalFormat("0.00");

        LogUtil.printlnInConsle(result.floatValue()+"");
        LogUtil.printlnInConsle(result.doubleValue()+"");
        LogUtil.printlnInConsle(df.format(result));
//        LogUtil.printlnInConsle(String.format("%1$.2f", result.doubleValue()));
    }

    public static void testMath() {
        double[] nums = { 1.4, 1.5, 1.6, -1.4, -1.5, -1.6 };
        for (double num : nums) {
            print(num);
        }
    }

    private static void print(double num) {

        System.out.println("Math.floor(" + num + ")=" + Math.floor(num));
        System.out.println("Math.round(" + num + ")=" + Math.round(num));
        System.out.println("Math.ceil(" + num + ")=" + Math.ceil(num));
    }

    public static String getRandomString(int paramInt) {
        Random localRandom = new Random();
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramInt; i++) {
            int j = localRandom.nextInt(3);
            long l = 0L;
            switch (j) {
                case 0:
                    l = Math.round(Math.random() * 25.0D + 65.0D);
                    localStringBuffer.append(String.valueOf((char) (int) l));
                    break;
                case 1:
                    l = Math.round(Math.random() * 25.0D + 97.0D);
                    localStringBuffer.append(String.valueOf((char) (int) l));
                    break;
                case 2:
                    localStringBuffer.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return localStringBuffer.toString();
    }
}
