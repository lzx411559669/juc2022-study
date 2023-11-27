package org.lzx.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.atomic
 * @data 2023/11/25 11:53
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }

        System.out.println();
        int tempInt = 0;

        tempInt = atomicIntegerArray.getAndSet(0,1122);
        System.out.println(tempInt+"\t"+atomicIntegerArray.get(0));
        tempInt = atomicIntegerArray.getAndIncrement(0);
        System.out.println(tempInt+"\t"+atomicIntegerArray.get(0));
    }
}
