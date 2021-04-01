package com.qinh.List;

import com.qinh.entites.Person;
import org.junit.Test;

import java.util.*;

/**
 * 一。list接口框架
 * |---Collection接口：单列集合，用来存储一个一个的对象
 *           |----List接口：存储有序的、可重复的数据。 -->“动态”数组，替换原有的数组
 *                |---ArrayList：作为List接口的主要实现类;线程不安全的，效率高;底层使用Object[] elementData存储
 *                |---LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高;底层使用双向链表存储
 *                |===Vector：作为List接口的古老实现类;线程安全的，效率;低底层使用Object[] elementData存储
 *
 *  二。ArrayList的源码分析：
 *   1.jdk7情况下
 *      ArrayList list = new ArrayList();//底层创建了长度是10的Object[]数组 elementData
 *      List.add(123);//elementData[0] = new Integer(123);
 *      ...
 *      list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
 *      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数组复制到新的数组中。
 *      结论：建议开发中使用带参的构造器：ArrayList list = new ArrayList(int initialCapacity);
 *   2.jdk8中ArrayList的变化：
 *      ArrayList list = new ArrayList();//底层Object[] elementData初始化为{}，并没有创建长度为10的数组
 *      list.add(123);//第一次调用add()时，底层才创建了长度为10的数组，并将数据123添加到elementData[0]
 *      ...
 *      后续的添加和扩容操作与jdk7无异
 *   小结：jdk7中的ArrayList的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
 *
 *  三。LinkedList的源码分析：
 *      LinkedList list = new LinkedList();//内部声明了Node类型的first和last属性，默认值为null
 *      list.add(123);//将123封装到Node中，创建了Node对象
 *
 *      其中，Node定义为：体现了LinkedList的双向链表的说法
 *      private static class Node<E> {
 *         E item;
 *         Node<E> next;
 *         Node<E> prev;
 *
 *         Node(Node<E> prev, E element, Node<E> next) {
 *             this.item = element;
 *             this.next = next;
 *             this.prev = prev;
 *         }
 *     }
 *
 *  四。Vector的源码分析：jdk7和jdk8中通过Vector()构造器创建对象时 ，底层都创建了长度为10的数组
 *      在扩容方面，默认扩容为原来的数组长度的2倍
 *
 *  面试题：ArrayList、LinkedList、Vector三者的异同？
 *  相同点：三个类都是实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 *  不同：见上
 *
 *  五.list接口中常用方法
 *  增：add(Object obj)
 *  删：remove(int index) / remove(Object obj)
 *  改：set(int index,Object ele)
 *  查：get(int index)
 *  插：add(int index,Object ele)
 *  长度：size()
 *  遍历：① Iterator迭代器方式
 *       ②增强for循环
 *       ③普通的循环
 *
 *  六.Iterator 和 ListIterator 有什么区别？
 *  Iterator 可以遍历 Set 和 List 集合，而 ListIterator 只能遍历 List。
 *  Iterator 只能单向遍历，而 ListIterator 可以双向遍历（向前/后遍历）。
 *  ListIterator 从 Iterator 接口继承，然后添加了一些额外的功能，
 *  比如添加一个元素、替换一个元素、获取前面或后面元素的索引位置。
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-28-23:36
 */
public class ListTest {

    @Test
    public void t1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Date());
        list.add(new Person("Tome",12));

        System.out.println(list);

        /*
        1.void add(int index,Object ele):在index位置插入ele元素
         */
        list.add(1,"BB");
        System.out.println(list);
        /*
        2.boolean addAll(int index,Collection eles):从index位置开始将eles中的所有元素添加进来
         */
        List<Integer> integers = Arrays.asList(1, 2, 3);
        list.addAll(integers);
        System.out.println(list.size());//9
        /*
        3.Object get(int index):获取指定index位置的元素
         */
        System.out.println(list.get(0));//123
    }

    @Test
    public void t2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Date());
        list.add(new Person("Tome",12));
        list.add(456);

        /*
        4.int indexOf(Object obj)：返回obj集合中首次出现的位置.如果不存在就返回-1
         */
        int index = list.indexOf("AA");//2
        System.out.println(index);
        /*
        5.int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
         */
        System.out.println(list.lastIndexOf(456));//5
        /*
        6.Object remove(int index):移除指定index位置的元素，并返回此元素
         */
        Object remove = list.remove(0);
        System.out.println(remove);
        System.out.println(list);
        /*
        7.Object set(int index,Object ele):设置指定index位置的元素为ele
         */
        list.set(1,"CC");
        System.out.println(list);
        /*
        8.List subList(int fromIndex,int toIndex):返回从fromIndex到toIndex位置的子集合(左闭右开)
         */
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);
    }

    @Test
    public void t3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Date());
        list.add(new Person("Tome",12));

        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //方式二：增强for循环
        for(Object obj : list){
            System.out.println(obj);
        }
        //方式三：普通的循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void t4(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Date());
        list.add(new Person("Tome",12));

        //从后往前遍历
        ListIterator iterator = list.listIterator(list.size());
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
    }
}
