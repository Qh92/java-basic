package com.qinh;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-12-21:43
 */
interface Human {
    String getBelief();

    void eat(String food);
}

//被代理类
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

class HumanUtil{
    public void method1(){
        System.out.println("====通用方法1===");
    }

    public void method2(){
        System.out.println("===通用方法2====");
    }
}

/**
 * 要想实现动态代理，需要解决的问题？
 * 问题1：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象
 * 问题2：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
 */

class ProxyFactory{
    //调用此方法，返回一个代理类的对象。解决问题1
    //object:被代理类的对象
    public static Object getProxyInstance(Object object){
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),handler);
    }
}

class MyInvocationHandler implements InvocationHandler{

    /**
     * 需要使用被代理类的对象进行赋值
     */
    private Object object;

    public void bind(Object object){
        this.object = object;
    }

    //当通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HumanUtil util = new HumanUtil();
        util.method1();

        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //object:被代理类的对象
        Object returnValue = method.invoke(object, args);

        util.method2();

        //上述方法的返回值就作为当前类中的invoke()的返回值
        return returnValue;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //proxyInstance：代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("四川麻辣烫");

        System.out.println("**************");

        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nike);
        proxyClothFactory.produceCloth();
    }
}
