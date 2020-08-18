package com.ning4256.reentrantWriteReadLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class Mycache {
    //定义一个map类
    private volatile Map<Integer,String> map = new HashMap<>();

    //添加读写锁
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    //这是写入方法
    public void put(Integer key, String value){
        //拿到写锁
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "\t 开始写入,key: " + key);
            map.put(key, value);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }finally {
            writeLock.unlock();
        }
    }

    //这是读取方法
    public void get(Integer key){
        //拿到读锁
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "\t 开始读取");
            String res = map.get(key);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 读取完成，res: " + res);
        } finally {
            readLock.unlock();
        }
    }
}


public class ReentrantWriteReadLockDemo {



    public static void main(String[] args) {
        Mycache mycache = new Mycache();

        //多个线程同时写数据
        for (int i = 1; i < 6; i++) {
            final Integer tmpI = i;
            new Thread(() -> {
                mycache.put(tmpI, tmpI+"");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }, String.valueOf(tmpI)).start();
        }

        for (int i = 1; i < 6; i++) {
            final Integer tmpI = i;
            new Thread(() -> {
                mycache.get(tmpI);
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }, String.valueOf(tmpI)).start();
        }
    }
}
