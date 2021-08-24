package com.qinh;

import com.qinh.entity.Employee;
import com.qinh.entity.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-13-21:06
 */
public class StreanAPITest1 {

    /**
     * 1-筛选与切片
     * filter(Predicate p) 接收 Lambda ， 从流中排除某些元素
     * distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     * limit(long maxSize) 截断流，使其元素不超过给定数量
     * skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一 个空流。与 limit(n) 互补
     */
    @Test
    public void t1(){
        List<Employee> list = EmployeeData.getEmployees();
        //查询员工表中薪资大于7000的员工信息
        list.stream().filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println();
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();
        list.stream().skip(30).forEach(System.out::println);
        System.out.println();
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",41,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));

        list.stream().distinct().forEach(System.out::println);

        System.out.println("稍许复杂的filter------------");
        list.stream().filter(distinctByProperty(e -> e.getName())).forEach(System.out::println);

    }

    public static <T>Predicate<T> distinctByProperty(Function<? super T,?> property){
        List list = new ArrayList();
        return new Predicate<T>(){
            @Override
            public boolean test(T t) {
                boolean flag = !list.contains(property.apply(t));
                list.add(property.apply(t));
                return flag;
            }
        };
        /*return t -> {
            boolean flag = !list.contains(property.apply(t));
            list.add(property.apply(t));
            return flag;
        };*/
    }

    /**
     * 2-映 射
     * map(Function f)
     * 接收一个函数作为参数，该函数会被应用到每个元 素上，并将其映射成一个新的元素。
     * mapToDouble(ToDoubleFunction f)
     * 接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 DoubleStream。
     * mapToInt(ToIntFunction f)
     * 接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 IntStream。
     * mapToLong(ToLongFunction f)
     * 接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 LongStream。
     * flatMap(Function f)
     * 接收一个函数作为参数，将流中的每个值都换成另 一个流，然后把所有流连接成一个流
     */
    @Test
    public void t2(){
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println();
        //获取员工姓名长度大于3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(n -> n.length() > 3).forEach(System.out::println);
        System.out.println();
        //
        Stream<Stream<Character>> streamStream = list.stream().map(StreanAPITest1::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        System.out.println();
        Stream<Character> characterStream = list.stream().flatMap(StreanAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    /**
     * 将字符串中的多个字符构成的集合转换为对应的Stream的实例
     *
     * @param str
     * @return
     */
    public static Stream<Character>  fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void t3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        //list1.add(list2);
        //[1, 2, 3, [4, 5, 6]]
        //System.out.println(list1);
        list1.addAll(list2);
        //[1, 2, 3, 4, 5, 6]
        System.out.println(list1);

    }

    /**
     * 3-排序
     * sorted() 产生一个新流，其中按自然顺序排序
     * sorted(Comparator com) 产生一个新流，其中按比较器顺序排序
     */
    @Test
    public void t4(){
        List<Integer> list = Arrays.asList(12, 32, 44, 5, 32, 87, 65);
        list.stream().sorted().forEach(System.out::println);
        System.out.println();

        List<Employee> employees = EmployeeData.getEmployees();
        //抛异常，原因：Employee没有实现Comparable接口
        //employees.stream().sorted().forEach(System.out::println);
        employees.stream().sorted((e1,e2) -> {
            int compare = Integer.compare(e1.getAge(), e2.getAge());
            if (compare != 0){
                return compare;
            }else {
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
    }
}
