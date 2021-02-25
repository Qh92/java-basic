package org.qinh.edu.annotationtest;

import org.qinh.edu.annotation.Info;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class InfoTest {

    //@Deprecated
    @Info(name = "修饰方法",whatToDo = "这个是来获取相关信息的")
    public void info(){
    }

    public static void main(String[] args) throws NoSuchMethodException {
        //通过反射获取info方法
        Method method = InfoTest.class.getMethod("info");
        //判断该方法是否含有@info注解
        boolean annotationPresent = method.isAnnotationPresent(Info.class);
        if(annotationPresent){
            System.out.println
                    ("info方法上存在info注解");
        }else{
            System.out.println("info方法上不存在info注解");
        }
        //获取方法上的所有注解
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation != null && annotation instanceof Info){
                String name = ((Info) annotation).name();
                String whatToDo = ((Info) annotation).whatToDo();
                System.out.println("name : "+name);
                System.out.println("whatToDo : "+whatToDo);
            }
            System.out.println(annotation);
        }
        /**
         * 输出信息如下
         * info方法上存在info注解
         * @java.lang.Deprecated()
         * name : 修饰方法
         * whatToDo : 这个是来获取相关信息的
         * @cn.qh.edu.annotation.Info(name=修饰方法, whatToDo=这个是来获取相关信息的)
         */

    }
}
