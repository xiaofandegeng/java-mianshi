package com.ning4256.deadLock;

import java.util.concurrent.TimeUnit;

public class HelloGCDemo {
    public static void main(String[] args) {
        System.out.println("****heello，GC******");
        try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }
        // java -XX:+PrintFlagsInitial
        // jps -l
        //jinfo -flags 线程数
        //java -XX:+PrintFlagsFinal -version
    }
}
