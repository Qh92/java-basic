package com.qinh.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Collections：操作Collection、Map的工具类
 *
 * 面试题：Collection 和 Collections的区别？
 *
 * @author Qh
 * @version 1.0
 * @date 2020-12-15-21:22
 */
public class CollectionsTest {

    /*
    reverse(List):反转List中元素的顺序
    shuffle(List):对List集合元素进行随机排序
    sort(List):根据元素的自然顺序对指定List集合元素按升序排序
    sort(List,Comparator):根据指定的Comparator产生的顺序对List集合元素进行排序
    swap(List,int,int):将指定List集合中的i处元素和j处元素进行交换
    Object max(Collection):根据元素的自然顺序，返回给定集合中的最大元素
    Object max(Collection,Comparator):根据Comparator指定的顺序，返回给定集合中的最大元素
    Object min(Collection):
    Object min(Collection,Comparator)
    int frequency(Collection,Object):返回指定集合中指定元素的出现次数
    void copy(List dest,List src):将src中的内容复制到dest中
    boolean replaceAll(List list,Object oldVal,Object newVal):使用新值替换List对象中的所有旧值
     */

    @Test
    public void t2(){
        List list = new ArrayList();
        list.add(123);
        list.add(124);
        list.add(333);
        list.add(555);
        list.add(128);
        list.add(567);

        //错误的写法，报异常 java.lang.IndexOutOfBoundsException: Source does not fit in dest
        /*List dest = new ArrayList();
        Collections.copy(dest,list);
        System.out.println(dest);//java.lang.IndexOutOfBoundsException: Source does not fit in dest*/

        List dest = Arrays.asList(new Object[list.size()]);
        System.out.println(dest);
        Collections.copy(dest,list);
        System.out.println(dest);

        /*
        Collections 类中提供了多个synchronizedXxx()方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题
         */
        //返回的list1即为线程安全的List
        List list1 = Collections.synchronizedList(list);


    }


    @Test
    public void t1(){
        List list = new ArrayList();
        list.add(123);
        list.add(124);
        list.add(333);
        list.add(555);
        list.add(128);
        list.add(567);
        System.out.println(list);//[123, 124, 333, 555, 128, 567]
        /*Collections.reverse(list);
        System.out.println(list);//[567, 128, 555, 333, 124, 123]*/

        /*Collections.shuffle(list);
        System.out.println(list);*/

        /*Collections.sort(list);
        System.out.println(list);//[123, 124, 128, 333, 555, 567]*/

        /*Collections.swap(list,1,2);
        System.out.println(list);//[123, 333, 124, 555, 128, 567]*/
        /*int frequency = Collections.frequency(list, 123);
        System.out.println(frequency);//1*/

    }
}
