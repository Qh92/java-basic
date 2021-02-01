package com.qinh;

import com.qinh.entity.Animal;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * 获取运行时类的方法结构
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-01-1:17
 */
public class MethodTest {

    @Test
    public void t1(){
        Class<Animal> clazz = Animal.class;

        //getMethods():获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }
        System.out.println();
        //getDeclaredMethods()：获取运行时类中声明的所有方法（不包含父类中声明的方法）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods){
            System.out.println(m);
        }
    }


    /**
     * @Xxx(注解)
     * 权限修饰符  返回值类型  方法名(参数类型1 形参名1,...) throws XxxException{}
     *
     */
    @Test
    public void t2(){
        Class<Animal> clazz = Animal.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods){
            //1.获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations){
                //@com.qinh.entity.MyAnnotation(value=猫科)
                System.out.print(a + "\t");
            }
            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            //3.返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            //4.方法名
            System.out.print(m.getName());

            System.out.print("(");
            //5.形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if (!(Objects.isNull(parameterTypes) && parameterTypes.length == 0)){
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(i == parameterTypes.length - 1){
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + " args_" + i + ",");
                }
            }
            System.out.print(")");

            //6.抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();
            System.out.print(" exception: " + Objects.isNull(exceptionTypes) + " length: " +exceptionTypes.length);
            /*System.out.print(" exception " + Objects.isNull(exceptionTypes));
            if(!(Objects.isNull(exceptionTypes) && exceptionTypes.length == 0)){
                System.out.print(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if(i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }*/
            if(exceptionTypes.length > 0){
                System.out.print(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if(i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }


            System.out.println();
        }

    }


}
