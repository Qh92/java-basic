package com.qinh.inheritance.exer;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-28-16:00
 */
public class KidTest {

    public static void main(String[] args) {
        Kids kids = new Kids(12);
        kids.printAge();

        kids.setSalary(0);
        kids.setSex(1);
        kids.employeed();
        kids.manOrWoman();

    }
}
