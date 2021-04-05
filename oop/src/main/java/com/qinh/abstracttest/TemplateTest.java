package com.qinh.abstracttest;

/**
 * 抽象类的应用:模板方法的设计模式
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-05-12:23
 */
public class TemplateTest {
    public static void main(String[] args) {
        SubTemplate t = new SubTemplate();
        t.spendTime();
    }
}

abstract class Template{

    public void spendTime(){
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为:" + (end - start));
    }
    public abstract void code();
}

class SubTemplate extends Template{

    @Override
    public void code() {
        for (int i = 2; i < 1000; i++){
            boolean flag = true;
            for (int j = 2; j < Math.sqrt(i); j++){
                if (i % j == 0){
                    flag = false;
                    break;
                }
                if (flag){
                    System.out.println(i);
                }
            }
        }
    }
}
