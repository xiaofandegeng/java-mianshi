package com.ning4256.blockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class MyDataConditions {
    private volatile Integer num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //第一个线程打印5此
    public void print5() throws Exception {
        lock.lock();
        try {
            while (num != 1) {
                c1.await();
            }
            for (int i = 1; i < 6; i++) {
                //判断
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                //干活

                System.out.println(Thread.currentThread().getName() + "\t 正在执行");
                num = 2;
                //通知
                c2.signal();
            }
        }finally {
            lock.unlock();
        }

    }
    //第一个线程打印5此
    public void print10() throws Exception {
        lock.lock();
        try {
            while (num != 2) {
                c2.await();
            }
            for (int i = 1; i < 11; i++) {
                //判断
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                //干活
                System.out.println(Thread.currentThread().getName() + "\t 正在执行");
                //通知
                num = 3;
                c3.signal();
            }
        }finally {
            lock.unlock();
        }

    }
    //第一个线程打印5此
    public void print15() throws Exception {
        lock.lock();
        try {
            while (num != 3) {
                c3.await();
            }
            for (int i = 1; i < 16; i++) {
                //判断
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                //干活

                System.out.println(Thread.currentThread().getName() + "\t 正在执行");
                //通知
                num = 1;
                c1.signal();
            }
        }finally {
            lock.unlock();
        }

    }

}

//多个线程按序执行
public class LockConditions {
    public static void main(String[] args) {
        MyDataConditions myData = new MyDataConditions();

        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                try {
                    myData.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "线程1").start();
        }
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                try {
                    myData.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "线程2").start();
        }
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                try {
                    myData.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "线程3").start();
        }


    }
}
