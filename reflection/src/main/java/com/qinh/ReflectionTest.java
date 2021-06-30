package com.qinh;

import com.qinh.entity.Person;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-28-21:58
 */
public class ReflectionTest {

    //反射之前，对于Person的操作
    @Test
    public void t1(){

        //1.创建Person类的对象
        Person p1 = new Person("Tom",12);
        //2.通过对象，调用其内部的属性和方法
        p1.setAge(18);
        System.out.println(p1.toString());//Person{name='Tom', age=18}

        p1.show();
        //在Person类外部，不可以通过Person类的对象调用其内部私有结构
        //比如：name、showNation()以及私有的构造器
    }

    //反射之后，对于Person的操作
    @Test
    public void t2() throws Exception {
        /*
        关于java.lang.Class类的理解
        1.类的加载过程：
        程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
        接着使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程就称为类的加载。加载到内存中的类，就称为运行时类，此运行时类，就作为Class的一个实例。
        2.换句话说，Class的实例就对应着一个运行时类。
        3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，可以通过不同的方式来获取此运行时类。

         */
        Class<Person> clazz = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        Person p1 = constructor.newInstance("Tom", 12);
        System.out.println(p1.toString());//Person{name='Tom', age=12}
        //2.通过反射，调用对象指定的属性、方法
        Field age = clazz.getDeclaredField("age");
        age.set(p1,10);
        System.out.println(p1.toString());//Person{name='Tom', age=10}
        //3.调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p1);//你好，我是一个人

        //4.通过反射，可以调用Person类的私有结构。比如：私有构造器、方法、属性
        //调用私有的构造器
        Constructor<Person> cons = clazz.getDeclaredConstructor(String.class);
        cons.setAccessible(true);
        Person p2 = cons.newInstance("Jerry");
        System.out.println(p2.toString());//Person{name='Jerry', age=0}
        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p2,"HanMeimei");
        System.out.println(p2.toString());//Person{name='HanMeimei', age=0}
        //调用私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p2, "中国");//我的国籍是：中国
        System.out.println(nation);//中国
    }

    /*
    疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
    建议：直接new的方式
    什么时候会使用反射的方式。反射的特征：动态性
    疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
    不矛盾。
     */

    //获取Class的实例的方式（前三种方式需要掌握）
    @Test
    public void t3() throws ClassNotFoundException {
        //方式1：调用运行时类的属性：.class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);//class com.qinh.entity.Person

        //方式2：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class<? extends Person> clazz2 = p1.getClass();
        System.out.println(clazz2);//class com.qinh.entity.Person

        //方式3：调用Class的静态方法：forName(String classPath)
        Class<?> clazz3 = Class.forName("com.qinh.entity.Person");
        System.out.println(clazz3);//class com.qinh.entity.Person

        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1 == clazz3);//true

        //方式4：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.qinh.entity.Person");
        System.out.println(clazz4);//class com.qinh.entity.Person
        System.out.println(clazz1 == clazz4);//true
    }

    //Class实例可以是哪些结构的说明
    @Test
    public void t4(){
        Class c1 = Object.class;
        Class<Map.Entry> entryClass = HashMap.Entry.class;
        Class<AbstractMap.SimpleEntry> simpleEntryClass = HashMap.SimpleEntry.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;
        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);//true
    }

}
