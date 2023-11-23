package org.lzx.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cas
 * @data 2023/11/23 7:40
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        Account account = new Account();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    account.increment();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance());
    }
}

class Account {
    private AtomicInteger balance = new AtomicInteger(0);

    public void increment() {
        balance.incrementAndGet();
    }

    public int getBalance() {
        return balance.get();
    }
}
