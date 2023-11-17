package org.lzx.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.lock
 * @data 2023/11/17 7:42
 */
public class InterruptDemo2 {
    public static void main(String[] args) {
        //实例方法interrupt()仅仅是设置线程的中断状态位为true，不会停止线程
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 600; i++) {
                System.out.println("-----: " + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标志位02：" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        System.out.println("t1线程默认的中断标志位：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

        System.out.println("t1线程调用interrupt()后的中断标志位03：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2000毫秒后，t1线程已经不活动了，不会产生任何影响
        System.out.println("t1线程调用interrupt()后的中断标志位04：" + t1.isInterrupted());
    }
}
