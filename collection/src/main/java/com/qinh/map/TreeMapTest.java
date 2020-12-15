package com.qinh.map;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-12-15-20:35
 */
public class TreeMapTest {
    /*
    向TreeMap中添加key-value，要求key必须是由同一个类创建的对象
    因为要按照key进行排序：自然排序，定制排序
     */
    //自然排序
    @Test
    public void t1(){
        TreeMap treeMap = new TreeMap();
        User u1 = new User("Tom", 18);
        User u2 = new User("Jerry", 19);
        User u3 = new User("Jack", 30);
        User u4 = new User("Curry", 28);
        User u5 = new User("Westbrook", 26);

        treeMap.put(u1,96);
        treeMap.put(u2,89);
        treeMap.put(u3,85);
        treeMap.put(u4,90);
        treeMap.put(u5,100);

        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //定制排序
    @Test
    public void t2(){
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }
                throw new RuntimeException("输入的年龄不匹配");
            }
        });
        User u1 = new User("Tom", 18);
        User u2 = new User("Jerry", 19);
        User u3 = new User("Jack", 30);
        User u4 = new User("Curry", 28);
        User u5 = new User("Westbrook", 26);

        treeMap.put(u1,96);
        treeMap.put(u2,89);
        treeMap.put(u3,85);
        treeMap.put(u4,90);
        treeMap.put(u5,100);

        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
