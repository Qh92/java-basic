package com.qinh.entity;

import java.io.Serializable;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-0:46
 */
public class Creature<T> implements Serializable {
    private static final long serialVersionUID = 5381327024739194713L;

    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }

}
