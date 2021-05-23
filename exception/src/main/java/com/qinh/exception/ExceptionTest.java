package com.qinh.exception;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 一、异常体系结构
 * java.lang.Throwable
 *      |-----java.lang.Error:一般不编写针对性的代码进行处理
 *      |-----java.lang.exception:可以进行异常的处理
 *          |------编译时异常（checked）
 *                  |----IOExeption
 *                      |-----FileNotFoundException
 *                  |----ClassNotFoundExeption
 *          |------运行时异常（unchecked）
 *                  |----NullPointerException
 *                  |----ArrayIndexOutOfBoundsException
 *                  |----ClassCastException
 *                  |----NumberFormatException
 *                  |----InputMismatchException
 *                  |----ArithmeticException
 *
 * 面试题：常见的异常都有哪些？举例说明。
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-14-22:56
 */
public class ExceptionTest {
    private static final Logger log = LoggerFactory.getLogger(ExceptionTest.class);
    //编译时异常
    @Test
    public void test7() throws IOException {
        File file = new File("hello.txt");
        FileInputStream fileInputStream = new FileInputStream(file);//不处理异常时，会有编译异常

        int read = fileInputStream.read();//不处理异常时，会有编译异常
        while (read != -1){
            System.out.print((char) read);
            read = fileInputStream.read();//不处理异常时，会有编译异常
        }
        fileInputStream.close();//不处理异常时，会有编译异常
    }

    //运行时异常
    //ArithmaticException
    @Test
    public void test6(){
        int a = 0;
        int b = 0;
        System.out.println(a / b);
    }
    //InputMismatchException
    @Test
    public void test5(){
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        System.out.println(score);
    }

    //NumberFormatException
    @Test
    public void test4(){
        String str = "abc";
        int i = Integer.parseInt(str);
    }
    //ClassCastException
    @Test
    public void test3(){
        Object obj = new Date();
        String str = (String) obj;
    }
    //ArrayIndexOutOfBoundsException
    @Test
    public void test2(){
        int[] arr = new int[10];
        //System.out.println(arr[11]);
        //StringIndexOutOfBoundsException
        String s = "abc";
        System.out.println(s.charAt(4));
    }

    //NullPointerException
    @Test
    public void test1(){
        int[] arr = null;
        //log.info(String.valueOf(arr[3]));
        //System.out.println(arr[3]);

        String s = "abc";
        s = null;
        System.out.println(s.charAt(0));
    }

    public static void main(String[] args) {
        //java.util.InputMismatchException
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        System.out.println(score);
    }
}
