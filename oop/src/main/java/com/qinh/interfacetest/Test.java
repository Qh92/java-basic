package com.qinh.interfacetest;

/**
 * 测试接口变量类型：只能为static final修饰的变量
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-05-22:31
 */
public class Test {
    public static void main(String[] args) {
        Child test = new Child("test");

    }
}

interface Playable {
    void play();
}
interface Bounceable {
    void play();
}
interface Rollable extends Playable,
        Bounceable {
    Ball ball = new Ball("PingPang");
}
class Ball implements Rollable {
    private String name;
    public String getName() {
        return name;
    }
    public Ball(String name) {
        System.out.println("父类实例化");
        this.name = name;
        Child child = new Child("111");
    }
    public void play() {
        //异常ball 为 public static final 修饰的
        //ball = new Ball("Football");
        System.out.println(ball.getName());
    }
}

class Child extends Ball{

    public Child(String name) {
        super(name);
        System.out.println("子类实例化");
    }
}
