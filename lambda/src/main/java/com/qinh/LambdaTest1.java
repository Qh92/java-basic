package com.qinh;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 *
 * 1.举例：(o1,o2) -> Integer.compare(o1,o2)
 * 2.格式：
 *      -> :Lambda操作符 或 箭头操作符
 *      ->左边：Lambda形参列表（其实就是接口中的抽象方法的形参列表）
 *      ->右边：Lambda体（其实就是重写的抽象方法的方法体）
 * 3.Lambda表达式的使用：（分为6种情况介绍）
 *      总结:
 *      ->左边：Lambda形参列表的参数类型可以省略(类型推断)；如果Lambda形参列表只有一个参数，其括号可以省略
 *      ->右边：Lambda体应该使用一对{}包裹；如果Lambda体只有一条执行语句（可能是return语句），可以省略这一对{}和return关键字
 *
 * 4.Lambda表达式的本质：作为函数式接口的实例
 * 5.如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口。
 *   我们可以在一个接口上使用 @FunctionalInterface 注解，这样做可以检查它是否是一个函数式接口。
 * 6.所以以前用匿名实现类表示的现在都可以用Lambda表达式来写。
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-13-16:48
 */
public class LambdaTest1 {

    //语法格式一：无参，无返回值
    @Test
    public void t1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("***************");

        Runnable r2 = () -> System.out.println("我爱北京故宫");
        r2.run();
    }

    //语法格式二：Lambda 需要一个参数，但是没有返回值
    @Test
    public void t2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("今天湖人战胜了灰熊");

        System.out.println("*************");

        Consumer<String> con1 = (String s) -> System.out.println(s);
        con1.accept("逆转战胜灰熊");
    }

    //语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void t3(){
        Consumer<String> con1 = (String s) -> System.out.println(s);
        con1.accept("逆转战胜灰熊");

        System.out.println("*************");

        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("逆转战胜灰熊");
    }

    @Test
    public void t4(){
        //类型推断
        ArrayList<String> list = new ArrayList<>();
        int[] arr = {1,2,3};
    }

    //语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省
    @Test
    public void t5(){
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("逆转战胜灰熊");

        System.out.println("*************");

        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("逆转战胜灰熊");
    }

    //语法格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void t6(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        int compare1 = com1.compare(12, 21);
        System.out.println(compare1);

        System.out.println("***********");

        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        int compare2 = com2.compare(12, 6);
        System.out.println(compare2);
    }

    //语法格式六：当 Lambda 体只有一条语句时，return 与大括号若有，都可以省
    @Test
    public void t7(){
        Comparator<Integer> com1 = (o1,o2) -> {
            return o1.compareTo(o2);
        };
        int compare1 = com1.compare(12, 6);
        System.out.println(compare1);

        System.out.println("***********");

        Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);
        int compare2 = com1.compare(12, 6);
        System.out.println(compare2);
    }

    @Test
    public void t8(){
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("逆转战胜灰熊");

        System.out.println("*************");

        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("逆转战胜灰熊");
    }

}
