package com.virgil.thread;

import com.virgil.util.LogUtil;

/**
 * Created by liuwujing on 15/1/7.
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();

    }

    private void test(){
        SyncStack stack = new SyncStack();
        //下面的消费者类对象和生产者类对象所操作的是同一个同步堆栈对象
        Runnable source = new Producer(stack);
        Runnable sink = new Consumer(stack);
        Thread t1 = new Thread(source); //线程实例化
        Thread t2 = new Thread(sink); //线程实例化
        t1.start(); //线程启动
        t2.start(); //线程启动
    }
    class Producer implements Runnable { //生产者类
        SyncStack theStack;
//生产者类生成的字母都保存到同步堆栈中

        public Producer(SyncStack s) {
            theStack = s;
        }

        public void run() {
            char c;
            for (int i = 0; i < 20; i++) {
                c = (char) (Math.random() * 26 + 'A');
//随机产生20个字符
                theStack.push(c); //把字符入栈
                System.out.println("Produced: " + c); //打印字符
                try {
                    Thread.sleep((int) (Math.random() * 1000));
/*每产生一个字符线程就睡眠*/
                } catch (InterruptedException e) {
                }
            }
        }
    }

    class Consumer implements Runnable { //消费者类
        SyncStack theStack;
//消费者类获得的字符都来自同步堆栈

        public Consumer(SyncStack s) {
            theStack = s;
        }

        public void run() {
            char c;
            for (int i = 0; i < 20; i++) {
                c = theStack.pop(); //从堆栈中读取字符
                System.out.println("Consumed: " + c);
//打印字符
                try {
                    Thread.sleep((int) (Math.random() * 1000));
/*每读取一个字符线程就睡眠*/
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private class SyncStack { //同步堆栈类
        private volatile int index = 0; //堆栈指针初始值为0
        private int length=6;

        private char[] buffer = new char[length]; //堆栈有6个字符的空间
        public synchronized void push(char c) { //加上互斥锁
            while (index == length) { //堆栈已满，不能压栈
//                try {
                    LogUtil.printlnInConsle("push等待");
//                    this.wait(); //等待，直到有数据出栈
//                } catch (InterruptedException e) {
//                }
            }

            buffer[index] = c; //数据入栈
            index++; //指针向上移动
//            this.notify(); //通知其它线程把数据出栈
        }

        public synchronized char pop() { //加上互斥锁
            if(index == 0){
                LogUtil.printlnInConsle("pop等待开始");
            }
            while (index == 0) { //堆栈无数据，不能出栈
//                try {

//                    this.wait(); //等待其它线程把数据入栈
//                } catch (InterruptedException e) {
//                }
            }
            LogUtil.printlnInConsle("pop等待结束");
//            this.notify(); //通知其它线程入栈
            index--; //指针向下移动
            return buffer[index]; //数据出栈
        }
    }
}
