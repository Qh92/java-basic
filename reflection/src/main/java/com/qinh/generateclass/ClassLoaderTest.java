package com.qinh.generateclass;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-17-10:33
 */
public class ClassLoaderTest implements MethodInterceptor {

    @Test
    public void t1() throws Exception {
        //读取本地的class文件内的字节码，转换成字节码数组
        File file = new File(".");
        file = new File("E:\\ideaWorkspace\\project\\java_edu\\javabasic\\reflection\\GenerateClass.class");
        System.out.println(file.getCanonicalPath());
        //InputStream input = new FileInputStream(file.getCanonicalPath()+"\\GenerateClass.class");
        InputStream input = new FileInputStream(file);
        long length = file.length();
        byte[] result = new byte[1024];

        int count = input.read(result);
        // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.defineMyClass( result, 0, count);
        //测试加载是否成功，打印class 对象的名称
        System.out.println(clazz.getCanonicalName());

        //实例化一个Programmer对象
        Object o= clazz.newInstance();
        try {
            //调用Programmer的code方法
            clazz.getMethod("hello", String.class).invoke(o, "你好反射");
        } catch (IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
