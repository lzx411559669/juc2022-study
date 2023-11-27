package org.lzx.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.atomic
 * @data 2023/11/25 11:27
 */
public class AtomicIntegerDemo {
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 1; i <= SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++) {
                        myNumber.add();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(myNumber.atomicInteger.get());
    }
}


class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void add() {
        atomicInteger.getAndIncrement();
    }
}