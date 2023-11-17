package org.lzx.juc.lock;

/**
 * @author liuzhengxing
 * @version v1.0
 * @Description: 死锁演示
 * @package org.lzx.juc.lock
 * @data 2023/11/16 21:46
 */
public class DeadLockDemo {
    static Object a = new Object();

    static Object b = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println("t1线程持有a锁，试图获取b锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("t1线程获取到b锁");
                }
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println("t2线程持有a锁，试图获取a锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("t2线程获取到a锁");
                }
            }
        },"t2").start();

    }
}
