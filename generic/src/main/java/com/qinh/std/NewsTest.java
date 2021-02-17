package com.qinh.std;

import org.junit.Test;

import java.util.*;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-15-20:55
 */
public class NewsTest {

    @Test
    public void t1(){
        List<News> news = new ArrayList<>();
        news.add(new News("装一个新闻类News，包含新闻标题，新闻作者，新闻","qh","封装一个新闻类News，包含新闻标题，新闻作者，新闻内容，新闻类型三个属性"));
        news.add(new News("提供必要的访问器和修改器方法，重写toString方法","qh","提供必要的访问器和修改器方法，重写toString方法"));
        news.add(new News("要求只要新闻标题相同就判断为同一条新闻","qh","要求只要新闻标题相同就判断为同一条新闻"));
        for (News n : news){
            String title = n.getTitle().substring(0, 10);
            System.out.println(title);
        }
    }

    /**
     * 使用HashMap类实例化一个Map类型的对象m1，键（String类型）和值（int型）分别用于存储员工的姓名和工资，存入数据如下：	张三——800元；李四——1500元；王五——3000元；
     * 将张三的工资更改为2600元
     * 为所有员工工资加薪100元；
     * 遍历集合中所有的员工
     * 遍历集合中所有的工资
     */
    @Test
    public void t2(){
        Map<String,Integer> map = new HashMap<>();
        map.put("张三",800);
        map.put("李四",1500);
        map.put("王五",3000);

        map.put("张三",2600);
        //System.out.println(map.size());
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key);
            System.out.println(value);
            map.put(key,value + 100);
        }
        Set<Map.Entry<String, Integer>> entries1 = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator1 = entries1.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, Integer> next = iterator1.next();
            System.out.println(next.getKey() + " : " + next.getValue());
        }
        System.out.println("==============");
        Set<String> keys = map.keySet();
        Iterator<String> iteratorKey = keys.iterator();
        while (iteratorKey.hasNext()){
            String next = iteratorKey.next();
            System.out.println(next);
        }
        System.out.println();
        Collection<Integer> values = map.values();
        values.forEach(System.out::println);
    }
}
