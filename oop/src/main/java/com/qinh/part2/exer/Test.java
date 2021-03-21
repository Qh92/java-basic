package com.qinh.part2.exer;

import java.io.PrintStream;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-21-13:23
 */
public class Test {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        //需要在method方法被调用之后，仅打印出a=100,b=200，请写出method方法的代码
        method(a,b);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }


    public static void method(int a,int b){
        //方式1
        /*a = a * 10;
        b = b * 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.exit(0);*/

        //方式2
        PrintStream printStream = new PrintStream(System.out){
            @Override
            public void println(String x) {
                if ("a = 10".equals(x)){
                    x = "a = 100";
                }else if ("b = 10".equals(x)){
                    x = "b = 200";
                }
                super.println(x);
            }
        };
        System.setOut(printStream);


    }
}

interface T1 extends T2,T3{

}

interface T2{}
interface T3{}