package org.lzx.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.lock
 * @data 2023/11/19 10:22
 */
public class LockSupportDemo {
    public static void main(String[] args) {
//        waitNotify();
//        waitSignal();
        park();
    }


    private static void waitNotify() {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("thread1 start");
                try {
                   objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 被唤醒");
            }
        }).start();

        try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("thread2 start");
                objectLock.notify();
                System.out.println("thread2 发出通知");
            }
        }).start();
    }

    private static void waitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread1 start");
                condition.await();
                System.out.println("thread1 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }).start();

        try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println("thread2 发出通知");
            }finally {
                lock.unlock();
            }
        }).start();
    }
    private static  void park(){
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 start");
            LockSupport.park();
            System.out.println("thread1 被唤醒");
        });

        t1.start();

        try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            System.out.println("thread2 start");
            LockSupport.unpark(t1);
            System.out.println("thread2 发出通知");
        }).start();
    }
}
