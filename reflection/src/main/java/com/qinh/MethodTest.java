package com.qinh;

import com.qinh.entity.Animal;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Method;

/**
 * 获取运行时类的方法结构
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-1:17
 */
public class MethodTest {

    @Test
    public void t1(){
        Class<Animal> clazz = Animal.class;

        //getMethods():获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }
        System.out.println();
        //getDeclaredMethods()：获取运行时类中声明的所有方法（不包含父类中声明的方法）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods){
            System.out.println(m);
        }
    }


}
