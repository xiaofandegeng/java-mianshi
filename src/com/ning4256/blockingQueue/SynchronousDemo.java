package com.ning4256.blockingQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

//这是不存储数据的阻塞队列
public class SynchronousDemo {
    public static void main(String[] args) {

        SynchronousQueue<String> queue = new SynchronousQueue<>();
        //一个线程放元素
        new Thread(()->{
            try {
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 开始存放第一个元素");
                queue.put("one");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 开始存放第二个元素");
                queue.put("two");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 开始存放第三个元素");
                queue.put("three");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "AAA").start();


        //一个线程取元素

        new Thread(()->{
            try {
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 开始取出第一个元素");
                queue.take();
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 开始取出第二个元素");
                queue.take();
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 开始取出第三个元素");
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "BBB").start();

    }

}
