package com.qinh;

import org.junit.Test;

import java.util.Arrays;

/**
 * java.util.Arrays:操作数组的工具类，里面定义了很多操作数组的方法
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-16-21:51
 */
public class ArraysTest {

    /**
     * 1 boolean equals(int[] a,int[] b) 判断两个数组是否相等。
     * 2 String toString(int[] a) 输出数组信息。
     * 3 void fill(int[] a,int val) 将指定值填充到数组之中。
     * 4 void sort(int[] a) 对数组进行排序。
     * 5 int binarySearch(int[] a,int key) 对排序后的数组进行二分法检索指定的值。
     */
    @Test
    public void t1(){
        int[] arr = new int[]{1,2,3,4};
        int[] arr1 = new int[]{1,3,2,4};
        int[] arr2 = new int[]{1,99,12,78,32};
        boolean equals = Arrays.equals(arr, arr1);
        System.out.println(equals);

        System.out.println(Arrays.toString(arr1));

        Arrays.fill(arr1,10);
        System.out.println(Arrays.toString(arr1));

        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = new int[]{32,43,12,23,44,56,-12,-23,-99,-34,89,69};
        //如果找到了则返回索引值，如果未找到则返回负数
        int index = Arrays.binarySearch(arr3, 99);
        System.out.println(index);
    }
}
