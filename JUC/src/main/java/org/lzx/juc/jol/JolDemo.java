package org.lzx.juc.jol;


import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.jol
 * @data 2023/11/30 7:22
 */
public class JolDemo {
    public static void main(String[] args) {

//        System.out.println(VM.current().details());
//        //对象对齐数
//        System.out.println(VM.current().objectAlignment());
//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

class Customer{//只有一个对象头的实例对象，16字节，8字节的对象头，8字节的实例数据，8字节的数组长度
    //1.第一种情况只有对象头，没有任何其他实例数据16字节，8字节的对象头，4字节类型指针，4字节对齐补充。
    //第二种情况，int+boolean，16字节（忽略压缩指针的影响），+4字节+1字节=21字节，对齐填充，24字节
int id;
boolean flag = false;
}
