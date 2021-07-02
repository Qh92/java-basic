package com.qinh.notsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList在迭代的时候如果同时对其进行修改就会
 * 抛出java.util.ConcurrentModificationException异常
 * 并发修改异常
 *
 * @author Qh
 * @version 1.0
 * @date 2021/7/2 14:58
 */
public class ArrayListTest {
    public static void main(String[] args){
        //在迭代的时候对其进行修改会报java.util.ConcurrentModificationException异常
        //List<String> list = new ArrayList<>();
        //解决方法
        //1.Vector
        //List<String> list = new Vector<>();
        //2.Collections.synchronizedList()
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //3.CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName() + " : " + list);
            },String.valueOf(i)).start();
        }

        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }

    }
}
