package com.qinh;

import org.junit.Test;

/**
 * 数组中的常见异常
 * 1. 数组角标越界的异常: ArrayIndexOutOfBoundsException
 * 2. 空指针异常: NullPointerException
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-16-22:03
 */
public class ArrayExceptionTest {

    @Test
    public void t1(){
        int[] arr = new int[]{1,2,3,4,5,6,7};
//        for (int i = 0; i <= arr.length; i++){
//            //java.lang.ArrayIndexOutOfBoundsException: 7
//            System.out.println(arr[i]);
//        }
//        System.out.println(arr[-1]);

        //空指针异常
        //情况一
        int[] arr1 = new int[]{1,23,4};
        arr1 = null;
        //java.lang.NullPointerException
//        System.out.println(arr1[1]);
        //情况二
        int[][] arr2 = new int[4][];
        System.out.println(arr2[0]);
        //java.lang.NullPointerException
//        System.out.println(arr2[0][0]);

        //情况三
        String[] arr3 = new String[]{"AA","BB","CC"};
        arr3[0] = null;
        //java.lang.NullPointerException
//        System.out.println(arr3[0].toString());
    }
}
