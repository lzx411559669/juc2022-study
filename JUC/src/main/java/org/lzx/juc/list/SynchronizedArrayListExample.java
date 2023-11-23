package org.lzx.juc.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.list
 * @data 2023/11/20 21:49
 */
public class SynchronizedArrayListExample {
    public static void main(String[] args) {
        // 创建一个线程安全的ArrayList
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // 创建两个线程，分别向synchronizedList中添加元素
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedList.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                synchronizedList.add(i);
            }
        });

        // 启动两个线程
        thread1.start();
        thread2.start();

        try {
            // 等待两个线程执行完成
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印synchronizedList的大小
        System.out.println("Synchronized ArrayList size: " + synchronizedList.size());
    }
}
