package com.virgil.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuwujing on 15/4/8.
 */
public class CustomThreadPool extends ThreadPoolExecutor {
    private final static CustomThreadPool instance = getInstance();
    private final BlockingQueue<Runnable> rejectedTask = new LinkedBlockingQueue<>();

    private CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        super.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                rejectedTask.offer(r);
            }
        });
    }

    public static CustomThreadPool getInstance() {
        CustomThreadPool i = instance;
        if (i == null) {
            int coreThreadNum = Runtime.getRuntime().availableProcessors() * 2;
            int maxThreadNum = Runtime.getRuntime().availableProcessors() * 10;
            int maxLiveTime = 60;
            i = new CustomThreadPool(coreThreadNum, maxThreadNum, maxLiveTime, TimeUnit.SECONDS, new SynchronousQueue<>());
        }
        return i;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        Runnable task = rejectedTask.poll();
        if (task != null) {
            super.execute(task);
        }
    }
}
