package com.virgil.thread;

import com.virgil.util.LogUtil;

/**
 * Created by liuwujing on 15/1/5.
 */
public class MutiLockTest {
    private Integer str = 0;
    private Integer str2 = 100;
    byte[] ob = new byte[0];
    byte[] ob2 = new byte[0];

    public static void main(String[] args) {
        MutiLockTest main1 = new MutiLockTest();
//        main1.in(main1.ob);
        MutiLockTest main2 = new MutiLockTest();
//        Class<?> clazz=MutiLockTest.class;
        out(main1, main2);
    }

    public static void out(MutiLockTest main1, MutiLockTest main2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                main1.method1(main1, main2);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                main2.method2(main1, main2);
            }
        }).start();
    }

    public void in(byte[] o) {
        ob = o;
        int x = 0;
        while (x < 20) {
            x++;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test();
                }
            }).start();
        }

        int y = 100;
        while (y < 120) {
            y++;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test2();
                }
            }).start();
        }
    }

    public void test() {
        synchronized (ob) {
            str++;
            LogUtil.printlnInConsle(str);
        }
    }

    public void test2() {
        synchronized (ob2) {
            str2++;
            LogUtil.printlnInConsle(str2);
        }
    }

    public void method1(MutiLockTest mian1, MutiLockTest mian2) {
        int i = 0;
        while (i < 10000) {
            i++;
            synchronized (mian1) {
                synchronized (mian2) {
                    LogUtil.printlnInConsle("i="+i+"===" + System.currentTimeMillis());
                }
            }
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void method2(MutiLockTest mian1, MutiLockTest mian2) {
        int i = 0;
        while (i < 10000) {
            i++;
            synchronized (mian1) {
                synchronized (mian2) {
                    LogUtil.printlnInConsle("i="+i+"||||" + System.currentTimeMillis());
                }
            }
        }
    }
}
