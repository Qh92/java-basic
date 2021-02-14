package com.qinh;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 *
 * 消费型接口 Consumer<T>    void accept(T t)
 * 供给型接口 Supplier<T>    T get()
 * 函数型接口 Function<T,R>  R apply(T t)
 * 断定型接口 Predicate<T>   boolean test(T t)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-13-17:38
 */
public class LambdaTest2 {

    @Test
    public void t1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了，去happy一下，需要money: " + aDouble);
            }
        });

        System.out.println("***************");

        happyTime(400,money -> System.out.println("学习太累了，去happy一下，需要money: " + money));
    }

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void t2(){
        List<String> list = Arrays.asList("北京", "南京", "天津", "东京", "西京", "普京");
        List<String> filterStr = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterStr);

        System.out.println("********************");

        List<String> filterStr1 = filterString(list,s -> s.contains("京"));
        System.out.println(filterStr1);
    }

    /**
     * 根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
     *
     * @param list
     * @param pre
     * @return
     */
    public List<String> filterString(List<String>list, Predicate<String> pre){
        ArrayList<String> filter = new ArrayList<>();
        for (String s : list){
            if (pre.test(s)){
                filter.add(s);
            }
        }
        return filter;
    }

}
