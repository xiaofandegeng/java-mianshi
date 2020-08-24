package com.ning4256.threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));

        try {
            for (int i = 1; i <= 10 ; i++) {
                final Integer tmpI = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "号窗口，正在服务第" + tmpI + "顾客");
                });
            }
        }finally {
            threadPool.shutdown();
        }


    }
}
