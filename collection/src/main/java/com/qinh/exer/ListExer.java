package com.qinh.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-11-29-15:38
 */
public class ListExer {
    /*
    区分List中remove(int index) 和 remove(Object obj)
     */
    @Test
    public void testListRemove(){
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//1,2
    }
    private void updateList(List list){
        list.remove(2);//删除2索引的数据
        //list.remove(new Integer(2));
    }
}
