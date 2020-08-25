package com.ning4256.deadLock;

class MyDeadLock implements Runnable {

    private String lockA;
    private String lockB;

    public MyDeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 已经获取锁：" + lockA + "\t 尝试获取锁：" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 已经获取锁：" + lockB + "\t 尝试获取锁：" + lockA);
            }
        }
    }
}
/**
 * 死锁示例
 */
public class MyDeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyDeadLock(lockA, lockB), "ThradAAA").start();
        new Thread(new MyDeadLock(lockB, lockA), "ThradBBB").start();

    }
}

