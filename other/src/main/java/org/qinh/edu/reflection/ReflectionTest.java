package org.qinh.edu.reflection;

import org.junit.Test;

/**
 * @author: Qh
 * @version: 1.0
 * @date: 2021/2/18 17:54
 */
public class ReflectionTest {

    @Test
    public void t1() throws ClassNotFoundException {
        System.out.println(int.class);
        System.out.println(Integer.class);
        System.out.println(String.class);
        System.out.println(void.class);
        ClassLoader classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);
    }
}
