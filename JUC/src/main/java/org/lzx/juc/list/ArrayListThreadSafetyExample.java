package org.lzx.juc.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.list
 * @data 2023/11/20 21:45
 */
public class ArrayListThreadSafetyExample {
    public static void main(String[] args) {
        // 创建一个非线程安全的ArrayList
        List<Integer> arrayList = new ArrayList<>();
        Object lock = new Object();

        // 创建两个线程，分别向ArrayList中添加元素
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {

//                synchronized (lock) {
                    arrayList.add(i);
//                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
//                synchronized (lock) {
                    arrayList.add(i);
//                }
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

        // 打印ArrayList的大小，由于非线程安全可能导致大小小于预期
        System.out.println("ArrayList size: " + arrayList.size());
    }
}
