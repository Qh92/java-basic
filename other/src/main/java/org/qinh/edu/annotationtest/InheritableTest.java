package org.qinh.edu.annotationtest;

import org.qinh.edu.annotation.Inheritable;

/**
 * 如果带成员变量的Annotation没有指定默认值，使用带成员变量的Annotation时，需要为成员变量赋值
 * 如果带成员变量的Annotation指定了默认值，使用Annotation时，可以不为它的成员变量指定值，如果指定了值，默认值则不起作用
 */
@Inheritable(name="测试",age=18)
class Base{

}

public class InheritableTest extends Base {
    public static void main(String[] args) {
        System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));//true
    }
}
