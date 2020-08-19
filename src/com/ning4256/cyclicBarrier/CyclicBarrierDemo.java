package com.ning4256.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, ()->{
            System.out.println(Thread.currentThread().getName() + "\t 火箭发射");
        });

        for (int i = 1; i < 7; i++) {
            final Integer tmpI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 倒计时:" + tmpI);
                try {
                    cyclicBarrier.await();
                    try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }


    }
}
