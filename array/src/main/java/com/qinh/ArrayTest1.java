package com.qinh;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

/**
 * 二维数组的使用
 *
 * 1.理解：
 * 对于二维数组的理解，我们可以看成是一维数组 array1又作为另一个一维数组array2的元素而存在。
 * 其实，从数组底层的运行机制来看，其实没 有多维数组。
 *
 * 2.二维数组的使用
 *    ①二维数组的声明和初始化
 *    ②如何调用数组的指定位置的元素
 *    ③如何获取数组的长度
 *    ④如何遍历数组
 *    ⑤数组元素的默认初始值
 *    ⑥数组的内存解析
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-14-21:06
 */
public class ArrayTest1 {
    @Test
    public void t1(){
        //1.二维数组的声明和初始化
        int[] arr = new int[]{1,2,3,4};
        //静态初始化
        int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8}};
        //动态初始化1
        String[][] arr2 = new String[3][2];
        //动态初始化2
        String[][] arr3 = new String[3][];
        //错误的情况
        //String[][] arr4 = new String[][5];
        //String[2][3] arr5 = new String[][];
        //int[][] arr6 = new int[3][4]{{1,2},{3,4}};

        //也是正确的写法
        int[] arr4[] = new int[][]{{1,2,3},{4,5},{6,7,8}};
        int[][] arr5 = {{1,2,3},{4,5},{6,7,8}};

        //2.如何调用数组的指定位置的元素
        //输出2
        System.out.println(arr1[0][1]);
        //输出null
        System.out.println(arr2[1][1]);
        //java.lang.NullPointerException
        //System.out.println(arr3[1][0]);
        arr3[1] = new String[4];
        //null
        System.out.println(arr3[1][0]);

        //3.如何获取数组的长度
        //3
        System.out.println(arr4.length);
        //2
        System.out.println(arr4[1].length);

        System.out.println();
        //4.如何遍历数组
        for (int i = 0; i < arr4.length; i++){
            for (int j = 0; j < arr4[i].length; j++){
                System.out.print(arr4[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 规定：二维数组分为外层数组的元素，内层数组的元素
     *       int[][] arr = new int[4][3];
     *       外层元素: arr[0],arr[1]等
     *       内层元素: arr[0][0],arr[1][2]等
     *
     * 数组元素的默认初始值
     *      针对初始化方式一: 比如: int[][] arr = new int[4][3];
     *          外层元素的初始化值为:地址值
     *          内层元素的初始化值为:与一维数组初始化值情况相同
     *      针对初始化方式二: 比如:  double[][] arr2 = new double[4][];
     *          外层元素的初始化值为: null
     *          内层元素的初始化值为: 不能调用，否则报错
     *
     *
     */
    @Test
    public void t2(){
        int[][] arr = new int[4][3];

        //[I@32a1bec0
        System.out.println(arr[0]);
        //0
        System.out.println(arr[0][0]);
        //[[I@22927a81
        System.out.println(arr);

        System.out.println("************");
        double[][] arr2 = new double[4][];
        //null
        System.out.println(arr2[1]);
        //java.lang.NullPointerException
        //System.out.println(arr2[1][2]);
    }
}
