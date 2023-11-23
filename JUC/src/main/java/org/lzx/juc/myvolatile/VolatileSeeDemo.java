package org.lzx.juc.myvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.myvolatile
 * @data 2023/11/21 22:54
 */
public class VolatileSeeDemo {
    /**
     * t1	-------come in
     * main	 修改完成
     * t1	-------flag被设置为false，程序停止
     */
    static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {
                System.out.println(Thread.currentThread().getName() + "-------come in");
            }
            System.out.println(Thread.currentThread().getName() + "-------flag被设置为false，程序停止");
        }).start();

        try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成");
    }
}
