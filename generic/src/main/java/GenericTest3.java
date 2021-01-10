import demo2.Person;
import demo2.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 1.泛型在继承方面的体现
 *
 * 2.通配符的使用
 *
 * @author Qh
 * @version 1.0
 * @date 2020-12-28-0:46
 */
public class GenericTest3 {

    /*
    1.泛型在继承方面的体现

      虽然类A是类B的父类，G<A>和G<B>二者不具备子父类关系,二者是并列关系。
      补充：类A是类B的父类，A<G> 是 B<G> 的父类。

     */
    @Test
    public void t1(){
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //Date date = new Date();
        //str = date;

        List<Object> list1 = null;
        List<String> list2 = null;
        //此时的list1和list2的类型不具有子父类关系
        //list1 = list2;
        /*
        反证法：
        假设list1 = list2;
            list1.add(123);导致混入非String的数据。出错。
         */
        show(list1);
        show1(list2);
    }

    public void show1(List<String> list){

    }
    public void show(List<Object> list){

    }

    @Test
    public void t2(){
        List<String> list1 = null;
        ArrayList<String> list2 = null;
        list1 = list2;
    }

    /*
    2.通配符的使用
      通配符：?
      类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void t3(){
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list = null;
        list = list1;
        list = list2;
        //编译通过
        //print(list1);
        //print(list2);

        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入)：对于List<?>就不能向其内部添加数据
        //除了添加null之外
        list.add(null);
        //list.add("DD");
        //list.add("?");
        //获取(读取)：允许读取数据，读取的数据类型为Object
        Object o = list.get(0);
        System.out.println(o);

    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }

    /*
    3.有限制条件的通配符的使用。
       ? extends A : G<? extends A>可以作为G<A>和G<B>的父类，其中B是A的子类
       ? super A : G<? super A>可以作为G<A>和G<B>的父类，其中B是A的父类
     */
    @Test
    public void t4(){
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
        //编译不通过
        //list1 = list5;

        //编译不通过
        //list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据：
        list1 = list4;
        Person person = list1.get(0);
        //编译不通过
        //Student s = list1.get(0);

        list2 = list4;
        Object object = list2.get(0);
        //编译不通过
        //Person p = list2.get(0);

        //写入数据
        //编译不通过
        //list1.add(new Student());
        //list1.add(new Person());
        //编译运行都可以
        list2.add(new Person());
        list2.add(new Student());
        //list2.add(new Object());

    }


}
