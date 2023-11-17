package org.lzx.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.lock
 * @data 2023/11/16 21:40
 */
public class ReEntryLockDemo {
    public static void main(String[] args) {
        Object o = new Object();
        /**
         * ---------------外层调用
         * ---------------中层调用
         * ---------------内层调用
         */
        new Thread(() -> {
            synchronized (o) {
                System.out.println("外层调用");
                synchronized (o) {
                    System.out.println("中层调用");
                    synchronized (o) {
                        System.out.println("内层调用");
                    }
                }
            }
        }, "T1").start();
        /**
         * 注意：加锁几次就需要解锁几次
         * ---------------外层调用
         * ---------------中层调用
         * ---------------内层调用
         */
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("外层调用");
                lock.lock();
                try {
                    System.out.println("中层调用");
                    lock.lock();
                    try {
                        System.out.println("内层调用");
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "T2").start();

    }


}
