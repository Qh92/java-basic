package com.qinh;

import com.qinh.entity.Employee;
import com.qinh.entity.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream的终止操作
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-14-21:24
 */
public class StreamAPITest2 {

    /**
     * 终端操作会从流的流水线生成结果。其结果可以是任何不是流的值，例 如：List、Integer，甚至是 void
     * 流进行了终止操作后，不能再次使用。
     * allMatch(Predicate p) 检查是否匹配所有元素
     * anyMatch(Predicate p) 检查是否至少匹配一个元素
     * noneMatch(Predicate p) 检查是否没有匹配所有元素
     * findFirst() 返回第一个元素
     * findAny() 返回当前流中的任意元素
     * count() 返回流中元素总数
     * max(Comparator c) 返回流中最大值
     * min(Comparator c) 返回流中最小值
     * forEach(Consumer c) 内部迭代(使用 Collection 接口需要用户去做迭代,称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
     */
    @Test
    public void t1(){
        //是否所有的员工的年龄都大于18岁
        List<Employee> employees = EmployeeData.getEmployees();
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);
        //是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);
        //是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);

        System.out.println();

        Optional<Employee> first = employees.stream().sorted((v1,v2) -> v1.getAge() - v2.getAge()).findFirst();
        System.out.println(first);
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any);
    }

    @Test
    public void t2(){
        List<Employee> employees = EmployeeData.getEmployees();
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);
        //返回最高的工资
        Optional<Double> max = employees.stream().map(e -> e.getSalary()).max(Double::compare);
        System.out.println(max);
        //返回最低工资的员工信息
        Optional<Employee> min = employees.stream().min((v1, v2) -> Double.compare(v1.getSalary(), v2.getSalary()));
        System.out.println(min);
        System.out.println();
        employees.stream().forEach(System.out::println);
        //使用集合的遍历操作
        employees.forEach(System.out::println);
    }

    /**
     * 2-归约
     * 备注：map 和 reduce 的连接通常称为 map-reduce 模式，因 Google 用它来进行网络搜索而出名。
     * reduce(T iden, BinaryOperator b)
     * 可以将流中元素反复结合起来，得到一 个值。返回 T
     * reduce(BinaryOperator b)
     * 可以将流中元素反复结合起来，得到一 个值。返回 Optional<T>
     */
    @Test
    public void t3(){
        //计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        //计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        //Optional<Double> salarySum = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        Optional<Double> salarySum = employees.stream().map(Employee::getSalary).reduce((s1,s2) -> s1 + s2);
        System.out.println(salarySum);
    }

    /**
     * 3-收集
     * Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、Set、 Map)。
     * collect(Collector c) 将流转换为其他形式。接收一个 Collector 接口的实现，用于给Stream中元素做汇总 的方法
     */
    @Test
    public void t4(){
        //查找工资大于6000的员工，结果返回为一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> list = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println();
        Set<Employee> set = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
}
