package com.qinh;

import com.qinh.entity.Animal;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-02-22:24
 */
public class ReflectionTest1 {

    @Test
    public void t1() throws Exception {
        Class<Animal> clazz = Animal.class;
        //创建运行时类的对象
        Animal animal = clazz.newInstance();
        //获取指定的属性:要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("id");
        /*
        设置当前属性的值
        set():参数1：指明设置哪个对象的属性  参数2：将此属性值设置为多少
         */
        id.set(animal,1001);
        /*
        获取当前属性的值
        get()：参数1：获取哪个对象的当前属性值
         */
        int aId = (int)id.get(animal);
        System.out.println(aId);
    }

    /**
     * 如何操作运行时类中的指定的属性
     */
    @Test
    public void t2() throws Exception {
        Class<Animal> clazz = Animal.class;
        //创建运行时类的对象
        Animal animal = clazz.newInstance();
        //1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");
        //2.保证当前属性是可访问的
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        name.set(animal,"Cat");
        System.out.println(name.get(animal));
    }

    /**
     * 如何操作运行时类中的指定的方法
     */
    @Test
    public void t3() throws Exception {
        Class<Animal> clazz = Animal.class;
        //创建运行时类的对象
        Animal animal = clazz.newInstance();
        /*
        1.获取指定的某个方法
        getDeclaredMethod():参数1：指明获取的方法的名称  参数2：指明获取的方法的形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2.保证当前方法是可访问的
        show.setAccessible(true);
        /*
        3.调用invoke():参数1：方法的调用者 参数2：给方法形参赋值的实参
        invoke()的返回值即为对应类中调用的方法的返回值
        String kind = animal.show("灵长类");
         */
        Object value = show.invoke(animal, "灵长类");
        System.out.println(value);

        System.out.println("**********调用静态方法********");
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
        showDesc.invoke(clazz);
        //这样写也可以
        //showDesc.invoke(null);
    }

    /**
     * 如何操作运行时类中的指定的构造器
     */
    @Test
    public void t4() throws Exception {
        Class<Animal> clazz = Animal.class;
        //private Animal(String name)
        /*
        1.获取指定的构造器
        getDeclaredConstructor():参数：指明构造器的参数列表
         */
        Constructor<Animal> constructor = clazz.getDeclaredConstructor(String.class);
        //2.保证此构造器是可访问的
        constructor.setAccessible(true);
        //3.调用此构造器创建运行时类的对象
        Animal dog = constructor.newInstance("Dog");
        System.out.println(dog);
    }
}
