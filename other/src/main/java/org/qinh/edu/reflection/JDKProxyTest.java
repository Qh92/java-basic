package org.qinh.edu.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Qh
 * @version: 1.0
 * @date: 2021/3/11 16:00
 */

interface Person{
    void sleep();
    void eat();
}

abstract class Super{
    public void work(){
        System.out.println("正在工作....");
    }
}

//被代理类
class Man extends Super implements Person{

    @Override
    public void sleep() {
        System.out.println("正在睡觉.....");
    }

    @Override
    public void eat() {
        System.out.println("正在吃饭....");
    }
}

//代理对象
class MyProxy{

    public static Object getProxyInstance(Object obj){
        Handler handler = new Handler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}

class Handler implements InvocationHandler{

    private Object obj;

    public void bind(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始调用....");
        Object invoke = method.invoke(obj, args);
        System.out.println("调用结束....");
        return invoke;
    }
}

public class JDKProxyTest {

    public static void main(String[] args){
        Person man = (Person)MyProxy.getProxyInstance(new Man());
        man.eat();
    }


}
