package org.lzx.juc.synchronizedUp;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.synchronizedUp
 * @data 2023/12/3 21:04
 */
public class DebugTest {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3,11,12,15,16,17,18,19,20};
        for (int i = 0; i < ints.length; i++) {
            int temp = ints[i];
            System.out.println(temp);
        }
    }
}
