package com.qinh.part2;

/**
 * 自定义数组的工具类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-21-1:02
 */
public class ArrayUtil {

    /**
     * 求数组的最大值
     */
    public int getMax(int[] arr){
        if (arr.length < 0){
            throw new RuntimeException("数组未正确初始化");
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 求数组的最小值
     */
    public int getMin(int[] arr){
        if (arr.length < 0){
            throw new RuntimeException("数组未正确初始化");
        }
        int min = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    /**
     * 求数组的总和
     */
    public int getSum(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }

    /**
     * 求数组的平均值
     */
    public int getAvg(int[ ] arr){
        return getSum(arr) / arr.length;
    }

    /**
     * 反转数组
     */
    public void reverse(int[] arr){
        for (int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    //方法重载
    public void reverse(String[] arr){

    }

    /**
     * 复制数组
     */
    public int[] copy(int[ ] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /**
     * 数组排序:从小到大
     */
    public void sort(int[ ] arr){
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 遍历数组
     */
    public void print(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 查找指定元素
     */
    public int getIndex(int[] arr,int dest){
        for (int i = 0; i < arr.length; i++){
            if (dest == arr[i]){
                return i;
            }
        }
        return -1;
    }
}
