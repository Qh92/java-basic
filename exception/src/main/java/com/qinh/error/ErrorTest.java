package com.qinh.error;

/**
 * Error:
 * java虚拟机无法解决的严重问题。如：JVM系统内部错误，资源耗尽等严重情况。
 * 比如：StackOverflowError 和 OOM
 * 一般不编写针对性的代码进行处理
 *
 * @description
 * @author Qh
 * @version 1.0
 * @date 2020-11-14-22:34
 */
public class ErrorTest {
    public static void main(String[] args) {
        //1.栈溢出：java.lang.StackOverflowError
        //main(args);

        //2.堆溢出：java.lang.OutOfMemoryError: Java heap space
        Integer[] arr = new Integer[1024*1024*1024];
    }

}
