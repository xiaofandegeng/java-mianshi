package com.ning4256.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列版生产者消费者
 */
class MyDataBQC {
    private volatile boolean Flag = true;
    private AtomicInteger num = new AtomicInteger();
    private BlockingQueue<String> queue = null;

    public MyDataBQC(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    //生产者生产商品
    public void prod() throws InterruptedException {
        boolean res;
        while (Flag) {
            res = queue.offer(num + "", 2L, TimeUnit.SECONDS);
            if (res){
                System.out.println(Thread.currentThread().getName() + "\t 生产蛋糕成功，res：" + res);
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 生产蛋糕失败");
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName() + "\t Flag==flase，停止生产");
    }
    //消费者消费商品
    public void cons() throws InterruptedException {
        String res;
        while (Flag){
            res = queue.poll(2L, TimeUnit.SECONDS);
            if(res == null || res.equalsIgnoreCase("")) {
                Flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 消费蛋糕失败");
                return;
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 消费蛋糕成功");

        }



    }
    //停止生产消费
    public void stop() {
        this.Flag = false;
    }
}


public class BlockingQueueConsumer {
    public static void main(String[] args) {
        MyDataBQC myData = new MyDataBQC(new ArrayBlockingQueue<String>(10));
        //生产者
        new Thread(() -> {
            System.out.println("生产线开始");
            try {
                myData.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者").start();

        new Thread(()-> {
            System.out.println("消费线开始");
            try {
                myData.cons();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者").start();
    }
}
