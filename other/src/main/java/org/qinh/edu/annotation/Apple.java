package org.qinh.edu.annotation;

public class Apple extends Fruit {

    @Override
    public void info() {
        System.out.println("子类Apple");
    }

    @Deprecated
    public void getColor(){
        System.out.println("获取苹果的颜色");
    }
}
