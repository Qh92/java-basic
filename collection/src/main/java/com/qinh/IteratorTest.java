package com.qinh;

import com.qinh.entites.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，用于Iterator接口
 * 内部方法：hasNext() 和 next()
 * @author Qh
 * @version 1.0
 * @date 2020-11-26-0:09
 */
public class IteratorTest {
    @Test
    public void t1(){
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        Iterator iterator = collection.iterator();
        //测试演示
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        //报错 java.util.NoSuchElementException
//        System.out.println(iterator.next());

        //推荐方式
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
