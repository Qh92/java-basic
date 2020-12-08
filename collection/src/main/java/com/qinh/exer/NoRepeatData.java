package com.qinh.exer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * 在list内去除重复数字值，要求尽量简单
 * 自定义类：需要重写equals()和hashCode()
 * @author Qh
 * @version 1.0
 * @date 2020-12-08-21:04
 */
public class NoRepeatData {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list1 = duplicateList(list);
        Iterator iterator = list1.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static List duplicateList(List list){
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }

}
