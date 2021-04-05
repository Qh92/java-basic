package com.qinh.interfacetest.java8;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-06-0:00
 */
public class Man extends Father implements Filial,Spoony{
    @Override
    public void help() {
        System.out.println("我该救谁呢?");
        Filial.super.help();
        Spoony.super.help();
    }
}

interface Filial{
    default void help(){
        System.out.println("老妈，我来救你了");
    }
}
interface Spoony{
    default void help(){
        System.out.println("媳妇，别怕，我来了");
    }
}

class Father{
    public void help(){
        System.out.println("儿子，救我媳妇");
    }
}

