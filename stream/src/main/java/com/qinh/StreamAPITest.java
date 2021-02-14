package com.qinh;

import com.qinh.entity.Employee;
import com.qinh.entity.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.Stream关注的是对数据的运算，与CPU打交道
 *   集合关注的是数据的存储，与内存打交道
 *
 * 2.
 * ①Stream 自己不会存储元素。
 * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 3.Stream执行流程
 * ① Stream的实例化
 * ② 一系列的中间操作（过滤、映射...）
 * ③ 终止操作
 *
 * 4.说明：
 * 4.1 一个中间操作链，对数据源的数据进行处理
 * 4.2 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-13-20:43
 */
public class StreamAPITest {

    //创建 Stream方式一：通过集合
    @Test
    public void t1(){
        //default Stream<E> stream() : 返回一个顺序流
        //default Stream<E> parallelStream() : 返回一个并行流
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        Stream<Employee> employeeStream = employees.parallelStream();
    }

    //创建 Stream方式二：通过数组
    @Test
    public void t2(){
        //调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        int[] arr = {1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001, "Tome");
        Employee e2 = new Employee(1001, "Jerry");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }

    //创建 Stream方式三：通过Stream的of()
    @Test
    public void t3(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
    }

    //创建 Stream方式四：创建无限流
    @Test
    public void t4(){
        //迭代 public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //生成 public static<T> Stream<T> generate(Supplier<T> s)

        //遍历前10个偶数
        Stream.iterate(0,t -> t + 2).limit(10).forEach(System.out::println);
        //生成10个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
