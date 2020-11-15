package com.qinh.exception;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * try-catch-finally中finally的使用
 *
 * 1.finally是可选的
 * 2.finally中声明的是一定会被执行的代码，即使catch中又出现异常了，try中有return语句，catch中有return语句等情况
 * 3.像数据库连接、输入输出流、网络编程Socket等资源，JVM是不能自动的回收的，我们需要自己手动的进行资源的释放。此时的资源释放，就需要声明在finally中
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-15-20:50
 */
public class FinallyTest {

    @Test
    public void test3() {
        FileInputStream fileInputStream = null;
        try {
            File file = new File("hello.txt");
            fileInputStream = new FileInputStream(file);//不处理异常时，会有编译异常

            int read = fileInputStream.read();//不处理异常时，会有编译异常
            while (read != -1){
                System.out.print((char) read);
                read = fileInputStream.read();//不处理异常时，会有编译异常
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fileInputStream != null){
                    fileInputStream.close();//不处理异常时，会有编译异常
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2(){
        int num = method();
        System.out.println(num);
    }
    public int method(){
        try{
            int[] arr = new int[10];
            System.out.println(arr[10]);
            return 1;
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return 2;
        }finally {
            System.out.println("一定会被执行.........");
            return 3;
        }
    }

    @Test
    public void test1(){
        try{
            int a = 10;
            int b = 0;
            System.out.println(a / b);
        }catch (ArithmeticException e){
            //e.printStackTrace();
            int[] arr = new int[10];
            System.out.println(arr[10]);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("最终会执行......");
        }
        System.out.println("测试是否最终会不会执行");
    }
}
