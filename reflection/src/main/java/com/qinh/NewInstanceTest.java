package com.qinh;

import com.qinh.entity.Person;
import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-0:15
 */
public class NewInstanceTest {

    @Test
    public void t1(){
        try {
            Class<Person> clazz = Person.class;
            /*
            newInstance():调用此方法，创建对应的运行时类的对象。内部调用了运行时类的空参构造器
            要想此方法正常的创建运行时类的对象，要求：
            1.运行时类必须提供空参的构造器
            2.空参的构造器的访问权限得够。通常，设置为public。

            在javabean中要求提供一个public的空参构造器。原因：
            1.便于通过反射，创建运行时类的对象
            2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
             */
            Person person = clazz.newInstance();
            //Person{name='null', age=0}
            System.out.println(person);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void t2(){
        int num = new Random().nextInt(3);
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "com.qinh.entity.Person";
                break;
            default:
        }
        Object instance = getInstance(classPath);
        System.out.println(instance);
    }

    /**
     * 创建一个指定类的对象。
     *
     * @param classPath 指定类的全类名
     * @return
     */
    public Object getInstance(String classPath){
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(classPath);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
