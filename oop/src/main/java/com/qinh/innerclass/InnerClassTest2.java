package com.qinh.innerclass;

/**
 * 局部内部类使用注意事项
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-06-13:44
 */
public class InnerClassTest2 {

    /**
     * 在局部内部类的方法中(比如：show)如果调用局部内部类所声明的方法(比如:method)中的局部变量(比如:num)的话
     * 要求此局部变量声明为final的
     * JDK7及以前版本，要求此局部变量显示声明为final的
     * JDK8及之后的版本，可以省略final的声明
     */
    public void method(){
        //局部变量
        int num = 10;
        class AA{
            public void show(){
//                num = 20;
                System.out.println(num);
            }
        }
    }

}
