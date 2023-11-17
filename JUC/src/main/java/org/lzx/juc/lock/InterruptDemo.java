package org.lzx.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.lock
 * @data 2023/11/17 7:30
 * 使用interrupt() 和isInterrupted()组合使用来中断某个线程
 */
public class InterruptDemo {
/*    static  volatile boolean isStop = false;
    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
               if (isStop){
                   System.out.println(Thread.currentThread().getName()+" isStop 的值被改为true，t1程序停止");
                   break;
               }
                System.out.println("-----------hello volatile");
            }
        },"t1").start();

        try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace();}
    new Thread(()->{
            isStop = true;
            },"t2").start();
    }*/

/*    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + " atomicBoolean的值被改为true，t1程序停止");
                    break;
                }
                System.out.println("-----------hello atomicBoolean");
            }
        }, "t1").start();

        try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();

    }*/

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " isInterrupted()的值被改为true，t1程序停止");
                    break;
                }
                System.out.println("--------- hello interrupted");
            }
        }, "t1");
        t1.start();

        try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace();}
        //t2向t1放出协商，将t1中的中断标识位设为true，希望t1停下来
        new Thread(() -> {
           t1.interrupt();
        }, "t2").start();

        //当然，也可以t1自行设置
//        t1.interrupt();
    }
}
