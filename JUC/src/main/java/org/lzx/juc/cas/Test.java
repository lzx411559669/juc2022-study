package org.lzx.juc.cas;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cas
 * @data 2023/11/23 7:33
 */
public class Test {
    public static void main(String[] args) {
        Demo demo = new Demo();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    demo.increment();
                }
            }).start();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(demo.getCount());
    }
}

class Demo {
    private volatile int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}


