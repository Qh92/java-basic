package org.qinh.edu.annotationtest;

import java.util.ArrayList;
import java.util.List;


public class SuppressWarningsTest {
    public static void main(String[] args) {
        List myList = new ArrayList<Integer>();
        myList.add(20);
        List<String> ls = myList;
        System.out.println(ls.get(0));

    }
}
