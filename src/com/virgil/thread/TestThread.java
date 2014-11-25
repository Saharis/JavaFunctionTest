package com.virgil.thread;

import com.virgil.util.LogUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuwujing on 14/11/20.
 */
public class TestThread {
    public static void main(String[] args){
        Runnable a=new Runnable() {
            @Override
            public void run() {
                LogUtil.printlnInConsle("Thread.currentThread():" +
                        "getName()-" + Thread.currentThread().getName() +
                        "===isAlive():" + Thread.currentThread().isAlive() +
                        "===getId():" + Thread.currentThread().getId() +
                        "===getState():" + Thread.currentThread().getState() +
                        "===getStackTrace():" + Thread.currentThread().getStackTrace());
            }
        };
        new Thread(a).start();
        new Thread(a).start();
        new Thread(a).start();
        ExecutorService ex= Executors.newFixedThreadPool(2);
        ex.execute(new Thread(a));
        ex.execute(new Thread(a));
        ex.execute(new Thread(a));
        ex.execute(new Thread(a));
        ex.execute(new Thread(a));

    }

}
