package com.qinh.statictest;

/**
 * static关键字的使用
 *
 * 1.static:静态的
 * 2.static可以用来修饰：属性、方法、代码块、内部类
 *
 * 3.使用static修饰属性:静态变量（类变量）
 *      3.1属性：按是否使用static修饰，又分为：静态属性 vs 非静态属性(实例变量)
 *         实例变量：创建了类的多个对象，每个对象都独立的拥有一套类中的非静态属性
 *                  当修改 了其中一个对象中的非静态属性时，不会导致其它对象中同样的属性值的修改
 *          静态变量：创建了类的多个对象，每个对象都共享同一个静态变量。
 *                  当通过某个对象修改静态变量时，会导致其它对象调用此静态变量时，是修改过了的。
 *      3.2 static修饰属性的其它说明：
 *          ① 静态变量随着类的加载而加载，可以通过"类.静态变量"的方式进行调用
 *          ② 静态变量的加载要早于对象的创建
 *          ③ 由于类只会加载一次，则静态变量在内存中也只会存在一份，存在方法区的静态域中。
 *          ④    类变量  实例变量
 *          类     yes     no
 *          对象    yes    yes
 *
 * 4.使用static修饰方法
 *
 * @Author Qh
 * @Date 2021/4/2 19:35
 * @Version 1.0
 */
public class StaticTest {
    public static void main(String[] args){
        Chinese.nation = "中国";

        Chinese c1 = new Chinese();
        c1.name = "james";
        c1.age = 36;
        c1.nation = "CHN";

        Chinese c2 = new Chinese();
        c2.name = "马龙";
        c2.age = 30;
        c2.nation = "CHINA";
        System.out.println(c1.nation);


    }
}

/**
 * 中国人
 */
class Chinese{
    String name;
    int age;
    static String nation;
}
