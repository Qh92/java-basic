package com.qinh.entity;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-0:48
 */
@MyAnnotation(value = "hi")
public class Animal extends Creature<String> implements Comparable<String> ,MyInterface{

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
    private String show(String kind){
        System.out.println("动物类别是: " + kind);
        return kind;
    }

    public void display(String feature,int age) throws NullPointerException,ClassCastException{
        System.out.println("特征：" + feature + " 年纪: " + age);
    }

    @Override
    public void info() {
        System.out.println("动物类");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static void showDesc(){
        System.out.println("小动物有什么坏心眼。");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
