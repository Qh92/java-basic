package com.qinh.entity;

import java.io.Serializable;

/**
 * Person需要满足如下的要求，方可序列化
 * 1.需要实现Serializable接口
 * 2.需要当前类提供一个全局常量 ：serialVersionUID
 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的。（默认情况下，基本数据类型是可序列化的）
 * 4.ObjectInputStream和ObjectOutputStream不能序列化static和transient修饰的成员变量
 *
 * @Author Qh
 * @Date 2021/1/14 23:52
 * @Version 1.0
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 6628193888648150500L;
    private String name;

    private int age;

    private Account account;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Account account) {
        this.name = name;
        this.age = age;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }
}
