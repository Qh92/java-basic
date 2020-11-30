package com.qinh.collection;

import com.qinh.entites.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，用于Iterator接口
 * 1.内部方法：hasNext() 和 next()
 * 2.集合对象每次调用iterator()方法都得到一个全新的迭代器，默认游标都在集合的第一个元素之前
 * 3.内部定义了remove(),可以在遍历的时候，删除集合中的元素，此方法不同于集合直接调用remove()
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
        //hasNest():判断是否还有下一个元素
        while (iterator.hasNext()){
            //next():①指针下移 ②将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }
    }

    @Test
    public void t2(){
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        //错误方式一：①报错java.util.NoSuchElementException ②跳着输出
        /*Iterator iterator = collection.iterator();
        while (iterator.next() != null){
            System.out.println(iterator.next());
        }*/

        //错误方式二：一直会输出123，程序终止不了
        /*
        原因：集合对象每次调用iterator()方法都得到一个全新的迭代器，默认游标都在集合的第一个元素之前
         */
        while(collection.iterator().hasNext()){
            System.out.println(collection.iterator().next());
        }
    }

    @Test
    public void t3(){
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        Iterator iterator = collection.iterator();
        /**
         * 如果还未调用next()或在上一次调用next()方法之后已经调用了remove方法
         * 再调用remove都会报IllegalStateException
         */
        while (iterator.hasNext()){
            //iterator.remove();//此时指针仍在最上面，指针指向null
            Object value = iterator.next();
            if("tom".equals(value)){
                iterator.remove();
            }
        }

        Iterator iterator1 = collection.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
    }
}
