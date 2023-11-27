package org.lzx.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.atomic
 * @data 2023/11/25 12:09
 * 需求：10个线程各自转账1000
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=1000; j++) {
                        bankAccount.transfer(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(bankAccount.balance);
    }
}

class BankAccount {
    public volatile int balance;
    private static final AtomicIntegerFieldUpdater<BankAccount> balanceUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "balance");

    public void transfer(BankAccount bankAccount) {
        balanceUpdater.getAndIncrement(bankAccount);
    }
}
