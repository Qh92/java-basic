package com.qinh.entity;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-0:48
 */
@MyAnnotation(value = "hi")
public class Animal extends Creature<System> implements Comparable<String> ,MyInterface{

    private String name;
    int age;
    public int id;

    public Animal() {
    }

    @MyAnnotation(value = "小猫咪")
    private Animal(String name) {
        this.name = name;
    }

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation(value = "猫科")
    private void show(String kind){
        System.out.println("动物类别是: " + kind);
    }

    public String display(String feature,int age) throws NullPointerException,ClassCastException{
        return feature + age;
    }

    @Override
    public void info() {
        System.out.println("动物类");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
