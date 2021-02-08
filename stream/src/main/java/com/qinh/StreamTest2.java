package com.qinh;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1.无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
 * 2.为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
 * 3.惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
 * 4.可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
 *
 * 对stream的操作分为为两类，中间操作(intermediate operations)和结束操作(terminal operations)，二者特点是：
 * 中间操作总是会惰式执行，调用中间操作只会生成一个标记了该操作的新stream，仅此而已。
 * 结束操作会触发实际计算，计算发生时会把所有中间操作积攒的操作以pipeline的方式执行，这样可以减少迭代次数。计算完成之后stream就会失效。
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-08-20:05
 */
public class StreamTest2 {

    @Test
    public void t1(){
        //使用Stream.forEach()迭代
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        //stream.forEach(System.out::println);
        //保留长度等于3的字符串
        //stream.filter(str -> str.length() == 3).forEach(System.out::println);
        //stream.sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        //stream.map(str -> str.toUpperCase()).forEach(System.out::println);
        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        listStream.flatMap(list -> list.stream()).forEach(System.out::println);
    }

    /**
     * - Optional<T> reduce(BinaryOperator<T> accumulator)
     * - T reduce(T identity, BinaryOperator<T> accumulator)
     * - <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
     */
    @Test
    public void t2(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        //找出最长单词
        //Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        //Optional<String> longest = stream.max((s1, s2) -> s1.length() - s2.length());
        //System.out.println(longest.get());
        //求单词长度之和
        //Integer lengthSum = stream.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
    }

    @Test
    public void t3(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        //List<String> list = stream.collect(Collectors.toList());
        //Set<String> set = stream.collect(Collectors.toSet());
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);
        //ArrayList<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        //System.out.println(list);
        //List<String> list = stream.collect(Collectors.toList());
        //System.out.println(list);
        //ArrayList<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        //HashSet<String> set = stream.collect(Collectors.toCollection(HashSet::new));
        //String str = stream.collect(Collectors.joining("|", "{", "}"));
        //System.out.println(str);
    }
}
