package com.qinh.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;

/**
 *  一、Map的实现类的结构：
 *   |-----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
 *           |-------HashMap:作为Map的主要实现类：线程不安全的。效率高；存储null的key和value
 *                  |----LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历
 *                                     原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素
 *                                     对于频繁的遍历操作，此类执行效率高于HashMap
 *           |-------TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 *                           底层使用红黑树
 *           |-------Hashtable:作为古老的实现类:线程安全的，效率低；不能存储null的key和value
 *                  |----Properties:常用来处理配置文件。key和value都是String类型
 *
 *      HashMap底层：数组+链表（jdk7及以前）
 *                  数组+链表+红黑树（jdk8）
 *
 *   面试题：
 *   1.HashMap的底层实现原理？
 *   2.HashMap和Hashtable的异同？
 *   3.CurrentHashMap与Hashtable的异同？
 *
 *
 *   二、Map结构的理解：
 *      Map中的key:无序的、不可重复的，使用Set存储所有的key      ---->key所在的类要重写equals()和hashCode()  (以HashMap为例)
 *      Map中的value:无序的、可重复的，使用Collection存储所有的value  --->value所在类要重写equals()
 *      一个键值对：key-value构成了一个Entry对象
 *      Map中的entry:无序的、不可重复的，使用Set存储所有的entry
 *
 * @author Qh
 * @version 1.0
 * @date 2020-12-08-21:47
 */
public class MapTest {

    @Test
    public void t1(){
        HashMap hashMap = new HashMap();
        hashMap.put(null,null);
        hashMap.put(12,12);

        /*Hashtable hashtable = new Hashtable();
        hashtable.put(123,null);//java.lang.NullPointerException
        hashtable.put(null,123);//java.lang.NullPointerException*/

    }
}
