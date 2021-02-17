package com.qinh.generateclass;

/**
 * 自定义一个类的加载器，用于将字节码转换为class对象
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-17-10:27
 */
public class MyClassLoader extends ClassLoader{

    public Class<?> defineMyClass(byte[]b, int off, int len){
        return super.defineClass(null,b, off, len);
    }
}
