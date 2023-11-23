package org.lzx.juc.list;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.list
 * @data 2023/11/20 22:10
 */
public class DeviceTest {
/*
    static boolean flag = true;

    static volatile List<Device> devices = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        devices = queryDevices();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in ");
            Device device = devices.get(100);
            synchronized (device) {
                while (!device.isUseing()) {
                    try {
                        device.wait(); // 等待条件满足
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("结束");
        }, "Thread name").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Device device = devices.get(100);
        synchronized (device) {
            device.setUseing(true);
            System.out.println("main 修改完成：" + device.isUseing());
            device.notifyAll(); // 通知等待的线程条件已满足
        }

        flag = false;
    }*/

    static boolean flag = true;
    static volatile List<Device> devices = Collections.synchronizedList(new ArrayList<>());

    static final Lock lock = new ReentrantLock();
    static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        devices = queryDevices();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in ");
            Device device = devices.get(100);
            lock.lock();
            try {
                while (!device.isUseing()) {
                    condition.await(); // 等待条件满足
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("结束");
        }, "Thread name").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Device device = devices.get(100);
        lock.lock();
        try {
            device.setUseing(true);
            System.out.println("main 修改完成：" + device.isUseing());
            condition.signalAll(); // 通知等待的线程条件已满足
        } finally {
            lock.unlock();
        }
    }

    static List<Device> queryDevices() {
        List<Device> devices = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Device device = new Device();
            device.setIp(String.valueOf(i));
            device.setUseing(false);
            devices.add(device);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return devices;
    }
}
