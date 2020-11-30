package com.qinh.set;

import org.junit.Test;

import java.util.*;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-11-30-0:27
 */
public class TreeSetTest {
    /*
    1.向TreeSet中添加的数据，要求是相同类的对象
    2.两种排序方式：自然排序(实现Comparable接口) 和 定制排序(Comparator)

    3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals()
    4.定制排序中，比较两个对象是否相同的标准为：compare()返回0，不再是equals()
     */
    @Test
    public void t1(){
        TreeSet set = new TreeSet();
        //失败，不能添加不同类的对象
        /*set.add(456);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(128);
        set.add(123);*/
        //举例1：
        /*set.add(123);
        set.add(333);
        set.add(-1);
        set.add(999);
        set.add(678);*/
        //举例2：
        set.add(new User("Tom",12));
        set.add(new User("Jerry",22));
        set.add(new User("Curry",18));
        set.add(new User("Jack",43));
        set.add(new User("Jack",56));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void t2(){
        Comparator comp = new Comparator() {
            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof  User){
                    User user1 = (User)o1;
                    User user2 = (User)o2;
                    return Integer.compare(user1.getAge(),user2.getAge());
                }else{
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };
        TreeSet set = new TreeSet(comp);
        //举例2：
        set.add(new User("Tom",12));
        set.add(new User("Jerry",22));
        set.add(new User("Curry",18));
        set.add(new User("Mary",43));
        set.add(new User("Jack",43));
        set.add(new User("Jack",56));


        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
