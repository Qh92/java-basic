package com.qinh.exer;

import org.junit.Test;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-15-0:06
 */
public class ArrayExer1 {

    /**
     * 获取arr数组中所有元素的和。
     * 提示：使用for的嵌套循环即可。
     */
    @Test
    public void t1(){
        int[][] arr = new int[][]{{3,5,8},{12,9},{7,0,6,4}};
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }

    /**
     * 声明：int[] x,y[]; 在给x,y变量赋值以后，以下选项允许通过编译的是：
     * a) x[0] = y;  no
     * b) y[0] = x;   yes
     * c) y[0][0] = x;   no
     * d) x[0][0] = y;  no
     * e) y[0][0] = x[0];  yes
     * f) x = y;   no
     * 提示： 一维数组：int[] x  或者int x[]   二维数组：int[][] y 或者 int[] y[]  或者 int y[][]
     */
    @Test
    public void t2(){
    }

    /**
     * 使用二维数组打印一个 10 行杨辉三角。
     * 【提示】
     * 1. 第一行有 1 个元素, 第 n 行有 n 个元素
     * 2. 每一行的第一个元素和最后一个元素都是 1
     * 3. 从第三行开始, 对于非第一个元素和最后一个元素的元素。
     * 即： yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
     */
    @Test
    public void t3(){
        int[][] yanghui = new int[10][];
        for (int i = 0; i < yanghui.length; i++){
            yanghui[i] = new int[i+1];
        }
        for (int i = 0; i < yanghui.length; i++){
            for (int j = 0; j < yanghui[i].length; j++){
                if (j == 0 || j == yanghui[i].length - 1){
                    yanghui[i][j] = 1;
                }else {
                    yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
                }
            }
        }
        for (int i = 0; i < yanghui.length; i++){
            for (int j = 0; j < yanghui[i].length; j++){
                System.out.print(yanghui[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 创建一个长度为6的int型数组，要求数组元素的值都在1-30之间，且是随机赋值。同时，要求 元素的值各不相同
     */
    @Test
    public void t4(){
        int[] arr = new int[6];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 30 + 1);
            for (int j = 0; j < i - 1; j++){
                while (arr[i] == arr[j]){
                    arr[i] = (int)(Math.random() * 30 + 1);
                    if (arr[i] != arr[j]){
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
    }
}
