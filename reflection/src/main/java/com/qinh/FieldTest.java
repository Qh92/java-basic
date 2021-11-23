package com.qinh;

import com.qinh.entity.Animal;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-1:01
 */
public class FieldTest {

    @Test
    public void t1(){
        Class<Animal> clazz = Animal.class;

        //获取属性结构
        /*
        getFields():获取当前运行时类及其父类中声明为public访问权限的属性
         */
        Field[] fields = clazz.getFields();
        for (Field f : fields){
            System.out.println(f);
        }
        System.out.println();
        /*
        getDeclaredFields():获取当前运行时类中声明的所有属性（不包含父类中声明的属性）
         */
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            System.out.println(f);
        }
        System.out.println();
        //获取父类的所有属性（包含私有属性）
        Field[] fields1 = clazz.getSuperclass().getDeclaredFields();
        for (Field f : fields1){
            System.out.println(f);
        }
    }

    /**
     * 权限修饰符   数据类型  变量名
     *
     */
    @Test
    public void t2(){
        Class<Animal> clazz = Animal.class;

        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            //1.权限修饰符
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier)+"\t");
            //2.数据类型
            Class<?> type = f.getType();
            System.out.print(type.getName()+"\t");
            //3.变量名
            String name = f.getName();
            System.out.print(name);
            System.out.println();
        }

    }
}
