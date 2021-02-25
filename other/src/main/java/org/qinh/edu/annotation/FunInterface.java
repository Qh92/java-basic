package org.qinh.edu.annotation;

@FunctionalInterface
public interface FunInterface {
    static void foo(){
        System.out.println("foo类函数");
    }

    default void bar(){
        System.out.println("bar默认函数");
    }

    void test();//只定义一个抽象方法

    //void eat();
}
