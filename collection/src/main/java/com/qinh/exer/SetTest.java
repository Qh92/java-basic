package com.qinh.exer;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 面试题 难度 **
 * @author Qh
 * @version 1.0
 * @date 2020-12-08-21:21
 */
public class SetTest {
    /**
     * Set:数组+链表
     * 1.计算出p1和p2的hashCode值，然后存放在数组对应位置
     * 2.remove时，先计算修改后的p1的hashCode值，此时hashCode值与之前计算出的hashCode值不一致，即删除不了
     * 3.add时，再次计算新对象的hashCode值，拿到此对象的hash值与最开始p1及p2对象的hash值比较，发现都不一致，即加入成功
     * 4.add时，计算新对象的hashCode值，此时发现当前hash值与p1一致，进而比较当前对象与p1对象的equals,发现不一致，则加入成功
     */
    @Test
    public void t1(){
        HashSet set = new HashSet();
        //Person类重写了equals()和hashCode()
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        set.add(p1);
        set.add(p2);
        p1.setName("CC");
        set.remove(p1);
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}]
        set.add(new Person(1001,"CC"));
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}]
        set.add(new Person(1001,"AA"));
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}, Person{id=1001, name='AA'}]
    }


    /**
     * HashMap原理同HashSet
     */
    @Test
    public void t2() {
        HashMap<Person,String> map = new HashMap<>();

        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        map.put(p1, "p1");
        map.put(p2, "p2");

        p1.setName("CC");

        System.out.println(map.get(p1));

        map.put(new Person(1001,"CC"),"CC");

        System.out.println(map.get(p1));

        map.put(new Person(1001,"AA"),"aa");

        System.out.println(map.get(new Person(1001,"AA")));

    }
}
