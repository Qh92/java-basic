package com.qinh.abstracttest;

/**
 * 抽象类的匿名子类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-05-11:37
 */
public class PersonTest {
    public static void main(String[] args) {
        //匿名对象
        method(new Student());
        /*Student student = new Student() {

        };
        student.breath();*/
        /*
        非匿名的类非匿名的对象
         */
        Worker worker = new Worker();
        method1(worker);
        /*
        非匿名的类匿名的对象
         */
        method1(new Worker());
        System.out.println("*****************");
        /*
        创建一匿名子类的对象:p
         */
        Person p = new Person() {
            @Override
            public void eat2() {
                System.out.println("吃东西");
            }

            @Override
            public void breath() {
                System.out.println("好好呼吸");
            }
        };
        method1(p);
        System.out.println("******************");
        /*
        创建匿名子类的匿名对象
         */
        method1(new Person() {
            @Override
            public void eat2() {
                System.out.println("吃好吃的东西");
            }

            @Override
            public void breath() {
                System.out.println("好好呼吸新鲜空气");
            }
        });

    }

    public static void method(Student s){

    }
    public static void method1(Person p){
        p.eat2();
        p.breath();
    }
}

class Worker extends Person{

    @Override
    public void breath() {

    }

    @Override
    public void eat2() {

    }
}
