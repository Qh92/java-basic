package com.qinh.std;

import demo2.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 泛型在继承中的体现
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-15-17:22
 */
public class GenericExtendsTest {
    public void printCollection1(Collection c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); k++) {
            System.out.println(i.next());
        }
    }

    public void printCollection2(Collection<Object> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }

    @Test
    public void t1(){
        List<String> strList = new ArrayList<>();
        strList.add("12");
        strList.add("13");
        printCollection1(strList);
        List<Integer> intList = new ArrayList<>();
        intList.add(12);
        intList.add(14);
        printCollection1(intList);

        /*
        如果B是A的一个子类型（子类或者子接口），而G是具有泛型声明的 类或接口，G<B>并不是G<A>的子类型！
        比如：String是Object的子类，但是List<String >并不是List<Object> 的子类
         */
        //编译不通过
        //printCollection2(strList);
    }

    public void testGenericAndSubClass() {
        Person1[] persons = null;
        Man[] mans = null; // 而 Person[] 是 Man[] 的父类.
        persons = mans;
        Person1 p = mans[0];
        // 在泛型的集合上
        List<Person> personList = null; List<Man> manList = null;
        // personList = manList;(报错)
    }

    public void addString(List<? extends Object> list){
        //list.add(new Person1());
    }


}

class Person1{

}

class Man extends Person1{

}
