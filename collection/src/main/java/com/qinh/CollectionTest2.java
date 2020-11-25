package com.qinh;

import com.qinh.entites.Person;
import org.junit.Test;

import java.util.*;

/**
 * Collection接口中声明的方法的测试
 * 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()
 * @author Qh
 * @version 1.0
 * @date 2020-11-24-23:47
 */
public class CollectionTest2 {

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
        1.contains(Object obj):判断当前集合中是否包含obj。在判断时会调用obj对象所在类的equals()
         */
        boolean contains = collection.contains(123);
        System.out.println(contains);//true
        System.out.println(collection.contains(new String("tom")));//true
        //重写Person的equals方法后，返回true
        System.out.println(collection.contains(new Person("Jerry",20)));//false

        /*
        2.containsAll(Collection coll):判断形参coll中的所有元素是否都存在于当前集合中。
         */
        Collection collection1 = Arrays.asList("123",456);
        System.out.println(collection.contains(collection1));//false

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

        /*
        3.remove(Object obj):从当前集合中移除obj元素。也会调用obj对象所在类的equals()
         */
        collection.remove(123);
        System.out.println(collection);//[456, Person{name='Jerry', age=20}, tom, Wed Nov 25 00:14:33 CST 2020, false]

        collection.remove(new Person("Jerry",20));
        System.out.println(collection);

        /*
        4.removeAll(Collection coll):差集，从当前集合中移除coll中所有的元素。
         */
        Collection collection1 = Arrays.asList("123",456);
        collection.removeAll(collection1);
        System.out.println(collection);//[tom, Wed Nov 25 00:19:29 CST 2020, false]
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

        /*
        5.retainAll(Collection col):交集，获取当前集合和col集合的交集，并返回给当前集合
         */
        Collection collection1 = Arrays.asList("123",456,false);
        collection.retainAll(collection1);
        System.out.println(collection);

        /*
        6.equals(Object obj):要想返回true,需要判断当前集合和形参集合元素都相同
         */

        Collection collection2 = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        Collection collection3 = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        System.out.println(collection2.equals(collection3));//true

        Collection collection4 = new ArrayList();
        collection.add(123);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(456);
        collection.add(false);
        System.out.println(collection2.equals(collection4));

    }

    @Test
    public void t4(){
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add(new Person("Jerry",20));
        collection.add(new String("tom"));
        collection.add(new Date());
        collection.add(false);

        /*
        7.hashCode():返回当前对象的哈希值
         */
        System.out.println(collection.hashCode());
        /*
        8.集合-->数组：toArray()
         */
        Object[] arr = collection.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        /*
        扩展：数组--->集合:调用Arrays类的静态方法asList()
         */
        List<String> strings = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(strings);//[AA, BB, CC]
        //在开发的时候不要写成这个，这个会将new int[]{123, 456}识别为一个整体
        List<int[]> ints = Arrays.asList(new int[]{123, 456});
        System.out.println(ints);//[[I@78e03bb5],识别为一个元素了
        List<Integer> integers = Arrays.asList(new Integer[]{123, 456});
        System.out.println(integers);//[123, 456]
        /*
        9.iterator():返回Iterator接口的实例，用于遍历集合元素，放在IteratorTest.java中
         */


    }
}
