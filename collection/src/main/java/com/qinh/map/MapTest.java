package com.qinh.map;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

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
 *   三、HashMap的底层实现原理？以jdk7为例说明
 *       HashMap map = new HashMap();
 *       在实例化以后，底层创建了长度是16的一维数组Entry[] table
 *       ...可能已经执行过多次put..
 *       map.put(key1,value1);
 *       首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置
 *       如果此位置上的数据为空，此时的key1-value1添加成功   ---情况1
 *       如果此位置上的数据不为空，（意味着此位置上存在一个或多个数据（以链表形式存在）），比较key1和已经存在的一个或多个数据的哈希值：
 *           如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功  ---情况2
 *           如果key1的哈希值和已经存在的某一个数据（key2-value2）的哈希值相同，继续比较：调用key1所在类的equals()方法，比较
 *               如果equals()返回false,此时的key1-value1添加成功    ---情况3
 *               如果equals()返回true,使用value1替换相同key的value值(使用value1替换value2)
 *
 *       补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。
 *
 *       在不断的添加过程中，会涉及到扩容问题，当超出临界值且要存放的位置非空时，默认的扩容方式：扩容为原来容量的2倍，并将原来的数据复制过来。
 *
 *       jdk8 相较于 jdk7在底层实现方面的不同：
 *       1. new HashMap():底层没有创建一个长度为16的数组
 *       2. jdk8底层的数组是：Node[].而非Entry[]
 *       3. 首次调用put()方法时，底层创建长度为16的数组
 *       4. jdk7底层结构只有：数组+链表。jdk8中底层结构：数组+链表+红黑树
 *          当数组的某一个索引位置上的元素以链表形式存在的数据个数 >8 且当前数组的长度 > 64时，此时此索引位置上的所有数据改为使用红黑树存储
 *
 *
 *        DEFAULT_INITIAL_CAPACITY ：HashMap的默认容量，16
 *        DEFAULT_LOAD_FACTOR : HashMap的默认加载因子，0.75
 *        threshold:扩容的临界值，=容量*填充因子：16 * 0.75 =>12
 *        TREEIFY_THRESHOLD : Bucket中链表长度大于该默认值，转化为红黑树：8
 *        MIN_TREEIFY_CAPACITY ： 桶中的Node被树化时最小的hash表容量：64
 *
 *
 *    四、LinkedHashMap的底层实现原理
 *        源码中：
 *        static class Entry<K,V> extends HashMap.Node<K,V> {
 *          Entry<K,V> before, after;//能够记录添加的元素的先后顺序
 *          Entry(int hash, K key, V value, Node<K,V> next) {
 *             super(hash, key, value, next);
 *          }
 *         }
 *
 *    五、Map接口中定义的方法：
 *
 *
 * @author Qh
 * @version 1.0
 * @date 2020-12-08-21:47
 */
public class MapTest {
    /*
    元素查询的操作：
    Object get(Object key):获取指定key对应的value
    boolean containsKey(Object key):是否包含指定的key
    boolean containsValue(Object value):是否包含指定的value
    int size():返回map中key-value对的个数
    boolean isEmpty():判断当前map是否为空
    boolean equals(Object obj):判断当前map和参数对象obj是否相等
     */
    @Test
    public void t4(){
        Map map = new HashMap<>();
        map.put("AA",123);
        map.put("BB",456);
        map.put("CC",999);

        Map map2 = new HashMap<>();
        map2.put("AA",123);
        map2.put("BB",456);
        map2.put("CC",999);
        System.out.println(map.get("AA"));//123
        System.out.println(map.containsKey("DD"));//false
        System.out.println(map.containsValue(123));//true
        System.out.println(map.size());//3
        System.out.println(map.isEmpty());//false
        System.out.println(map.equals(map2));//true
    }

    /*
    添加、删除、修改操作
    Object put(Object key,Object value):将指定key-value添加到（或修改）当前map对象中
    void putAll(Map m):将m中的所有key-value对存放到当前map中
    Object remove(Object key):移除指定key的key-value对，并返回value
    void clear():清空当前map中的所有数据
     */
    @Test
    public void t3(){
        Map map = new HashMap<>();
        //添加
        map.put("AA",123);
        map.put("BB",456);
        map.put("CC",999);
        //修改
        map.put("AA",888);
        System.out.println(map);//{AA=888, BB=456, CC=999}

        //putAll
        Map map2 = new HashMap<>();
        map2.put("DD",123);
        map2.put("EE",777);
        map.putAll(map2);
        System.out.println(map);//{AA=888, BB=456, CC=999, DD=123, EE=777}

        //remove(Object key)
        Object value = map.remove("AA");
        System.out.println(value);//888
        System.out.println(map);//{BB=456, CC=999, DD=123, EE=777}

        //clear()
        map.clear();
        System.out.println(map.size());//0

    }

    @Test
    public void t2(){
        HashMap hashMap = new HashMap();
        hashMap = new LinkedHashMap();
        hashMap.put(123,"AAA");
        hashMap.put(12,12);
        hashMap.put(33,"vfd");
        System.out.println(hashMap);
    }

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
