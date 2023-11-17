package org.lzx.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.lock
 * @data 2023/11/15 22:43
 */

class Ticket {
    private int number = 50;
    ReentrantLock lock = new ReentrantLock();//非公平锁
//    ReentrantLock lock = new ReentrantLock(true);//公平锁
    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票，"+"剩余票数为：" + number);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
                },"A").start();

        new Thread(()->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}
