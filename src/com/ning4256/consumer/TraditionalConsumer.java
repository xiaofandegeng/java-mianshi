package com.ning4256.consumer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统版生产者消费者
 */

//资源类
class MyData {
    private Integer number = 0;
    private Lock lock = new ReentrantLock();
    //判断条件
    private Condition condition = lock.newCondition();

    //生产方法
    public void add() throws InterruptedException {
        lock.lock();
        //判断
        while (number != 0) {
            //等待
            condition.await();
        }
        //干活
        number += 1;
        System.out.println(Thread.currentThread().getName() + "\t 生产蛋糕");
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        //通知
        condition.signalAll();
        lock.unlock();
    }

    //消费方法
    public void remove() throws InterruptedException {
        lock.lock();
        //判断
        while (number == 0) {
            //等待
            condition.await();
        }
        //干活
        number -= 1;
        System.out.println(Thread.currentThread().getName() + "\t 消费蛋糕");
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        //通知
        condition.signalAll();

        lock.unlock();
    }

}


public class TraditionalConsumer {
    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                try {

                    myData.add();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "生产者").start();
        }

        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                try {

                    myData.remove();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "消费者").start();
        }
    }

}
