package com.qinh.part1;

/**
 * 一、Java面向对象学习的三条主线
 * 1.Java类及类的成员：属性、方法、构造器；代码块、内部类
 * 2.面向对象的三大特征：封装性、继承性、多态性、（抽象性）
 * 3.其它关键字：this、super、static、final、abstract、interface、package、import
 *
 * 二、”人把大象装进冰箱“
 * 1.面向过程：强调的是功能行为，以函数为最小单位，考虑怎么做
 * ① 把冰箱门打开
 * ② 抬起大象，塞进冰箱
 * ③ 把冰箱门关闭
 * 2.面向对象：强调具备了功能的对象，以类/对象为最小单位，考虑谁来做
 * 人{
 *     打开(冰箱){
 *         冰箱.打开();
 *     }
 *      抬起(大象){
 *          大象.进入(冰箱);
 *      }
 *      关闭(冰箱){
 *          冰箱.关闭();
 *      }
 * }
 * 冰箱{
 *     打开();
 *     关闭();
 * }
 * 大象{
 *     进入(冰箱){
 *
 *     };
 * }
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-17-0:13
 */
public class OOPTest {
}
