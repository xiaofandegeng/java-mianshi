package com.ning4256.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountLatchDownDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i < 6; i++) {
            final Integer tmpI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 已经被消灭了");
                countDownLatch.countDown();
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }, CountLatchDownEnum.getMessage(tmpI)).start();
        }
        countDownLatch.await();
        System.out.println("秦国一统天下");

    }

    private static void countDownLatchDemo01() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i < 6; i++) {
            final Integer tmpI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 正在执行");
                countDownLatch.countDown();
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }, String.valueOf(tmpI)).start();
        }
        countDownLatch.await();
        System.out.println("执行完毕");
    }
}
