package com.qinh;

import com.qinh.entity.Animal;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
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
            //7.参数信息
            Parameter[] parameters = m.getParameters();
            for(Parameter p : parameters){
                //if(p.isNamePresent()){
                    System.out.println("参数名：" + p.getName() + " 形参类型：" + p.getType() + " 泛型类型：" + p.getParameterizedType()+"/n");
                //}
            }

            System.out.println();
        }

    }

    /**
     * 获取运行时类的父类
     */
    @Test
    public void t3(){
        Class<Animal> clazz = Animal.class;
        Class<? super Animal> superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }

    /**
     * 获取运行时类带泛型的父类
     */
    @Test
    public void t4(){
        Class<Animal> clazz = Animal.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    /**
     * 获取运行时类带泛型的父类的泛型
     */
    @Test
    public void t5(){
        Class<Animal> clazz = Animal.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //获取泛型类型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        for (Type t : actualTypeArguments){
            System.out.println(t.getTypeName());
            System.out.println(((Class)t).getName());
        }
    }

    /**
     * 获取运行时类实现的接口
     */
    @Test
    public void t6(){
        Class<Animal> clazz = Animal.class;

        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces){
            System.out.println(c);
        }
        System.out.println();
        //获取运行时类的父类实现的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class c : interfaces1){
            System.out.println(c);
        }
    }

    /**
     * 获取运行时类所在的包
     */
    @Test
    public void t7(){
        Class<Animal> clazz = Animal.class;
        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /**
     * 获取运行时类声明的注解
     */
    @Test
    public void t8(){
        Class<Animal> clazz = Animal.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a : annotations){
            System.out.println(a);
        }
    }

    @Test
    public void t9(){
        Class<Animal> clazz = Animal.class;
        try {
            Constructor<Animal> constructor = clazz.getDeclaredConstructor();
            Animal animal = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
