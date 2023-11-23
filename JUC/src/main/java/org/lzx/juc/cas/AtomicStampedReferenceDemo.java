package org.lzx.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cas
 * @data 2023/11/23 7:59
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        Book java = new Book(1, "java");
        AtomicStampedReference<Book> atomicStampedReference = new AtomicStampedReference<>(java, 1);

        System.out.println(atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());

        Book mysql = new Book(2, "mysql");
        boolean b;
        b = atomicStampedReference.compareAndSet(java, mysql, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());

        b = atomicStampedReference.compareAndSet(mysql, java, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Book {
    private int id;
    private String bookName;
}