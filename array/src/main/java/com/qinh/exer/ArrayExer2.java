package com.qinh.exer;

import org.junit.Test;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-15-21:18
 */
public class ArrayExer2 {

    /**
     * 求数值型数组中元素的最大值、最小值、平均数、总和等
     *
     * 定义一个int型的一维数组，包含10个元素，分别赋一些随机整数，
     * 然后求出所有元素的最大值，最小值，和值，平均值，并输出出来。
     * 要求：所有随机数都是两位数
     */
    @Test
    public void t1(){

        int[] arr = new int[10];
        for (int i = 0;i < arr.length; i++){
            arr[i] = (int)(Math.random() * 90 + 10);
        }
        for (int i = 0;i < arr.length; i++){
            System.out.print(arr[i] + "  ");
        }

        System.out.println();

        int max = arr[0],min = arr[0];
        int sum = 0,avg = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
            if (arr[i] < min){
                min = arr[i];
            }
            sum += arr[i];
        }

        avg = sum / arr.length;

        System.out.println("最大值为: " + max);
        System.out.println("最小值为: " + min);
        System.out.println("总和: " + sum);
        System.out.println("平均值: " + avg);

    }

    /**
     * 数组的复制、反转、查找(线性查找、二分法查找)
     *
     * 使用简单数组
     * (1)创建一个名为ArrayTest的类，在main()方法中声明array1和array2两个变量， 他们是int[]类型的数组。
     * (2)使用大括号{}，把array1初始化为8个素数：2,3,5,7,11,13,17,19。
     * (3)显示array1的内容。
     * (4)赋值array2变量等于array1，修改array2中的偶索引元素，使其等于索引值 (如array[0]=0,array[2]=2)。打印出array1。
     * 思考：array1和array2是什么关系？ array1和array2地址值相同，都指向了堆空间的唯一的一个数组实体
     * 拓展：修改题目，实现array2对array1数组的复制
     */
    @Test
    public void t2(){

        int[] array1 = new int[]{2,3,5,7,11,13,17,19};
        for (int i = 0; i < array1.length; i++){
            System.out.print(array1[i] + "\t");
        }
        //不能称为数组的复制
        int[] array2 = array1;
        for (int i = 0; i < array2.length; i++){
            if (i % 2 == 0){
                array2[i] = i;
            }
        }
        System.out.println();
        for (int i = 0; i < array1.length; i++){
            System.out.print(array1[i] + "\t");
        }
    }

    @Test
    public void t3(){
        int[] array1 = new int[]{2,3,5,7,11,13,17,19};
        //不能称为数组的复制
        int[] array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++){
            array2[i] = array1[i];
        }
    }

    @Test
    public void t4(){
        String[] arr = new String[]{"HH","DD","MM","BB","GG","AA"};
        //数组的复制(区别于数组变量的赋值，arr1 = arr)
        String[] arr1 = new String[arr.length];
        for (int i = 0; i < arr.length; i++){
            arr1[i] = arr[i];
        }

        //数组的反转
        for (int i = 0; i < arr1.length / 2; i++){
            String temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "\t");
        }
    }

    @Test
    public void t5(){
        String[] arr = new String[]{"HH","DD","MM","BB","GG","AA"};
        //查找(或搜索)
        //线性查找
        String dest = "BB";
        boolean flag = true;
        for (int i = 0; i < arr.length; i++){
            if (dest.equals(arr[i])){
                System.out.println("找到了指定的元素，位置为: " + i);
                flag = false;
                break;
            }
        }
        if (flag){
            System.out.println("很遗憾，没有找到!");
        }

        //二分法查找
        //前提：所要查找的数组必须有序
        int[] arr2 = new int[]{-98,-34,-12,0,12,34,53,65,74,89,99,100,145,167,187};

        int dest2 = -12;
        //初始的首索引
        int head = 0;
        //初始的末索引
        int end = arr2.length - 1;
        boolean flag2 = true;
        while (head <= end){
            int middle = (head + end) / 2;
            if (dest2 == arr2[middle]){
                System.out.println("找到了指定元素，位置为: " + middle);
                flag2 = false;
                break;
            }else if (arr2[middle] > dest2){
                end = middle - 1;
            }else {
                head = middle + 1;
            }
        }
        if (flag2){
            System.out.println("很遗憾,没有找到!");
        }

    }
}
