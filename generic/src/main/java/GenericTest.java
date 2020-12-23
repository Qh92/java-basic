import org.junit.Test;

import java.util.*;

/**
 * 泛型的使用
 * 1.jdk 5.0新增的特性
 * 2.在集合中使用泛型：
 *   总结：
 *   ① 集合接口或集合类在jdk5.0时都修改为带泛型的结构
 *   ② 在实例化集合类时，可以指明具体的泛型类型
 *   ③ 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构(方法、构造器、属性等)使用到类的泛型的位置，都指定为实例化的泛型类型
 *     比如：add(E e) ---> 实例化以后，add(Integer e)
 *   ④ 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，使用包装类来替换。
 *   ⑤ 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。
 *
 * @author Qh
 * @version 1.0
 * @date 2020-12-23-22:35
 */
public class GenericTest {

    //在集合中使用泛型之前的情况
    @Test
    public void t1(){
        ArrayList arrayList = new ArrayList();
        //需求：存放学生的成绩
        arrayList.add(90);
        arrayList.add(87);
        arrayList.add(77);
        arrayList.add(96);
        //问题一：类型不安全
        arrayList.add("Tom");
        for(Object score : arrayList){
            //问题二：强转时，可能出现类型转换异常 ClassCastException
            int stuScore = (Integer) score;
            System.out.println(stuScore);
        }
    }
    //在集合中使用泛型的情况：以ArrayList为例
    @Test
    public void t2(){
        ArrayList<Integer> lists = new ArrayList<>();
        lists.add(98);
        lists.add(96);
        lists.add(89);
        lists.add(90);
        //编译时，就会进行类型检查，保证数据的安全
//        lists.add("111");
        for(Integer score : lists){
            //避免强转操作
            int stuScore = score;
            System.out.println(stuScore);
        }

        Iterator<Integer> iterator = lists.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    //在集合中使用泛型的情况：以HashMap为例
    @Test
    public void t3(){
        //jdk7新特性：类型推断
        Map<String,Integer> map = new HashMap<>();
        map.put("Tom",89);
        map.put("Curry",98);
        map.put("James",100);
        //泛型的嵌套
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println("key : "+key+" value : "+value);
        }
    }
}
