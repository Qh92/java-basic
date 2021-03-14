package com.qinh;

import org.junit.Test;

/**
 * 一、数组的概述
 * 1.数据的理解:数组(Array)，是多个相同类型数据按一定顺序排列 的集合，并使用一个名字命名，并通过编号的方式 对这些数据进行统一管理
 *
 * 2.数组相关概念：
 * >数组名
 * >元素
 * >角标、下标、索引
 * >数组的长度：元素的个数
 *
 * 3.数组的特点
 * 1)数组的特点:数组是有序排列的
 * 2)数组属于引用数据类型的变量。数组的元素，既可以是基本数据类型，也可以是引用数据类型
 * 3)创建数组对象会在内存中开辟一整块连续的空间，而数组名中引用的是 这块连续空间的首地址
 * 4)数组的长度一旦确定，就不能修改
 *
 * 4.数组的分类：
 *    ①按照维数：一维数组、二维数组....
 *    ②按照数组元素的类型:基本数据类型元素的数组、引用数据类型元素的数组
 *
 * 5.一维数组的使用
 *    ①一维数组的声明和初始化
 *    ②如何调用数组的指定位置的元素
 *    ③如何获取数组的长度
 *    ④如何遍历数组
 *    ⑤数组元素的默认初始值
 *    ⑥数组的内存解析
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-14-16:08
 */
public class ArrayTest {

    @Test
    public void t1(){
        //1.一维数组的声明和初始化
        //声明
        int[] ids;
        //1.1静态初始化:数组的初始化和数组元素的赋值操作同时进行
        ids = new int[]{1001,1002,1003,1004};
        //1.2动态初始化:数组的初始化和数组元素的赋值操作分开进行
        String[] names = new String[5];

        //类型推断
        int[] arr = {1,2,3,4,5};
        //换行后类型推断不出来了
        /*int[] arr1 ;
        arr1 = {1,2,4,5,7};*/

        //总结:数组一旦初始化完成，其长度就确定了。

        //2.如何调用数组的指定位置的元素:通过角标的方式调用
        //数组的角标(或索引)从0开始的，到数组的长度-1结束
        names[0] = "melo";
        names[1] = "iverson";
        names[2] = "westbrook";
        names[3] = "curry";
        names[4] = "james";
        //names[5] = "kobe";//java.lang.ArrayIndexOutOfBoundsException: 5

        //3.如何获取数据的长度.属性:length
        System.out.println(names.length);//5
        System.out.println(ids.length);//4

        //4.如何遍历数组
        for (int i = 0; i < names.length; i++){
            System.out.println(names[i]);
        }
    }

    /**
     * 数组元素的默认初始值
     * > 数组元素类型是整型:0
     * > 数组元素类型是浮点型:0.0
     * > 数组元素类型是char型:0或'\u0000',而非'0'
     * > 数组元素是boolean型：false
     * > 数组元素是引用数据类型：null
     */
    @Test
    public void t2(){
        //数组元素的默认初始值
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        System.out.println(">>>>>>>>>>>>");
        short[] arr1 = new short[4];
        for (int i = 0; i < arr1.length; i++){
            System.out.println(arr1[i]);
        }
        System.out.println(">>>>>>>>>>");
        float[] f = new float[2];
        for (int i = 0; i < f.length; i++){
            System.out.println(f[i]);
        }
        System.out.println(">>>>>>>>>");
        char[] chars = new char[3];
        for (int i = 0; i < chars.length; i++){
            System.out.println("----:" + chars[i] + "***");
        }
        System.out.println(">>>>>");
        boolean[] booleans = new boolean[5];
        System.out.println(booleans[0]);
        System.out.println(">>>>>>>>>>>>");
        String[] strings = new String[3];
        System.out.println(strings[0]);
    }

    /**
     * 数组的内存解析
     */
    @Test
    public void t3(){

    }

}
