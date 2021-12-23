package com.qinh.exception;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-11-15-23:16
 */
public class ReturnExceptionDemo {
    static void methodA() {
        try {
            System.out.println("进入方法A");
            throw new RuntimeException("制造异常");
        } finally {
            System.out.println("用A方法的finally");
        }
    }

    static void methodB() {
        try {
            System.out.println("进入方法B");
            return;
        } finally {
            System.out.println("调用B方法的finally");
        }
    }

    static int methodC() {
        try {
            System.out.println("进入方法C");
            return 1;
        }finally {
            System.out.println("调用C方法的finally");
            return 2;
        }
    }

    public static int getInt() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            return a;
            /** return a 在程序执行到这一步的时候，这里不是return a 而是 return 30；这个返回 路径就形成了 * 但是呢，它发现后面还有finally，所以继续执行finally的内容，a=40 * 再次回到以前的路径,继续走return 30，形成返回路径之后，这里的a就不是a变量了，而是 常量30 */
        } finally {
            a = 40;
        }
        return a;
    }

    public static void main(String[] args) {
        try {
            methodA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        methodB();
        System.out.println(methodC());
        System.out.println(getInt());
    }
}
