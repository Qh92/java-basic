package com.qinh;

import com.qinh.entity.Animal;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-23:50
 */
public class OtherTest {

    /**
     * 获取构造器结构
     */
    @Test
    public void t1(){
        Class<Animal> clazz = Animal.class;

        //getConstructors()：获取当前运行时类中声明为public的构造器
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors){
            System.out.println(c);
        }
        System.out.println();

        //getDeclaredConstructors()：获取当前运行时类中声明的所有的构造器
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c : declaredConstructors){
            System.out.println(c);
        }
    }
}
