package org.qinh.edu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//使用jdk的元数据Annotation:@Retention
@Retention(RetentionPolicy.RUNTIME)
//使用jdk的元数据Annotation:@Target
@Target(ElementType.METHOD)
public @interface Testable {
}
