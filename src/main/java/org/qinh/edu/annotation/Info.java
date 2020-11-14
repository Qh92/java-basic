package org.qinh.edu.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//运行时存在
public @interface Info {
    String name() default "修饰类";//名称
    String whatToDo() default "这个类可以被继承";//能干什么
}
