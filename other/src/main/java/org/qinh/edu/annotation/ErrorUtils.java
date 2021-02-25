package org.qinh.edu.annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ErrorUtils {
    public static void faultyMethod(List<String>... lists){
        List[] listArray = lists;
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(new Random().nextInt(100));
        listArray[0] = myList;
        Integer s = (Integer) listArray[0].get(0);
        System.out.println(s);

    }
}
