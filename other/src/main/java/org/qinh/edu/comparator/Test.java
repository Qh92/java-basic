package org.qinh.edu.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-14-10:49
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> re = new ArrayList<>();

        re.add(1);
        re.add(2);
        re.add(6);
        re.add(5);
        re.add(8);
        re.add(8);
        re.add(4);

        Collections.sort(re, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //下面这么写，结果是降序
                if(o1 < o2){
                    return 1;
                }else if(o1 > o2){
                    return -1;
                }
                return 0;
            }

        });

        System.out.println(re);
    }
}
