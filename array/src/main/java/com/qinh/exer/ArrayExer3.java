package com.qinh.exer;

import org.junit.Test;

/**
 * 数组元素的排序算法
 *
 *  选择排序
 *       直接选择排序、堆排序
 *  交换排序
 *       冒泡排序、快速排序
 *  插入排序
 *       直接插入排序、折半插入排序、Shell排序
 *  归并排序
 *  桶式排序
 *  基数排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-16-21:20
 */
public class ArrayExer3 {

    /**
     * 数组的冒泡排序的实现
     */
    @Test
    public void t1(){

        int[] arr = new int[]{32,43,12,23,44,56,-12,-23,-99,-34,89,69};

        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "\t");
        }
    }

    /**
     *
     */
    @Test
    public void t2(){

    }
}
