package com.qinh.map.safe;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk1.7/jdk1.8 ConcurrentHashMap比较
 *
 * 从以下方面考虑：
 * 数据结构：1.7 数组 + 链表 1.8 数组 + 链表 + 红黑树（BST,AVL,RED-BLACK-TREE）
 * 参数
 * 源码
 * 扩容流程
 * CAS + synchronized
 * volatile
 *
 * @author Qh
 * @version 1.0
 * @date 2021/12/20 16:43
 */
public class ConcurrentHashMapTest {


    @Test
    public void t1() {
        //整数的hash值是其本身
        //Integer num = 10;
        //System.out.println(num.hashCode());

        Map<Integer,String> map = new ConcurrentHashMap<>(4);
        for (int i = 0; i < 10000; i++) {
            map.put(i, "java-" + i);
        }

    }
}
