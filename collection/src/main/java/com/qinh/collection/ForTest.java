package com.qinh.collection;

import com.qinh.entites.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * jdk5.0 新增了foreach循环，用于遍历集合、数组
 * @author Qh
 * @version 1.0
 * @date 2020-11-26-22:48
 */
public class ForTest {
    @Test
    public void t1(){
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        /*
        for(集合(数组)中元素的类型 局部变量 ： 集合(数组)对象)
        内部仍然调用了迭代器
         */
        for(Object obj : collection){
            System.out.println(obj);
        }
    }

    @Test
    public void t2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        for(int i : arr){
            System.out.println(i);
        }
    }

    //练习题
    @Test
    public void t3(){
        //方式一：普通for循环
        String[] arr = new String[]{"MM","MM","MM"};

        /*for (int i = 0; i < arr.length; i++) {
            arr[i] = "GG";
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);//GG,GG,GG
        }*/

        //方式二：增强for循环
        for(String s : arr){
            s = "GG";
        }
        for (int i = 0; i < arr.length ; i++) {
            System.out.println(arr[i]);//MM,MM,MM
        }

    }
}
