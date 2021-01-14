package com.qinh.entity;

import java.io.Serializable;

/**
 * Person需要满足如下的要求，方可序列化
 * 1.需要实现Serializable接口
 * 2.需要当前类提供一个全局常量 ：serialVersionUID
 *
 * @Author Qh
 * @Date 2021/1/14 23:52
 * @Version 1.0
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 6628193888648150500L;
    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
