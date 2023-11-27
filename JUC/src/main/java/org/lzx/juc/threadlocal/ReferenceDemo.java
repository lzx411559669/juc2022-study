package org.lzx.juc.threadlocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.threadlocal
 * @data 2023/11/27 22:18
 */

class MyObject {
    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        System.out.println("-----invoke finalize method!! ");
    }
}

public class ReferenceDemo {

    public static void main(String[] args) {

//        strongReference();
//        softReference();
//        weakReference();
        MyObject myObject = new MyObject();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();

        PhantomReference<MyObject> myObjectPhantomReference = new PhantomReference<>(myObject, objectReferenceQueue);
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(myObjectPhantomReference.get()+"\t"+"list add ok");
            }
        }, "T1").start();

        new Thread(() -> {
            while (true) {
                Reference<?> poll = objectReferenceQueue.poll();
                if (poll != null) {
                    System.out.println("---有虚引用对象回收加入了队列");
                    break;
                }
            }
        }, "T2").start();


    }

    private static void weakReference() {
        WeakReference<MyObject> myObjectWeakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before:" + myObjectWeakReference.get());
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc After:" + myObjectWeakReference.get());
    }

    /**
     * 软引用
     */
    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("gc before:" + softReference.get());
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc After内存够用:" + softReference.get());
        //new 一个20M的bytes对象
        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc After内存不够用:" + softReference.get());
        }
    }

    /**
     * 强引用
     */
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before:" + myObject);
        myObject = null;
        System.gc();//人工开启GC
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc After:" + myObject);
    }
}
