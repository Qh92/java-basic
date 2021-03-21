package com.qinh.part2;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-21-1:10
 */
public class ArrayUtilTest {
    public static void main(String[] args) {
        ArrayUtil util = new ArrayUtil();
        int[] arr = new int[]{12,32,43,54,12,14,54,65,43,-221,-3232};
        System.out.println(util.getMax(arr));
    }
}
