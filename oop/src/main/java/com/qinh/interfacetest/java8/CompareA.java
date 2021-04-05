package com.qinh.interfacetest.java8;

/**
 * JDK8:除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-05-23:37
 */
public interface CompareA {
    /**
     * 静态方法
     */
    public static void method1(){
        System.out.println("CompareA:北京");
    }

    /**
     * 默认方法
     */
    public default void method2(){
        System.out.println("CompareA:上海");
    }
    default void method3(){
        System.out.println("CompareA:上海");
    }
}
