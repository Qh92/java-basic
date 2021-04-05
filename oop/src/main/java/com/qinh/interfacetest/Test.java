package com.qinh.interfacetest;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-05-22:31
 */
public class Test {
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
        return name; }
    public Ball(String name) {
        this.name = name; }
    public void play() {
        //异常ball 为 public static final 修饰的
        //ball = new Ball("Football");
        System.out.println(ball.getName());
    } }
