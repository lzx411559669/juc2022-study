package org.lzx.juc.test;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.test
 * @data 2023/11/26 9:52
 */
public class ElseIfTest {
    public static void main(String[] args) {
        int a = 10;

        if (a >9) {
            System.out.println("a > 9");
        } else if (a > 5) {
            System.out.println("a > 5");
        } else if (a > 0) {
            System.out.println("a > 0");
        } else {
            System.out.println("a <= 0");
        }
    }
}
