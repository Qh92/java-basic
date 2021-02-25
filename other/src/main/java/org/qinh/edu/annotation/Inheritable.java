package org.qinh.edu.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Inheritable {
    /**
     * Annotation中的成员变量以方法的形式来定义
     * 可以为成员变量指定初始值（默认值） default
     * @return
     */
    String name() default "秦皓";
    int age() default 18;
}
