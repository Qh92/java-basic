package com.qinh.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 方法重写的规则之一：
 * 子类重写的方法抛出的异常类型不大于父类被重写的方法抛出的异常类型
 * @author Qh
 * @version 1.0
 * @date 2020-11-15-21:54
 */
public class OverrideTest {
    public static void main(String[] args) {
        OverrideTest overrideTest = new OverrideTest();
        overrideTest.display(new SubClass());

    }

    public void display(SuperClass s){
        try {
            s.method();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class SuperClass{
    public void method() throws IOException {

    }
}

class SubClass extends SuperClass{
    @Override
    public void method() throws FileNotFoundException {

    }
}
