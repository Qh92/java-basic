package com.qinh.enumLearn;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

/**
 * 使用enum关键字来定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-16-23:10
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        //toString()
        System.out.println(summer);
        //System.out.println(Season1.class.getSuperclass());
        //values();
        Season1[] values = Season1.values();
        for (int i = 0 ; i < values.length;i++){
            System.out.println(values[i]);
            values[i].show();
        }

        System.out.println("***********");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0;i < values1.length;i++){
            System.out.println(values1[i]);
        }

        //valueOf(String objName):返回枚举类中对象名为objName的对象
        //如果没有objName的枚举类对象，则抛出异常：java.lang.IllegalArgumentException
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
        winter.show();
    }


}
interface Info{
    void show();
}

enum Season1 implements Info{
    //1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁静的夏天");
        }
    },
    AUTUMN("秋天","秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","白雪皑皑"){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;
    //2..私有化类的构造器,并给对象属性赋值
    private Season1(String seasonName,String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    /*@Override
    public void show() {
        System.out.println("这是一个季节");
    }*/
}
