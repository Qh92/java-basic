package com.qinh;

import java.io.IOException;

/**
 * 自定义函数式接口
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-13-17:24
 */
@FunctionalInterface
public interface MyInterface {

    void method1(String str) throws IOException;

    default void method2() throws RuntimeException{
        System.out.println("method2");
    };
}
