import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
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
        print(list1);
        print(list2);
    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }


}
