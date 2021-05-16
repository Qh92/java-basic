import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 *
 * @author: Qh
 * @version: 1.0
 * @date: 2021/3/24 15:52
 */
class Programmer{
    public void code(){
        System.out.println("I'm a Programmer,Just Coding.....");
    }
    public void eat(){
        System.out.println("I'm eating.....");
    }
}

class Hacker implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
        methodProxy.invokeSuper(o,args);
        System.out.println("****  Oh,what a poor programmer.....");
        return null;
    }
}

public class CGLIBProxyTest {
    public static void main(String[] args) {
        Programmer progammer = new Programmer();

        Hacker hacker = new Hacker();
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(progammer.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(hacker);
        Programmer proxy =(Programmer)enhancer.create();
        proxy.code();
        proxy.eat();

    }
}
