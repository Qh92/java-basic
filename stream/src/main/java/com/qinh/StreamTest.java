package com.qinh;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * jdk8 stream类测试
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-08 09:05
 */
public class StreamTest {
    private List<Person> personList = new ArrayList<>();

    @Before
    public void t0(){
        personList.add(new Person("欧阳雪",18,"中国",'F'));
        personList.add(new Person("Tom",24,"美国",'M'));
        personList.add(new Person("Harley",22,"英国",'F'));
        personList.add(new Person("向天笑",20,"中国",'M'));
        personList.add(new Person("李康",22,"中国",'M'));
        personList.add(new Person("小梅",20,"中国",'F'));
        personList.add(new Person("何雪",21,"中国",'F'));
        personList.add(new Person("李康",22,"中国",'M'));
    }

    /**
     * 中间操作
     */
    /*
    刷选与切片
    - filter：接收Lambda，从流中排除某些操作；
    - limit：截断流，使其元素不超过给定对象
    - skip(n)：跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
    - distinct()：去重，通过流所生成元素的hashCode()和equals()去除重复元素。
     */
    @Test
    public void t1(){

        /*
        假设有一个Person类和一个Person列表，现在有两个需求：1）找到年龄大于18岁的人并输出；2）找出所有中国人的数量。
         */
        //找到年龄大于18岁的人并输出
        /*Stream<Person> personStream = personList.stream().filter(p -> p.getAge() > 18);
        Optional<Person> first = personStream.findFirst();
        System.out.println(first.get());*/
        personList.stream().filter(p -> p.getAge() > 18).forEach(System.out::println);

        //找出所有中国人的数量
        long count = personList.stream().filter(p -> p.getCountry().equals("中国")).count();
        System.out.println(count);//6

        //从Person列表中取出两个女性
        personList.stream().filter(p -> p.getSex() == 'F').limit(2).forEach(System.out::println);

        //Person列表中从第2个女性开始，取出所有的女性
        personList.stream().filter(p -> p.getSex() == 'F').skip(1).forEach(System.out::println);

        //男性去重,通过流所生成元素的hashCode()和equals()去除重复元素
        personList.stream().filter(p -> p.getSex() == 'M').distinct().forEach(System.out::println);
    }

    /*
    映射
    - map--接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    - flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void t2(){

        //PersonCountry类来接收所有的国家信息
        personList.stream().map(p -> {
            PersonCountry country = new PersonCountry();
            country.setCountry(p.getCountry());
            return country;
        }).distinct().forEach(System.out::println);
    }

    @Test
    public void t3(){
        //有一个字符列表，需要提出每一个字符
        List<String> strings = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        //返回流中流
        final Stream<Stream<Character>> stream = strings.stream().map(StreamTest::getCharacterByString);
        //stream.forEach(System.out::println);
        stream.forEach(stm -> stm.forEach(System.out::print));

        System.out.println("-------------------");
        //返回一个流
        Stream<Character> stream1 = strings.stream().flatMap(StreamTest::getCharacterByString);
        stream1.forEach(System.out::print);
    }

    public static Stream<Character> getCharacterByString(String str){
        List<Character> characterList = new ArrayList<>();
        for(Character character : str.toCharArray()){
            characterList.add(character);
        }
        return characterList.stream();
    }

    /*
    排序
    - sorted()--自然排序(Comparable)
    - sorted(Comparator com)--定制排序（Comparator）
     */
    @Test
    public void t4(){
        //前面的personList按年龄从小到大排序,年龄相同，则再按姓名排序
        Stream<Person> sorted = personList.stream().sorted((p1, p2) -> {
            if (p1.getAge() == p2.getAge()) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p1.getAge().compareTo(p2.getAge());
            }
        });

        sorted.forEach(System.out::println);

    }

    /**
     * 终止操作
     */
    /*
    查找与匹配
    - allMatch--检查是否匹配所有元素
    - anyMatch--检查是否至少匹配一个元素
    - noneMatch--检查是否没有匹配所有元素
    - findFirst--返回第一个元素
    - findAny--返回当前流中的任意元素
    - count--返回流中元素的总个数
    - max--返回流中最大值
    - min--返回流中最小值
     */

    @Test
    public void t5(){
        //判断personList中的人是否都是成年人
        boolean b = personList.stream().allMatch(p -> p.getAge() >= 18);
        System.out.println(b);

        //判断personList中的人是否都是中国人
        boolean b2 = personList.stream().allMatch(p -> p.getCountry().equals("中国"));
        System.out.println(b2);

        //查找年龄最大的人的信息
        Optional<Person> max = personList.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        System.out.println("年龄最大的人信息："+max.get());

        //查找年龄最小的人的信息
        Optional<Person> min = personList.stream().min((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        System.out.println("年龄最小的人的信息："+min.get());

    }

    /**
     * 归约
     */
    /*
    归约操作可以将流中元素反复结合起来，得到一个值

    Optional<T> reduce(BinaryOperator<T> accumulator);

    T reduce(T identity, BinaryOperator<T> accumulator);

    <U> U reduce(U identity,
                  BiFunction<U, ? super T, U> accumulator,
                  BinaryOperator<U> combiner);
     */

    @Test
    public void t6(){
        //求1到100的和
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 100; i++){
            list.add(i);
        }
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        /*
        0  (1,2) -> 1 + 2 + 0
        3  (3,4) -> 3 + 4 + 3
        10 (5,6) -> 5 + 6 + 10
        .
        .
        .
         */

        //求所有人的年龄之和
        Optional<Integer> sum2 = personList.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println(sum2.get());
    }

    /**
     * 收集
     */
    /*
    collect：将流转换为其他形式，接收一个Collector接口实现 ，用于给Stream中汇总的方法
    <R, A> R collect(Collector<? super T, A, R> collector);

    <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);
     */
    @Test
    public void t7(){
        //改写map举例中的的例子，将国家收集起来转换成List
        List<String> country = personList.stream().map(p -> p.getCountry()).distinct().collect(Collectors.toList());
        System.out.println(country);

        //计算出平均年龄
        Double age = personList.stream().collect(Collectors.averagingInt(p -> p.getAge()));
        System.out.println(age);
        
        //找出最小年龄、最大年龄
        Optional<Integer> max = personList.stream().map(Person::getAge).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(max.get());
        Optional<Integer> min = personList.stream().map(Person::getAge).collect(Collectors.minBy(Integer::compareTo));
        System.out.println(min.get());
    }

}
