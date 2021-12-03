package com.qinh.builder;

/**
 * 链式编程测试
 *
 * @author Qh
 * @version 1.0
 * @date 2021/12/3 16:24
 */
public class BuilderTest {

    private String name;

    private int age;

    private String address;

    private String hobby;


    public static void main(String[] args){
        BuilderTest builderTest = new BuilderTest().setName("qinh").setAge(18).setAddress("成都").setHobby("热爱打篮球");
        System.out.println(builderTest);
    }


    public BuilderTest setName(String name) {
        this.name = name;
        return this;
    }


    public BuilderTest setAge(int age) {
        this.age = age;
        return this;
    }

    public BuilderTest setAddress(String address) {
        this.address = address;
        return this;
    }

    public BuilderTest setHobby(String hobby) {
        this.hobby = hobby;
        return this;
    }

    @Override
    public String toString() {
        return "BuilderTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}


