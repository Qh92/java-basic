package com.qinh.exer;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-14-20:08
 */
public class ArrayTest {

    /**
     * 升景坊单间短期出租4个月，550元/月（水电煤公摊，网费35元/月），空调、卫生间、厨房齐全。
     * 屋内均是IT行业人士，喜欢安静。所以要求来租者最好是同行或者刚毕业的年轻人，爱干净、安静
     */
    @Test
    public void t1(){
        int[] arr = new int[]{8,2,1,0,3};
        int[] index = new int[]{2,0,3,2,4,0,1,3,2,3,3};
        String tel = "";
        for(int i = 0;i < index.length;i++){
            tel += arr[index[i]];
        }
        System.out.println("联系方式：" + tel);
    }

    /**
     * 从键盘读入学生成绩，找出最高分， 并输出学生成绩等级。
     * 成绩>=最高分-10    等级为’A’
     * 成绩>=最高分-20    等级为’B’
     * 成绩>=最高分-30    等级为’C’
     * 其余 等级为’D’
     * 提示：先读入学生人数，根据人数创建int数组， 存放学生成绩。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生人数: ");
        int num = scanner.nextInt();
        int[] scores = new int[num];
        String[] names = new String[num];
        System.out.println("请输入学生姓名: ");
        for (int i = 0; i < num; i++){
            String name = scanner.next();
            names[i] = name;
        }
        System.out.println("开始输入学生成绩: ");
        //String[] names = new String[]{"melo","james","iverson","curry","westbrook"};
        //找出最高的成绩
        int max = 0;
        for (int i = 0; i < num; i++){
            scores[i] = scanner.nextInt();
            if (scores[i] >= max){
                max = scores[i];
            }
        }
        //找出最高的成绩
        /*int max = 0;
        for (int i = 0; i < num; i++){
            if (scores[i] >= max){
                max = scores[i];
            }
        }*/
        System.out.println("最高分为: " + max);
        String[] level = new String[num];
        for (int i = 0; i < num; i++){
            if (scores[i] >= max - 10){
                level[i] = "等级为'A'";
            }else if(scores[i] >= max - 20){
                level[i] = "等级为'B'";
            }else if(scores[i] >= max - 30){
                level[i] = "等级为'C'";
            }else {
                level[i] = "等级为'D'";
            }
        }

        for (int i = 0; i < num; i++){
            System.out.println(names[i] + "的成绩: " + scores[i]  + level[i]);
        }
    }

    @Test
    public void t2(){

    }

}
